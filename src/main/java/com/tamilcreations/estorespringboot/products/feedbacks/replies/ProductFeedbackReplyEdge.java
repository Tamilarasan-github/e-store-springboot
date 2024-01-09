package com.tamilcreations.estorespringboot.products.feedbacks.replies;

public class ProductFeedbackReplyEdge
{
	private String cursor;
	private ProductFeedbackReply node;
	
	public ProductFeedbackReplyEdge(String cursor, ProductFeedbackReply node)
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

	public ProductFeedbackReply getNode()
	{
		return node;
	}

	public void setNode(ProductFeedbackReply node)
	{
		this.node = node;
	}
	
	
}
