package com.tamilcreations.estorespringboot.prices;

import java.util.List;

public class PriceResponse
{
	private Price price;
	private List<Price> prices;
	private String errorMessage;
	private String successMessage;
	
	
	
	
	public PriceResponse(String errorMessage)
	{
		super();
		this.errorMessage = errorMessage;
	}
	
	public PriceResponse(Price price, String successMessage)
	{
		super();
		this.price = price;
		this.successMessage = successMessage;
	}
	
	
	public PriceResponse(List<Price> prices, String successMessage)
	{
		super();
		this.prices = prices;
		this.successMessage = successMessage;
	}
	
	
	
	public Price getPrice()
	{
		return price;
	}

	public void setPrice(Price price)
	{
		this.price = price;
	}

	public List<Price> getPrices()
	{
		return prices;
	}
	public void setPrices(List<Price> prices)
	{
		this.prices = prices;
	}
	public String getErrorMessage()
	{
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}
	public String getSuccessMessage()
	{
		return successMessage;
	}
	public void setSuccessMessage(String successMessage)
	{
		this.successMessage = successMessage;
	}
	
	
}
