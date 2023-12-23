package com.tamilcreations.estorespringboot.sellers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SellerEdge
{
	private String cursor;
	private Seller node;
	
	public SellerEdge(String cursor, Seller node)
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

	public Seller getNode()
	{
		return node;
	}

	public void setNode(Seller node)
	{
		this.node = node;
	}
	
	
}
