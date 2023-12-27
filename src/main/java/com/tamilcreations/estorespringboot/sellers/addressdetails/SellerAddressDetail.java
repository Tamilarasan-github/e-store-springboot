package com.tamilcreations.estorespringboot.sellers.addressdetails;

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
@Table(name="SellerAddressDetails")
public class SellerAddressDetail
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seller_address_details_Id")
	private Long sellerAddressDetails;
	
	@Column(name = "uuid")
	private String uuid;	
	
	@ManyToOne
	@JoinColumn(name = "seller_id")
	private Seller seller;
	
	
	@Column(name = "address_name")
	private String addressName;
	
	@Column(name = "address_type")
	private String addressType;
	
	@Column(name = "mark_as_default_flag")
	private boolean markAsDefaultFlag;
	
	@Column(name = "shop_name")
	private String shopName;
	
	@Column(name = "address_line_1")
	private String addressLine1;
	
	@Column(name = "address_line_2")
	private String addressLine2;
	@Column(name = "city")
	private String city;
	@Column(name = "state")
	private String state;
	@Column(name = "country")
	private String country;
	@Column(name = "zip_code")
	private String zipCode;
	@Column(name = "phone_number")
	private String phoneNumber;
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
