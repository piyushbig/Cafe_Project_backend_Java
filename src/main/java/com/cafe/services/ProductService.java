package com.cafe.services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import com.cafe.Exception.ProductServiceException;
import com.cafe.Exception.UserServiceException;
import com.cafe.dto.ProductDetailsDto;
import com.cafe.entity.Order;
import com.cafe.entity.Product;
import com.cafe.entity.User;
import com.cafe.repository.Order1Repository;
import com.cafe.repository.ProductRepository;
import com.cafe.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

    @Autowired
    private Order1Repository order1Repository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProductRepository productRepository;

    public void add(Product product,int userId,MultipartFile file) throws UserServiceException {
		Optional<Product> checkProduct=productRepository.findByName(product.getName());
		
		Product p = new Product();
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		
		if(fileName.contains(".."))
		{
			System.out.println("not a a valid file");
		}
		try {
			
			
			p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
		if(checkProduct.isEmpty()) {
			
			User user = userRepository.findById(userId)
	                .orElseThrow(() -> new UserServiceException("User not found with id: " + userId));
			
			product.setUser(user);
			
		Product savedProduct=productRepository.save(product);
		
		}else throw new ProductServiceException("product already Registerd!!");
	}

    public List<ProductDetailsDto> getProductDetailsForApprovedOrders() {
    	return order1Repository.getProductDetailsForApprovedOrders();
    }
    @Transactional
    public void updateOrder(Long orderId, Order updatedOrder) {
        // Retrieve the existing order from the database
        Order existingOrder = order1Repository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Update the existing order with the new details
        existingOrder.setStatus(updatedOrder.getStatus());
        existingOrder.setTotalAmount(updatedOrder.getTotalAmount());
        // Update other fields as needed

        // Save the updated order back to the database
        order1Repository.save(existingOrder);
    }

    @Transactional
    public List<ProductDetailsDto> updateOrderItemQuantity(Long orderId, int productId, int quantity) {
        Object log;
		try {
            order1Repository.updateOrderItemQuantity(orderId, productId, quantity);

            // Fetch the updated details
            List<ProductDetailsDto> updatedDetails = order1Repository.getProductDetailsForApprovedOrders();

           
            return updatedDetails;
        } catch (Exception e) {
            
            throw new RuntimeException("Error updating order item quantity", e);
        }
    }
    
//    @Transactional
//    public List<ProductDetailsDto> deleteOrderItem(Long orderId, int productId) {
//        try {
//            // Delete the order item
//            order1Repository.deleteOrderItem(orderId, productId);
//
//            // Fetch the updated details
//            List<ProductDetailsDto> updatedDetails = order1Repository.getProductDetailsForApprovedOrders();
//
//            
//            return updatedDetails;
//        } catch (Exception e) {
//            
//            throw new RuntimeException("Error deleting order item", e);
//        }
//    }
    @Transactional
    public void deleteOrderItem(Long orderId, int productId) {
        order1Repository.deleteOrderItem(orderId, productId);
    }
    @Transactional
    public void deleteProduct( int productId) {
        order1Repository.deleteProduct( productId);
    }
    

    public List<Product> fetchById(int id) {
        return productRepository.findByUserId(id);
    }
    public List<Product> fetchAll() {
        return productRepository.findAll();
    }
    
    public void  saveProductToDB(MultipartFile file,String name,String description
			,int price)
	{
		Product p = new Product();
		
		
	}
}

