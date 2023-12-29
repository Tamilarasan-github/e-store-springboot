package com.tamilcreations.estorespringboot.productStocks;

import java.util.List;

public class ProductStocksResponse
{
	private ProductStocks productStocks;
	
	private List<ProductStocks> productStocksList;
	
	private String sucessMessage;
	
	private String errorMessage;
	
	
	

	public ProductStocksResponse(List<ProductStocks> productStocksList, String sucessMessage)
	{
		super();
		this.productStocksList = productStocksList;
		this.sucessMessage = sucessMessage;
	}

	public ProductStocksResponse(String errorMessage)
	{
		super();
		this.errorMessage = errorMessage;
	}

	public ProductStocksResponse(ProductStocks productStocks, String sucessMessage)
	{
		super();
		this.productStocks = productStocks;
		this.sucessMessage = sucessMessage;
	}

	public ProductStocks getProductStocks()
	{
		return productStocks;
	}

	public void setProductStocks(ProductStocks productStocks)
	{
		this.productStocks = productStocks;
	}

	public List<ProductStocks> getProductStocksList()
	{
		return productStocksList;
	}

	public void setProductStocksList(List<ProductStocks> productStocksList)
	{
		this.productStocksList = productStocksList;
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
