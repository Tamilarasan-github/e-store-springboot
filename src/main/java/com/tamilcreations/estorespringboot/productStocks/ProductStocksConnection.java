package com.tamilcreations.estorespringboot.productStocks;

import java.util.List;

import graphql.relay.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ProductStocksConnection
{
	private PageInfo pageInfo;
	private List<ProductStocksEdge> edges;
	
	public ProductStocksConnection(PageInfo pageInfo, List<ProductStocksEdge> edges)
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

	public List<ProductStocksEdge> getEdges()
	{
		return edges;
	}

	public void setEdges(List<ProductStocksEdge> edges)
	{
		this.edges = edges;
	}
	
	
	
	
}
