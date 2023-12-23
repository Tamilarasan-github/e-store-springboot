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
@Setter
@Getter
@Component
@Entity
@Table(name="Discounts")
public class Discount
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "discount_id")
	private Long discountId;
	
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

}
