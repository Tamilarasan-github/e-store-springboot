package com.tamilcreations.estorespringboot.products.feedbacks;

public class ProductFeedbackEdge
{
	private String cursor;
	private ProductFeedback node;
	
	public ProductFeedbackEdge(String cursor, ProductFeedback node)
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

	public ProductFeedback getNode()
	{
		return node;
	}

	public void setNode(ProductFeedback node)
	{
		this.node = node;
	}
	
	
}
