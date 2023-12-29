package com.tamilcreations.estorespringboot.productStocks;

import java.util.Date;

import com.tamilcreations.estorespringboot.products.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ProductStocks")
public class ProductStocks
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stock_id")
	private Long stockId;

	@Column(name = "uuid")
	private String uuid;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "total_stocks")
	private int totalStocks;

	@Column(name = "available_stocks")
	private int availableStocks;

	@Column(name = "reserved_stocks")
	private int reservedStocks;

	@Column(name = "stocks_effective_date")
	private Date stocksEffectiveDate;

	@Column(name = "stocks_term_date")
	private Date stocksTermDate;
	
	@Column(name = "minimum_per_order")
	private int minimumPerOrder;

	@Column(name = "maximum_per_order")
	private int maximumPerOrder;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "delete_flag")
	private boolean deleteFlag;

	@Column(name = "comments")
	private String comments;

	

	
	public Long getStockId()
	{
		return stockId;
	}

	public void setStockId(Long stockId)
	{
		this.stockId = stockId;
	}

	public String getUuid()
	{
		return uuid;
	}

	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}

	public Product getProduct()
	{
		return product;
	}

	public void setProduct(Product product)
	{
		this.product = product;
	}

	public int getTotalStocks()
	{
		return totalStocks;
	}

	public void setTotalStocks(int totalStocks)
	{
		this.totalStocks = totalStocks;
	}

	public int getAvailableStocks()
	{
		return availableStocks;
	}

	public void setAvailableStocks(int availableStocks)
	{
		this.availableStocks = availableStocks;
	}

	public int getReservedStocks()
	{
		return reservedStocks;
	}

	public void setReservedStocks(int reservedStocks)
	{
		this.reservedStocks = reservedStocks;
	}

	public Date getStocksEffectiveDate()
	{
		return stocksEffectiveDate;
	}

	public void setStocksEffectiveDate(Date stocksEffectiveDate)
	{
		this.stocksEffectiveDate = stocksEffectiveDate;
	}

	public Date getStocksTermDate()
	{
		return stocksTermDate;
	}

	public void setStocksTermDate(Date stocksTermDate)
	{
		this.stocksTermDate = stocksTermDate;
	}

	public Date getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate()
	{
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate)
	{
		this.updatedDate = updatedDate;
	}

	public String getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	public String getUpdatedBy()
	{
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy)
	{
		this.updatedBy = updatedBy;
	}

	public boolean isDeleteFlag()
	{
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag)
	{
		this.deleteFlag = deleteFlag;
	}

	public String getComments()
	{
		return comments;
	}

	public void setComments(String comments)
	{
		this.comments = comments;
	}

	public int getMinimumPerOrder()
	{
		return minimumPerOrder;
	}

	public void setMinimumPerOrder(int minimumPerOrder)
	{
		this.minimumPerOrder = minimumPerOrder;
	}

	public int getMaximumPerOrder()
	{
		return maximumPerOrder;
	}

	public void setMaximumPerOrder(int maximumPerOrder)
	{
		this.maximumPerOrder = maximumPerOrder;
	}
}
