package com.tamilcreations.estorespringboot.products.categories;

public class ProductCategoryEdge
{
	private String cursor;
	private ProductCategory node;
	
	public ProductCategoryEdge(String cursor, ProductCategory node)
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

	public ProductCategory getNode()
	{
		return node;
	}

	public void setNode(ProductCategory node)
	{
		this.node = node;
	}
	
	
}
