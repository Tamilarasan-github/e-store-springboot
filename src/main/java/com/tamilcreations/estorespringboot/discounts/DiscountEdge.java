package com.tamilcreations.estorespringboot.discounts;

public class DiscountEdge
{
	private String cursor;
	private Discount node;
	
	public DiscountEdge(String cursor, Discount node)
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

	public Discount getNode()
	{
		return node;
	}

	public void setNode(Discount node)
	{
		this.node = node;
	}
	
	
}
