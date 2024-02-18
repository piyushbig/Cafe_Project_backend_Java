package com.cafe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cafe.Exception.ProductServiceException;
import com.cafe.Exception.ResourceNotFoundException;
import com.cafe.Exception.UserServiceException;
import com.cafe.dto.CafeStatus;
import com.cafe.dto.ProductDetailsDto;
import com.cafe.entity.Product;
import com.cafe.repository.ProductRepository;
import com.cafe.services.ProductService;

@RestController
@CrossOrigin
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    private final ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productRepository = productRepository;
		this.productService = productService;
    }

    @PostMapping("/register/{userId}")
	public CafeStatus register(@RequestBody Product product,@PathVariable int userId,@RequestParam("file") MultipartFile file){
		try {
			 productService.add(product, userId,file);
			
			CafeStatus status = new CafeStatus();
			status.setStatus(true);
			status.setMessage("Product added successfully!!");
			
			return status;
			
		}catch(ProductServiceException e) {
			CafeStatus status = new CafeStatus();
			status.setStatus(false);
			status.setMessage(e.getMessage());
			return status;
			
		}catch(UserServiceException e) {
			System.out.println(e.getMessage());
			CafeStatus status = new CafeStatus();
			status.setStatus(false);
			status.setMessage(e.getMessage());
			return status;
		}
		
	}
    
//    @PostMapping("/addP")
//    public String saveProduct(
//    		)
//    {
//    	productService.saveProductToDB(, name, desc, price);
//    	return "redirect:/listProducts.html";
//    }
     

    @GetMapping("/details")
    public ResponseEntity<List<ProductDetailsDto>> getAllProductDetails() {
        List<ProductDetailsDto> productDetailsList = productService.getProductDetailsForApprovedOrders();
        return new ResponseEntity<>(productDetailsList, HttpStatus.OK);
    }
    @PutMapping("/updateQuantity")
    public ResponseEntity<List<ProductDetailsDto>> updateOrderItemQuantity(@RequestParam Long orderId,
                                                                          @RequestParam int productId,
                                                                          @RequestParam int quantity) {
        try {
            List<ProductDetailsDto> updatedDetails = productService.updateOrderItemQuantity(orderId, productId, quantity);
            return new ResponseEntity<>(updatedDetails, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
//    @DeleteMapping("/deleteOrderItem")
//    public ResponseEntity<List<ProductDetailsDto>> deleteOrderItem(@RequestParam Long orderId,
//                                                                  @RequestParam int productId) {
//        try {
//            List<ProductDetailsDto> updatedDetails = productService.deleteOrderItem(orderId, productId);
//            return new ResponseEntity<>(updatedDetails, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    @DeleteMapping("/deleteOrderItem")
    public ResponseEntity<String> deleteOrderItem(@RequestParam Long orderId, @RequestParam int productId) {
        try {
            productService.deleteOrderItem(orderId, productId);
            return new ResponseEntity<>("Order item deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //for featch all data from Product table
    @GetMapping("/product/fetchById/{userId}")
	public List<Product> fetch(@PathVariable int userId) {
		List<Product> product = productService.fetchById(userId);
		
		return product;
	}
    
    @GetMapping("/fetchAll")
    public List<Product> fetchProducts(){
    	
    	List<Product> product = productService.fetchAll();
    	return product;
    	
    }
    
    @DeleteMapping("/deleteProduct")
    public ResponseEntity<String> deleteproduct( @RequestParam  int productId) {
        try {
            productService.deleteProduct( productId);
            return new ResponseEntity<>("Order item deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable int id,
            @RequestBody Product updatedProduct
    ) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        // Update the existing product with new details
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        
        // Set the updated category for the existing product
        existingProduct.setCategory(updatedProduct.getCategory());

        // Save the updated product to the database
        Product savedProduct = productRepository.save(existingProduct);
        return ResponseEntity.ok(savedProduct);
    }
}



