package com.tamilcreations.estorespringboot.products.feedbacks.replies;

import java.util.List;

import graphql.relay.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ProductFeedbackReplyConnection
{
	private PageInfo pageInfo;
	private List<ProductFeedbackReplyEdge> edges;
	
	public ProductFeedbackReplyConnection(PageInfo pageInfo, List<ProductFeedbackReplyEdge> edges)
	{
		super();
		this.pageInfo = pageInfo;
		this.edges = edges;
	}

	public PageInfo getPageInfo()
	{
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo)
	{
		this.pageInfo = pageInfo;
	}

	public List<ProductFeedbackReplyEdge> getEdges()
	{
		return edges;
	}

	public void setEdges(List<ProductFeedbackReplyEdge> edges)
	{
		this.edges = edges;
	}
	
	
	
	
}
