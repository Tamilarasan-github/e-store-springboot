package com.tamilcreations.estorespringboot.products.feedbacks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;


@Service
public class ProductFeedbackService
{
	@Autowired
	ProductFeedbackRepo productFeedbackRepo;
		
	public List<ProductFeedback> getProductFeedbacks(long productId)
	{
		return productFeedbackRepo.findByProductId(productId);
	}
}
