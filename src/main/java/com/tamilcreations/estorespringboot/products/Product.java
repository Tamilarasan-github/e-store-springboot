package com.tamilcreations.estorespringboot.products;

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

import com.tamilcreations.estorespringboot.sellers.Seller;

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
@Table(name="Products")
public class Product
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long productId;
	
	@ManyToOne
	@JoinColumn(name = "seller_id")
	private Seller seller;
	
	
	
	@Column(name = "product_code")
	private String productCode;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "product_title")
	private String productTitle;
	
	@Column(name = "product_condition")
	private String productCondition;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "product_status")
	private String productStatus;
	
	@Column(name = "product_category_id")
	private Long productCategoryId;
	
	@Column(name = "brand_name")
	private String brandName;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(name = "availability_status")
	private boolean availabilityStatus;
	
	@Column(name = "stock_quantity")
	private int stockQuantity;
	
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

	public Long getProductId()
	{
		return productId;
	}

	public void setProductId(Long productId)
	{
		this.productId = productId;
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

	public Long getProductCategoryId()
	{
		return productCategoryId;
	}

	public void setProductCategoryId(Long productCategoryId)
	{
		this.productCategoryId = productCategoryId;
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

	public boolean isAvailabilityStatus()
	{
		return availabilityStatus;
	}

	public void setAvailabilityStatus(boolean availabilityStatus)
	{
		this.availabilityStatus = availabilityStatus;
	}

	public int getStockQuantity()
	{
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity)
	{
		this.stockQuantity = stockQuantity;
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
