package com.tamilcreations.estorespringboot.discounts;

import java.util.List;

public class DiscountResponse
{
	private Discount discount;
	
	private List<Discount> discounts;
	
	private List<String> sucessMessage;
	
	private List<String> errorMessages;
	
	
	public DiscountResponse(Discount discount, List<String> sucessMessage)
	{
		super();
		this.discount = discount;
		this.sucessMessage = sucessMessage;
	}



	public DiscountResponse(List<Discount> discounts, List<String> sucessMessage, List<String> errorMessages)
	{
		super();
		this.discounts = discounts;
		this.sucessMessage = sucessMessage;
		this.errorMessages = errorMessages;
	}
	
	

	public DiscountResponse(List<Discount> discounts, List<String> sucessMessage)
	{
		super();
		this.discounts = discounts;
		this.sucessMessage = sucessMessage;
	}



	public Discount getDiscount()
	{
		return discount;
	}


	public void setDiscount(Discount discount)
	{
		this.discount = discount;
	}



	public DiscountResponse(List<String> errorMessages)
	{
		super();
		this.errorMessages = errorMessages;
	}



	public List<Discount> getDiscounts()
	{
		return discounts;
	}

	public void setDiscounts(List<Discount> discounts)
	{
		this.discounts = discounts;
	}

	public List<String> getSucessMessage()
	{
		return sucessMessage;
	}

	public void setSucessMessage(List<String> sucessMessage)
	{
		this.sucessMessage = sucessMessage;
	}

	public List<String> getErrorMessages()
	{
		return errorMessages;
	}

	public void setErrorMessages(List<String> errorMessages)
	{
		this.errorMessages = errorMessages;
	}
	
	
	
	
}
