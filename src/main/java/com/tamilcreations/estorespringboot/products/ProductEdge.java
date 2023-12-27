package com.tamilcreations.estorespringboot.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ProductEdge
{
	private String cursor;
	private Product node;
	
	public ProductEdge(String cursor, Product node)
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

	public Product getNode()
	{
		return node;
	}

	public void setNode(Product node)
	{
		this.node = node;
	}
	
	
}
