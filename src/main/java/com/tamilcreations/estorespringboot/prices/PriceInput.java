package com.tamilcreations.estorespringboot.prices;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.springframework.stereotype.Component;

import com.tamilcreations.estorespringboot.products.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PriceInput
{
	private String uuid;
	
	private Product product;

	private double totalPricePerUnit;

	private boolean taxIncluded;

	private String taxType;

	private double taxPercentage;

	private String currency;

	private String priceEffectiveDate;

	private String priceTermDate;

	private Date createdDate;

	private Date updatedDate;

	private String createdBy;

	private String updatedBy;

	private boolean deleteFlag;

	private String comments;
	
	

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

	public double getTotalPricePerUnit()
	{
		return totalPricePerUnit;
	}

	public void setTotalPricePerUnit(double totalPricePerUnit)
	{
		this.totalPricePerUnit = totalPricePerUnit;
	}

	public boolean isTaxIncluded()
	{
		return taxIncluded;
	}

	public void setTaxIncluded(boolean taxIncluded)
	{
		this.taxIncluded = taxIncluded;
	}

	public String getTaxType()
	{
		return taxType;
	}

	public void setTaxType(String taxType)
	{
		this.taxType = taxType;
	}

	public double getTaxPercentage()
	{
		return taxPercentage;
	}

	public void setTaxPercentage(double taxPercentage)
	{
		this.taxPercentage = taxPercentage;
	}

	public String getCurrency()
	{
		return currency;
	}

	public void setCurrency(String currency)
	{
		this.currency = currency;
	}

	public String getPriceEffectiveDate()
	{
		return priceEffectiveDate;
	}

	public void setPriceEffectiveDate(String priceEffectiveDate)
	{
		this.priceEffectiveDate = priceEffectiveDate;
	}

	public String getPriceTermDate()
	{
		return priceTermDate;
	}

	public void setPriceTermDate(String priceTermDate)
	{
		this.priceTermDate = priceTermDate;
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

	public Price toPrice()
	{

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Price price = new Price();
		try
		{
			price.setUuid(this.uuid);
			price.setProduct(this.product);
			price.setTotalPricePerUnit(this.totalPricePerUnit);
			price.setTaxIncluded(this.taxIncluded);
			price.setTaxType(this.taxType);
			price.setTaxPercentage(this.taxPercentage);
			price.setCurrency(this.currency);
			price.setPriceEffectiveDate(dateFormat.parse(this.priceEffectiveDate));
			price.setPriceTermDate(dateFormat.parse(this.priceTermDate));
			price.setCreatedDate(this.createdDate);
			price.setUpdatedDate(this.updatedDate);
			price.setCreatedBy(this.createdBy);
			price.setUpdatedBy(this.updatedBy);
			price.setDeleteFlag(this.deleteFlag);
			price.setComments(this.comments);
		} 
		catch (Exception e)
		{
			System.out.println(e);
		}

		return price;
	}

}
