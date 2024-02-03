package com.tamilcreations.estorespringboot.prices;

import java.util.List;

import graphql.relay.PageInfo;


public class PriceConnection
{
	private PageInfo pageInfo;
	private List<PriceEdge> edges;
	
	public PriceConnection(PageInfo pageInfo, List<PriceEdge> edges)
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

	public List<PriceEdge> getEdges()
	{
		return edges;
	}

	public void setEdges(List<PriceEdge> edges)
	{
		this.edges = edges;
	}
	
	
	
	
}
