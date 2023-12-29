package com.tamilcreations.estorespringboot.discounts;

import java.util.List;

public class DiscountResponse
{
	private Discount discount;
	
	private List<Discount> discounts;
	
	private String sucessMessage;
	
	private String errorMessage;
	
	
	

	public DiscountResponse(List<Discount> discounts, String sucessMessage)
	{
		super();
		this.discounts = discounts;
		this.sucessMessage = sucessMessage;
	}

	public DiscountResponse(String errorMessage)
	{
		super();
		this.errorMessage = errorMessage;
	}

	public DiscountResponse(Discount discount, String sucessMessage)
	{
		super();
		this.discount = discount;
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

	public List<Discount> getDiscounts()
	{
		return discounts;
	}

	public void setDiscounts(List<Discount> discounts)
	{
		this.discounts = discounts;
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
