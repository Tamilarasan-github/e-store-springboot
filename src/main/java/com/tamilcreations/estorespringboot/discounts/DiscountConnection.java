package com.tamilcreations.estorespringboot.discounts;

import java.util.List;

import graphql.relay.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class DiscountConnection
{
	private PageInfo pageInfo;
	private List<DiscountEdge> edges;
	
	public DiscountConnection(PageInfo pageInfo, List<DiscountEdge> edges)
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

	public List<DiscountEdge> getEdges()
	{
		return edges;
	}

	public void setEdges(List<DiscountEdge> edges)
	{
		this.edges = edges;
	}
	
	
	
	
}
