package com.tamilcreations.estorespringboot.discounts;

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
@Component
@Entity
@Table(name="Discounts")
public class Discount
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "discount_id")
	private Long discountId;
	
	@Column(name = "uuid")
	private String uuid;	
	
	@ManyToOne
	@JoinColumn(name = "product_id")	
	private Product product;
	
	@Column(name = "discount_type")
	private String discountType;
	
	@Column(name = "discount_amount")
	private double discountAmount;
	
	@Column(name = "discount_percentage")
	private double discountPercentage;
	
	@Column(name = "discount_effective_date")
	private Date discountEffectiveDate;
	@Column(name = "discount_term_date")
	private Date discountTermDate;
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
	public Long getDiscountId()
	{
		return discountId;
	}
	public void setDiscountId(Long discountId)
	{
		this.discountId = discountId;
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
	public String getDiscountType()
	{
		return discountType;
	}
	public void setDiscountType(String discountType)
	{
		this.discountType = discountType;
	}
	public double getDiscountAmount()
	{
		return discountAmount;
	}
	public void setDiscountAmount(double discountAmount)
	{
		this.discountAmount = discountAmount;
	}
	public double getDiscountPercentage()
	{
		return discountPercentage;
	}
	public void setDiscountPercentage(double discountPercentage)
	{
		this.discountPercentage = discountPercentage;
	}
	public Date getDiscountEffectiveDate()
	{
		return discountEffectiveDate;
	}
	public void setDiscountEffectiveDate(Date discountEffectiveDate)
	{
		this.discountEffectiveDate = discountEffectiveDate;
	}
	public Date getDiscountTermDate()
	{
		return discountTermDate;
	}
	public void setDiscountTermDate(Date discountTermDate)
	{
		this.discountTermDate = discountTermDate;
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
