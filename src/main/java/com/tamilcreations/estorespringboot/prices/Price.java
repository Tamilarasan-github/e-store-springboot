package com.tamilcreations.estorespringboot.prices;

import java.util.Date;

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
@Component
@Entity
@Table(name="Prices")
public class Price
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "price_id")
	private Long priceId;
	
	@Column(name = "uuid")
	private String uuid;	
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	
	@Column(name = "total_price_per_unit")
	private double totalPricePerUnit;
	
	@Column(name = "tax_included")
	private boolean taxIncluded;
	
	@Column(name = "tax_type")
	private String taxType;
	@Column(name = "tax_percentage")
	private double taxPercentage;
	@Column(name = "currency")
	private String currency;
	@Column(name = "price_effective_date")
	private Date priceEffectiveDate;
	@Column(name = "price_term_date")
	private Date priceTermDate;
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
	public Long getPriceId()
	{
		return priceId;
	}
	public void setPriceId(Long priceId)
	{
		this.priceId = priceId;
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
	public Date getPriceEffectiveDate()
	{
		return priceEffectiveDate;
	}
	public void setPriceEffectiveDate(Date priceEffectiveDate)
	{
		this.priceEffectiveDate = priceEffectiveDate;
	}
	public Date getPriceTermDate()
	{
		return priceTermDate;
	}
	public void setPriceTermDate(Date priceTermDate)
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
	
	
	

}
