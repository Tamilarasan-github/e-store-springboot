package com.tamilcreations.estorespringboot.products.feedbacks.replies;

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

import com.tamilcreations.estorespringboot.security.JwtAuthenticationFilter;
import com.tamilcreations.estorespringboot.users.User;
import com.tamilcreations.estorespringboot.users.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ProductFeedbackReplyController
{
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ProductFeedbackReplyRepo productFeedbackReplyReplyRepo;
	
	@Autowired
	private ProductFeedbackReplyService productFeedbackReplyService;
	
	@Autowired
	private UserService userService;
	
	@QueryMapping
	public ProductFeedbackReplyResponse getProductFeedbackReplysListByProductUuid(@Argument String productFeedbackUuid) throws Exception
	{
		return productFeedbackReplyService.getProductFeedbackRepliesList(productFeedbackUuid);
	}
	
	@MutationMapping
	public ProductFeedbackReplyResponse addNewProductFeedbackReply(@Argument ProductFeedbackReplyInput productFeedbackReplyInput) throws Exception
	{
		JwtAuthenticationFilter.getAuthorizationHeaderValueAndValidate(request);
		
		productFeedbackReplyInput.setCreatedDate(new Timestamp(new Date().getTime()));
		productFeedbackReplyInput.setUuid(UUID.randomUUID().toString());	
		
		String userUuid = productFeedbackReplyInput.getUser().getUuid();
		
		User user = userService.findUserByUserUuid(userUuid);
		
		productFeedbackReplyInput.setUser(user);
		
		ProductFeedbackReply newProductFeedbackReply = productFeedbackReplyInput.toProductFeedbackReply();
		
		return productFeedbackReplyService.addNewProductFeedbackReply(newProductFeedbackReply);
	}
	
	@QueryMapping
	public int getProductFeedbackReplyRepliesCount(@Argument Long productFeedbackReplyId)
	{
		return productFeedbackReplyService.getProductFeedbackReplyRepliesCount(productFeedbackReplyId);
	}
}
