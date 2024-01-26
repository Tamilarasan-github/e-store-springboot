package com.tamilcreations.estorespringboot.products.categories;

import java.util.List;

import graphql.relay.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ProductCategoryConnection
{
	private PageInfo pageInfo;
	private List<ProductCategoryEdge> edges;
	
	public ProductCategoryConnection(PageInfo pageInfo, List<ProductCategoryEdge> edges)
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

	public List<ProductCategoryEdge> getEdges()
	{
		return edges;
	}

	public void setEdges(List<ProductCategoryEdge> edges)
	{
		this.edges = edges;
	}
	
	
	
	
}
