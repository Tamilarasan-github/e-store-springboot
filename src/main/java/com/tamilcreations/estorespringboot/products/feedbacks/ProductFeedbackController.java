package com.tamilcreations.estorespringboot.products.feedbacks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class ProductFeedbackController
{
	@Autowired
	ProductFeedbackRepo productFeedbackRepo;
	
	@Autowired
	ProductFeedbackService productFeedbackService;
	
	@QueryMapping
	public List<ProductFeedback> getProductFeedbacks(@Argument long productId)
	{
		return productFeedbackService.getProductFeedbacks(productId);
	}
}
