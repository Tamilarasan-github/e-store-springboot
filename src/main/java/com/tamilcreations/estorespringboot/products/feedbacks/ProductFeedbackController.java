package com.tamilcreations.estorespringboot.products.feedbacks;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import com.tamilcreations.estorespringboot.users.User;
import com.tamilcreations.estorespringboot.users.UserService;
import com.tamilcreations.estorespringboot.utils.GenericService;
import com.tamilcreations.estorespringboot.utils.Roles;

import io.jsonwebtoken.Claims;


@Controller
public class ProductFeedbackController
{	
	@Autowired
	private ProductFeedbackService productFeedbackService;
	
	@Autowired
	private GenericService genericService;
	
	@Autowired
	private UserService userService;
	
	@Secured(value ={ Roles.USER, Roles.ADMIN, Roles.CUSTOMER_SUPPORT_READ_ACCESS, Roles.CUSTOMER_SUPPORT_WRITE_ACCESS })
	@QueryMapping
	public ProductFeedbackResponse getProductFeedbacksListByProductUuid(@Argument String productUuid) throws Exception
	{
		return productFeedbackService.getProductFeedbacksList(productUuid);
	}
	
	@Secured(value ={ Roles.USER, Roles.ADMIN, Roles.CUSTOMER_SUPPORT_READ_ACCESS, Roles.CUSTOMER_SUPPORT_WRITE_ACCESS })
	@MutationMapping
	public ProductFeedbackResponse addNewProductFeedback(@Argument ProductFeedbackInput productFeedbackInput) throws Exception
	{
		String loggedInUserName = genericService.getAuthenticatedUserName();
		String loggedInUserRole = genericService.getAuthenticatedRole();
		
		Claims claims = genericService.getClaims();
		productFeedbackInput.setCreatedDate(new Timestamp(new Date().getTime()));
		productFeedbackInput.setUuid(UUID.randomUUID().toString());	
		
		String userUuid = productFeedbackInput.getUser().getUuid();
		
		User user = userService.findUserByUserUuid(userUuid);
		
		productFeedbackInput.setUser(user);
		
		ProductFeedback newProductFeedback = productFeedbackInput.toProductFeedback();
		
		return productFeedbackService.addNewProductFeedback(newProductFeedback);
	}
	
	@Secured(value ={ Roles.USER, Roles.ADMIN, Roles.CUSTOMER_SUPPORT_READ_ACCESS, Roles.CUSTOMER_SUPPORT_WRITE_ACCESS })
	@QueryMapping
	public int getProductFeedbackRepliesCount(@Argument Long productFeedbackId)
	{
		return productFeedbackService.getProductFeedbackRepliesCount(productFeedbackId);
	}
}
