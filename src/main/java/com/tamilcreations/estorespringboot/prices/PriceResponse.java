package com.tamilcreations.estorespringboot.prices;

import java.util.List;

public class PriceResponse
{
	private List<Price> prices;
	private String errorMessage;
	private String successMessage;
	
	
	
	
	public PriceResponse(String errorMessage)
	{
		super();
		this.prices = prices;
		this.errorMessage = errorMessage;
	}
	
	public PriceResponse(List<Price> prices, String successMessage)
	{
		super();
		this.prices = prices;
		this.successMessage = successMessage;
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
