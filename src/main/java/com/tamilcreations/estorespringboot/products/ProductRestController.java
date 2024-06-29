package com.tamilcreations.estorespringboot.products;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tamilcreations.estorespringboot.sellers.SellerService;
import com.tamilcreations.estorespringboot.users.User;
import com.tamilcreations.estorespringboot.users.UserService;
import com.tamilcreations.estorespringboot.utils.AmazonS3Service;
import com.tamilcreations.estorespringboot.utils.GenericService;
import com.tamilcreations.estorespringboot.utils.Roles;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/upload")
public class ProductRestController
{
	@Autowired
	private GenericService genericService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AmazonS3Service amazonS3Service;
	
	
	
	@Secured(value= {Roles.SELLER_ALL_MODULES_FULL_ACCESS, Roles.SELLER_PRODUCT_WRITE_ACCESS })
	@PostMapping("/product-display-pic/{productUuid}")
	public ResponseEntity<Product> uploadProductDisplayPicForLoggedInSeller(@PathVariable String productUuid, @RequestParam MultipartFile file) throws IOException 
	{
		Claims claims = genericService.getClaims();
		String loggedInUser = claims.get("phoneNumber").toString();

		User user = userService.getUserByPhoneNumber(loggedInUser);
		Long loggedInSellerId = user.getSellerId();
		
		Product product = productService.findProductByProductUuid(productUuid);
		Long sellerIdInTheRequest = product.getSeller().getSellerId();
						
		if(loggedInSellerId.compareTo(sellerIdInTheRequest) == 0)
		{
			if (!file.isEmpty()) {
	            try {
	                
	                BufferedImage image = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
	                
	               
	                int width = image.getWidth();
	                int height = image.getHeight();
	                System.out.println("Image Resolution: " + width + " x " + height);
	                
	                if(width!=200 || height!=200)
	                {
	                	throw new RuntimeException("Please upload a file of size 200 x 200 for product display pic.");
	                }
	                
	            } catch (IOException e) {
	                e.printStackTrace();
	                throw new RuntimeException("Failed to read the file uploaded. Please try again later with valid file.");
	            }
	        } else {
	        	throw new RuntimeException("File is empty.");
	        }
		
			String filePath = amazonS3Service.uploadProductDisplayPicToAwsS3Bucket(file, productUuid);
			product.setImageUrl(filePath);
			Product productImageURLUpdated = productService.updateProductDisplayPic(product);
			return new ResponseEntity<>(productImageURLUpdated, HttpStatus.OK);

		}
		else
		{
			throw new RuntimeException("You do not have permission to add new Products for other sellers.");
		}
	}
	
	@Secured(value= {Roles.CUSTOMER_SUPPORT_WRITE_ACCESS, Roles.ADMIN, Roles.SUPER_ADMIN})
	@PostMapping("/support/product-display-pic/{productUuid}")
	public ResponseEntity<Product> uploadProductDisplayPicOnBehalfOfSeller(@PathVariable String productUuid, @RequestParam MultipartFile file) throws IOException
	{
		//User user = JwtAuthenticationFilter.getAuthorizationHeaderValueAndValidate(request);
		Claims claims = genericService.getClaims();
		String loggedInUser = claims.get("phoneNumber").toString();

		User user = userService.getUserByPhoneNumber(loggedInUser);
		Long loggedInSellerId = user.getSellerId();
		
		Product product = productService.findProductByProductUuid(productUuid);
		Long sellerIdInTheRequest = product.getSeller().getSellerId();
				
			String filePath = amazonS3Service.uploadProductDisplayPicToAwsS3Bucket(file, productUuid);
			product.setImageUrl(filePath);
			return new ResponseEntity<>(productService.updateProductDisplayPic(product), HttpStatus.OK);
	}
}
