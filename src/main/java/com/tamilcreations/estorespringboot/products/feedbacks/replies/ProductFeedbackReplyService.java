package com.tamilcreations.estorespringboot.products.feedbacks.replies;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tamilcreations.estorespringboot.products.Product;
import com.tamilcreations.estorespringboot.products.ProductRepo;
import com.tamilcreations.estorespringboot.products.feedbacks.ProductFeedback;
import com.tamilcreations.estorespringboot.products.feedbacks.ProductFeedbackRepo;


@Service
public class ProductFeedbackReplyService
{
	@Autowired
	ProductFeedbackReplyRepo productFeedbackReplyRepo;
	
	@Autowired
	ProductFeedbackRepo productFeedbackRepo;
	
	@Autowired
	ProductRepo productRepo;
	
	@Transactional
	public int getProductFeedbackReplyRepliesCount(Long productFeedbackReplyId)
	{
		return productFeedbackReplyRepo.getProductFeedbackReplyRepliesCount(productFeedbackReplyId);
	}

	@Transactional
	public ProductFeedbackReplyResponse getProductFeedbackRepliesList(String productFeedbackUuid) throws Exception
	{
		Optional<ProductFeedback> productFeedbackOptional = productFeedbackRepo.findProductFeedbackByProductFeedbackUuid(productFeedbackUuid);
		ProductFeedback productFeedback;
		if (productFeedbackOptional.isPresent())
		{
			productFeedback = productFeedbackOptional.get();
		} else
		{
			throw new Exception("Product Feedback not found for the uuid!");
		}
		List<ProductFeedbackReply> productFeedbackReplies = productFeedbackReplyRepo
				.findProductFeedbackRepliesByProductFeedbackId(productFeedback.getProductFeedbackId());

		if (productFeedbackReplies.size() <= 0)
		{
			return new ProductFeedbackReplyResponse("No replies are available for this feedback.");
		} 
		else
		{	
			return new ProductFeedbackReplyResponse(productFeedbackReplies,
					"All available replies for this feedback is fetched sucessfully.");
		}
	}

	@Transactional
	public ProductFeedbackReplyResponse addNewProductFeedbackReply(ProductFeedbackReply productFeedbackReply) throws Exception
	{
		String productFeedbackUuid = productFeedbackReply.getProductFeedback().getUuid();
		Optional<ProductFeedback> productFeedbackOptional = productFeedbackRepo.findProductFeedbackByProductFeedbackUuid(productFeedbackUuid);

		ProductFeedback productFeedback;
		if (productFeedbackOptional.isPresent())
		{
			productFeedback = productFeedbackOptional.get();
		} 
		else
		{
			throw new Exception("Product Feedback not found for the uuid " + productFeedbackUuid);
		}

		Long productFeedbackId = productFeedback.getProductFeedbackId();

		productFeedbackReply.getProductFeedback().setProductFeedbackId(productFeedbackId);
		
		ProductFeedbackReply savedProductFeedbackReply = productFeedbackReplyRepo.saveAndFlush(productFeedbackReply);
		
//		List<ProductFeedbackReply> productFeedbackReplyList = new ArrayList<ProductFeedbackReply>();
//		productFeedbackReplyList.add(savedProductFeedbackReply);
		return new ProductFeedbackReplyResponse(savedProductFeedbackReply, "Thank you for your valuable feedback!");

	}
}
