package com.tamilcreations.estorespringboot.sellers;

import java.util.List;

import graphql.relay.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SellerConnection
{
	private PageInfo pageInfo;
	private List<SellerEdge> edges;
	
	public SellerConnection(PageInfo pageInfo, List<SellerEdge> edges)
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

	public List<SellerEdge> getEdges()
	{
		return edges;
	}

	public void setEdges(List<SellerEdge> edges)
	{
		this.edges = edges;
	}
	
	
	
	
}
