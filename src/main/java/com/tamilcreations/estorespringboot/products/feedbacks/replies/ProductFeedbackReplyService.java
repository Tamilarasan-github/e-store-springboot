package com.tamilcreations.estorespringboot.products.feedbacks.replies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductFeedbackReplyService
{
	@Autowired
	ProductFeedbackReplyRepo productFeedbackReplyRepo;
	
	@Transactional
	public List<ProductFeedbackReply> getProductFeedbackReplies(long productFeedbackId)
	{
		return productFeedbackReplyRepo.findByProductFeedbackId(productFeedbackId);
	}
}
