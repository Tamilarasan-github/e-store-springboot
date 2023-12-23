package com.tamilcreations.estorespringboot.products;

import java.util.List;

import graphql.relay.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductConnection
{
	private PageInfo pageInfo;
	private List<ProductEdge> edges;
	
	public ProductConnection(PageInfo pageInfo, List<ProductEdge> edges)
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

	public List<ProductEdge> getEdges()
	{
		return edges;
	}

	public void setEdges(List<ProductEdge> edges)
	{
		this.edges = edges;
	}
	
	
	
	
}
