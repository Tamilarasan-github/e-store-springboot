package com.tamilcreations.estorespringboot.products.categories.subcategories;

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

import com.tamilcreations.estorespringboot.products.categories.ProductCategory;

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
@Table(name = "ProductSubCategories")
public class ProductSubCategory
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sub_category_id")
	private Long subCategoryId;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private ProductCategory productCategory;
	
	@Column(name = "sub_category_name")
	private String subCategoryName;
	@Column(name = "sub_category_status")
	private String subCategoryStatus;
	@Column(name = "sub_category_description")
	private String subCategoryDescription;
	@Column(name = "sub_category_image_url")
	private String subCategoryImageUrl;
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

}
