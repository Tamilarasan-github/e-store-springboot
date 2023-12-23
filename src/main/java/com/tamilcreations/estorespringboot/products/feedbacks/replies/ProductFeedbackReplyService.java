package com.tamilcreations.estorespringboot.products.feedbacks.replies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;


@Service
public class ProductFeedbackReplyService
{
	@Autowired
	ProductFeedbackReplyRepo productFeedbackReplyRepo;
		
	public List<ProductFeedbackReply> getProductFeedbackReplies(long productFeedbackId)
	{
		return productFeedbackReplyRepo.findByProductFeedbackId(productFeedbackId);
	}
}
