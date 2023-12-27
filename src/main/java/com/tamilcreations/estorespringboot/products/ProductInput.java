package com.tamilcreations.estorespringboot.products;

import java.sql.Timestamp;
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

import com.tamilcreations.estorespringboot.sellers.Seller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductInput
{
	
	
	private String uuid;
	
	private Seller seller;
	
	private String productCode;
	
	
	private String productName;
	
	
	private String productTitle;
	
	
	private String productCondition;
	
	
	private String description;
	
	
	private String productStatus;
	
	
	private int productSubCategoryId;
	
	
	private String brandName;
	
	
	private String imageUrl;
	
	
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

	public Seller getSeller()
	{
		return seller;
	}

	public void setSeller(Seller seller)
	{
		this.seller = seller;
	}

	public String getProductCode()
	{
		return productCode;
	}

	public void setProductCode(String productCode)
	{
		this.productCode = productCode;
	}

	public String getProductName()
	{
		return productName;
	}

	public void setProductName(String productName)
	{
		this.productName = productName;
	}

	public String getProductTitle()
	{
		return productTitle;
	}

	public void setProductTitle(String productTitle)
	{
		this.productTitle = productTitle;
	}

	public String getProductCondition()
	{
		return productCondition;
	}

	public void setProductCondition(String productCondition)
	{
		this.productCondition = productCondition;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getProductStatus()
	{
		return productStatus;
	}

	public void setProductStatus(String productStatus)
	{
		this.productStatus = productStatus;
	}

	public int getProductSubCategoryId()
	{
		return productSubCategoryId;
	}

	public void setProductCategoryId(int productSubCategoryId)
	{
		this.productSubCategoryId = productSubCategoryId;
	}

	public String getBrandName()
	{
		return brandName;
	}

	public void setBrandName(String brandName)
	{
		this.brandName = brandName;
	}

	public String getImageUrl()
	{
		return imageUrl;
	}

	public void setImageUrl(String imageUrl)
	{
		this.imageUrl = imageUrl;
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
	
	// Example method to convert ProductInput to Product
    public Product toProduct() {
        Product newProduct = new Product();
        // Set fields from ProductInput to Product
        newProduct.setUuid(UUID.randomUUID().toString()); // You can generate UUID or use the one from ProductInput
        newProduct.setSeller(this.getSeller());
        newProduct.setProductCode(this.getProductCode());
        newProduct.setProductName(this.getProductName());
        newProduct.setProductTitle(this.getProductTitle());
        newProduct.setProductCondition(this.getProductCondition());
        newProduct.setDescription(this.getDescription());
        newProduct.setProductStatus(this.getProductStatus());
        newProduct.setProductSubCategoryId(this.getProductSubCategoryId());
        newProduct.setBrandName(this.getBrandName());
        newProduct.setImageUrl(this.getImageUrl());
        newProduct.setCreatedDate(this.getCreatedDate());
        newProduct.setUpdatedDate(this.getUpdatedDate());
        newProduct.setCreatedBy(this.getCreatedBy());
        newProduct.setUpdatedBy(this.getUpdatedBy());
        newProduct.setDeleteFlag(false);
        newProduct.setComments(this.getComments());

        // You can set other fields as needed

        return newProduct;
    }

}
