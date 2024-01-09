package com.tamilcreations.estorespringboot.products.feedbacks.replies;

import java.util.List;

public class ProductFeedbackReplyResponse
{
	private ProductFeedbackReply productFeedbackReply;
	
	private List<ProductFeedbackReply> productFeedbackReplies;
	
	private String sucessMessage;
	
	private String errorMessage;
	
	
	

	public ProductFeedbackReplyResponse(String errorMessage)
	{
		super();
		this.errorMessage = errorMessage;
	}

	
	
	public ProductFeedbackReplyResponse(List<ProductFeedbackReply> productFeedbackReplies, String sucessMessage)
	{
		super();
		this.productFeedbackReplies = productFeedbackReplies;
		this.sucessMessage = sucessMessage;
	}



	public ProductFeedbackReplyResponse(ProductFeedbackReply productFeedbackReply, String sucessMessage)
	{
		super();
		this.productFeedbackReply = productFeedbackReply;
		this.sucessMessage = sucessMessage;
	}

	public ProductFeedbackReplyResponse(ProductFeedbackReply productFeedbackReply, List<ProductFeedbackReply> productFeedbackReplies,
			String sucessMessage, String errorMessage)
	{
		super();
		this.productFeedbackReply = productFeedbackReply;
		this.productFeedbackReplies = productFeedbackReplies;
		this.sucessMessage = sucessMessage;
		this.errorMessage = errorMessage;
	}



	public ProductFeedbackReply getProductFeedbackReply()
	{
		return productFeedbackReply;
	}



	public void setProductFeedbackReply(ProductFeedbackReply productFeedbackReply)
	{
		this.productFeedbackReply = productFeedbackReply;
	}



	public List<ProductFeedbackReply> getProductFeedbackReplies()
	{
		return productFeedbackReplies;
	}



	public void setProductFeedbackReplies(List<ProductFeedbackReply> productFeedbackReplies)
	{
		this.productFeedbackReplies = productFeedbackReplies;
	}



	public String getSucessMessage()
	{
		return sucessMessage;
	}



	public void setSucessMessage(String sucessMessage)
	{
		this.sucessMessage = sucessMessage;
	}



	public String getErrorMessage()
	{
		return errorMessage;
	}



	public void setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	
	
	


	
	
}
