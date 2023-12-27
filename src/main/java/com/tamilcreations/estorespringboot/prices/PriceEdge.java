package com.tamilcreations.estorespringboot.prices;

public class PriceEdge
{
	private String cursor;
	private Price node;
	
	public PriceEdge(String cursor, Price node)
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

	public Price getNode()
	{
		return node;
	}

	public void setNode(Price node)
	{
		this.node = node;
	}
	
	
}
