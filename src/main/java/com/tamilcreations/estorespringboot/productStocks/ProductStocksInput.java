package com.tamilcreations.estorespringboot.productStocks;

import java.util.Date;

import com.tamilcreations.estorespringboot.generic.GenericService;
import com.tamilcreations.estorespringboot.products.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ProductStocksInput
{
	
	private Long stockId;

	
	private String uuid;

	
	private Product product;

	
	private int totalStocks;

	
	private int availableStocks;

	
	private int reservedStocks;

	
	private String stocksEffectiveDate;

	
	private String stocksTermDate;
	
	
	private int minimumPerOrder;

	
	private int maximumPerOrder;

	
	private Date createdDate;

	
	private Date updatedDate;

	
	private String createdBy;

	
	private String updatedBy;

	
	private boolean deleteFlag;

	
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

	public String getStocksEffectiveDate()
	{
		return stocksEffectiveDate;
	}

	public void setStocksEffectiveDate(String stocksEffectiveDate)
	{
		this.stocksEffectiveDate = stocksEffectiveDate;
	}

	public String getStocksTermDate()
	{
		return stocksTermDate;
	}

	public void setStocksTermDate(String stocksTermDate)
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
	
	public ProductStocks toProductStocks() {
        ProductStocks productStocks = new ProductStocks();
        productStocks.setUuid(this.getUuid());
        productStocks.setProduct(this.getProduct());
        productStocks.setTotalStocks(this.getTotalStocks());
        productStocks.setAvailableStocks(this.getAvailableStocks());
        productStocks.setReservedStocks(this.getReservedStocks());
        productStocks.setStocksEffectiveDate(new GenericService().convertStringToDateFormat(this.getStocksEffectiveDate()));
        productStocks.setStocksTermDate(new GenericService().convertStringToDateFormat(this.getStocksTermDate()));
        productStocks.setCreatedDate(this.createdDate);
        productStocks.setUpdatedDate(this.updatedDate);
        productStocks.setCreatedBy(this.getCreatedBy());
        productStocks.setUpdatedBy(this.getUpdatedBy());
        productStocks.setDeleteFlag(this.isDeleteFlag());
        productStocks.setComments(this.getComments());
        productStocks.setMinimumPerOrder(this.getMinimumPerOrder());
        productStocks.setMaximumPerOrder(this.getMaximumPerOrder());

        // Set other fields as needed...

        return productStocks;
    }
}
