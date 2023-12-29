package com.tamilcreations.estorespringboot.products.feedbacks;

import java.util.List;

import graphql.relay.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ProductFeedbackConnection
{
	private PageInfo pageInfo;
	private List<ProductFeedbackEdge> edges;
	
	public ProductFeedbackConnection(PageInfo pageInfo, List<ProductFeedbackEdge> edges)
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

	public List<ProductFeedbackEdge> getEdges()
	{
		return edges;
	}

	public void setEdges(List<ProductFeedbackEdge> edges)
	{
		this.edges = edges;
	}
	
	
	
	
}
