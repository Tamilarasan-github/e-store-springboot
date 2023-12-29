package com.tamilcreations.estorespringboot.products.feedbacks;

import java.util.List;

public class ProductFeedbackResponse
{
	private ProductFeedback productFeedback;
	
	private List<ProductFeedback> productFeedbacks;
	
	private String sucessMessage;
	
	private String errorMessage;
	
	
	

	public ProductFeedbackResponse(String errorMessage)
	{
		super();
		this.errorMessage = errorMessage;
	}

	
	
	public ProductFeedbackResponse(List<ProductFeedback> productFeedbacks, String sucessMessage)
	{
		super();
		this.productFeedbacks = productFeedbacks;
		this.sucessMessage = sucessMessage;
	}



	public ProductFeedbackResponse(ProductFeedback productFeedback, String sucessMessage)
	{
		super();
		this.productFeedback = productFeedback;
		this.sucessMessage = sucessMessage;
	}

	public ProductFeedbackResponse(ProductFeedback productFeedback, List<ProductFeedback> productFeedbacks,
			String sucessMessage, String errorMessage)
	{
		super();
		this.productFeedback = productFeedback;
		this.productFeedbacks = productFeedbacks;
		this.sucessMessage = sucessMessage;
		this.errorMessage = errorMessage;
	}

	public ProductFeedback getProductFeedback()
	{
		return productFeedback;
	}

	public void setProductFeedback(ProductFeedback productFeedback)
	{
		this.productFeedback = productFeedback;
	}

	public List<ProductFeedback> getProductFeedbacks()
	{
		return productFeedbacks;
	}

	public void setProductFeedbacks(List<ProductFeedback> productFeedbacks)
	{
		this.productFeedbacks = productFeedbacks;
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
