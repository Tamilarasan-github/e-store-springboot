package com.tamilcreations.estorespringboot.products.feedbacks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tamilcreations.estorespringboot.products.Product;
import com.tamilcreations.estorespringboot.products.ProductRepo;

@Service
public class ProductFeedbackService
{
	@Autowired
	ProductFeedbackRepo productFeedbackRepo;
	
	@Autowired
	ProductRepo productRepo;
	
	@Transactional
	public int getProductFeedbackRepliesCount(Long productFeedbackId)
	{
		return productFeedbackRepo.getProductFeedbackRepliesCount(productFeedbackId);
	}

	@Transactional
	public ProductFeedbackResponse getProductFeedbacksList(String productUuid) throws Exception
	{
		Optional<Product> productOptional = productRepo.findProductByUuid(productUuid);
		Product product;
		if (productOptional.isPresent())
		{
			product = productOptional.get();
		} else
		{
			throw new Exception("Product not found for the uuid!");
		}
		List<ProductFeedback> productFeedbacks = productFeedbackRepo
				.findProductFeedbacksByProductId(product.getProductId());

		if (productFeedbacks.size() <= 0)
		{
			return new ProductFeedbackResponse("No feedbacks are available for this product.");
		} 
		else
		{
			List<ProductFeedback> productFeedbackListWithRepliesCount = new ArrayList<ProductFeedback>();
			
			for (ProductFeedback productFeedback : productFeedbacks)
			{
				Long productFeedbackId = productFeedback.getProductFeedbackId();
				
				int repliesCount = getProductFeedbackRepliesCount(productFeedbackId);
				
				productFeedback.setFeedbackRepliesCount(repliesCount);
				
				productFeedbackListWithRepliesCount.add(productFeedback) ;
				
			}
			return new ProductFeedbackResponse(productFeedbackListWithRepliesCount,
					"All available productFeedbacks for this product is fetched sucessfully.");
		}
	}

	@Transactional
	public ProductFeedbackResponse addNewProductFeedback(ProductFeedback productFeedback) throws Exception
	{
		String productUuid = productFeedback.getProduct().getUuid();
		Optional<Product> productOptional = productRepo.findProductByUuid(productUuid);

		Product product;
		if (productOptional.isPresent())
		{
			product = productOptional.get();
		} 
		else
		{
			throw new Exception("Product not found for the uuid " + productUuid);
		}

		Long productId = product.getProductId();

		productFeedback.getProduct().setProductId(productId);
		ProductFeedback savedProductFeedback = productFeedbackRepo.saveAndFlush(productFeedback);
		List<ProductFeedback> productFeedbackList = new ArrayList<ProductFeedback>();
		productFeedbackList.add(savedProductFeedback);
		return new ProductFeedbackResponse(productFeedbackList, "Thank you for your valuable feedback!");

	}
}
