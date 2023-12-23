package com.tamilcreations.estorespringboot.products.feedbacks.replies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class ProductFeedbackReplyController
{
	@Autowired
	ProductFeedbackReplyRepo productFeedbackReplyRepo;
	
	@Autowired
	ProductFeedbackReplyService productFeedbackReplyService;
	
	@QueryMapping
	public List<ProductFeedbackReply> getProductFeedbackReplies(@Argument long productFeedbackId)
	{
		return productFeedbackReplyService.getProductFeedbackReplies(productFeedbackId);
	}
}
