package com.tamilcreations.estorespringboot.users;

import com.tamilcreations.estorespringboot.products.categories.ProductCategory;

public class UserEdge
{
	private String cursor;
	private User node;
	
	public UserEdge(String cursor, User node)
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

	public User getNode()
	{
		return node;
	}

	public void setNode(User node)
	{
		this.node = node;
	}
	
	
}
