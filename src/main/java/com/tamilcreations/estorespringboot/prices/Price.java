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

}
