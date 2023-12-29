package com.tamilcreations.estorespringboot.products.feedbacks;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.tamilcreations.estorespringboot.users.User;
import com.tamilcreations.estorespringboot.users.UserService;


@Controller
public class ProductFeedbackController
{
	@Autowired
	ProductFeedbackRepo productFeedbackRepo;
	
	@Autowired
	ProductFeedbackService productFeedbackService;
	
	@Autowired
	UserService userService;
	
	@QueryMapping
	public ProductFeedbackResponse getProductFeedbacksListByProductUuid(@Argument String productUuid) throws Exception
	{
		return productFeedbackService.getProductFeedbacksList(productUuid);
	}
	
	@MutationMapping
	public ProductFeedbackResponse addNewProductFeedback(@Argument ProductFeedbackInput productFeedbackInput) throws Exception
	{
		productFeedbackInput.setCreatedDate(new Timestamp(new Date().getTime()));
		productFeedbackInput.setUuid(UUID.randomUUID().toString());	
		
		String userUuid = productFeedbackInput.getUser().getUuid();
		
		User user = userService.findUserByUserUuid(userUuid);
		
		productFeedbackInput.setUser(user);
		
		ProductFeedback newProductFeedback = productFeedbackInput.toProductFeedback();
		
		return productFeedbackService.addNewProductFeedback(newProductFeedback);
	}
	
	@QueryMapping
	public int getProductFeedbackRepliesCount(@Argument Long productFeedbackId)
	{
		return productFeedbackService.getProductFeedbackRepliesCount(productFeedbackId);
	}
}
