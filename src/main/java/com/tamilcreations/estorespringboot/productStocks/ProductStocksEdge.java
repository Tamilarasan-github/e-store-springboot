package com.tamilcreations.estorespringboot.productStocks;

public class ProductStocksEdge
{
	private String cursor;
	private ProductStocks node;
	
	public ProductStocksEdge(String cursor, ProductStocks node)
	{
		super();
		this.cursor = cursor;
		this.node = node;
	}

	public String getCursor()
	{
		return cursor;
	}

	public void setCursor(String cursor)
	{
		this.cursor = cursor;
	}

	public ProductStocks getNode()
	{
		return node;
	}

	public void setNode(ProductStocks node)
	{
		this.node = node;
	}
	
	
}
