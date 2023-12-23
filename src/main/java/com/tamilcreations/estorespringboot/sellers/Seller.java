package com.tamilcreations.estorespringboot.sellers;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.springframework.stereotype.Component;

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
@Table(name="Sellers")
public class Seller
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seller_id")
	private Long sellerId;
	
	
	@Column(name = "seller_name")
	private String sellerName;
	
	@Column(name = "seller_account_status")
	private String sellerAccountStatus;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "alternate_phone_number")
	private String alternatePhoneNumber;
	
	@Column(name = "email_id")
	private String emailId;
	
	@Column(name = "pan_number")
	private String panNumber;
	
	@Column(name = "gst_verification_number")
	private String gstVerificationNumber;
	
	@Column(name = "verification_status")
	private String verificationStatus;
	
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

	public Long getSellerId()
	{
		return sellerId;
	}

	public void setSellerId(Long sellerId)
	{
		this.sellerId = sellerId;
	}

	public String getSellerName()
	{
		return sellerName;
	}

	public void setSellerName(String sellerName)
	{
		this.sellerName = sellerName;
	}

	public String getSellerAccountStatus()
	{
		return sellerAccountStatus;
	}

	public void setSellerAccountStatus(String sellerAccountStatus)
	{
		this.sellerAccountStatus = sellerAccountStatus;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public String getAlternatePhoneNumber()
	{
		return alternatePhoneNumber;
	}

	public void setAlternatePhoneNumber(String alternatePhoneNumber)
	{
		this.alternatePhoneNumber = alternatePhoneNumber;
	}

	public String getEmailId()
	{
		return emailId;
	}

	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}

	public String getPanNumber()
	{
		return panNumber;
	}

	public void setPanNumber(String panNumber)
	{
		this.panNumber = panNumber;
	}

	public String getGstVerificationNumber()
	{
		return gstVerificationNumber;
	}

	public void setGstVerificationNumber(String gstVerificationNumber)
	{
		this.gstVerificationNumber = gstVerificationNumber;
	}

	public String getVerificationStatus()
	{
		return verificationStatus;
	}

	public void setVerificationStatus(String verificationStatus)
	{
		this.verificationStatus = verificationStatus;
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
