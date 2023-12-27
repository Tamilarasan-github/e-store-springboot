package com.tamilcreations.estorespringboot.discounts;

import java.text.SimpleDateFormat;
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

import com.tamilcreations.estorespringboot.generic.GenericService;
import com.tamilcreations.estorespringboot.products.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class DiscountInput
{

	private Long discountId;
	
	private String uuid;	
	
	private Product product;
	
	private String discountType;
	
	private double discountAmount;
	
	private double discountPercentage;
	
	private String discountEffectiveDate;
	
	private String discountTermDate;
	
	private Date createdDate;
	
	private Date updatedDate;
	
	private String createdBy;
	
	private String updatedBy;
	
	private boolean deleteFlag;
	
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

	public String getDiscountEffectiveDate()
	{
		return discountEffectiveDate;
	}

	public void setDiscountEffectiveDate(String discountEffectiveDate)
	{
		this.discountEffectiveDate = discountEffectiveDate;
	}

	public String getDiscountTermDate()
	{
		return discountTermDate;
	}

	public void setDiscountTermDate(String discountTermDate)
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
	
	public static Discount toDiscount(DiscountInput discountInput) {		
        Discount discount = new Discount();
        discount.setUuid(discountInput.getUuid());
        discount.setProduct(discountInput.getProduct());
        discount.setDiscountType(discountInput.getDiscountType());
        discount.setDiscountAmount(discountInput.getDiscountAmount());
        discount.setDiscountPercentage(discountInput.getDiscountPercentage());
        discount.setDiscountEffectiveDate(new GenericService().convertStringToDateFormat(discountInput.getDiscountEffectiveDate()));
        discount.setDiscountTermDate(new GenericService().convertStringToDateFormat(discountInput.getDiscountTermDate()));
        discount.setCreatedBy(discountInput.getCreatedBy());
        discount.setUpdatedBy(discountInput.getUpdatedBy());
        discount.setDeleteFlag(discountInput.isDeleteFlag());
        discount.setComments(discountInput.getComments());

        return discount;
    }

}
