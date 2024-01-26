package com.tamilcreations.estorespringboot.users.addressdetails;

import com.tamilcreations.estorespringboot.users.User;
import com.tamilcreations.estorespringboot.utils.Utils;







public class UserAddressDetailInput
{
		
	private String uuid;	
	
	private User user;
		
	private String addressName;
		
	private String addressType;
	
	
	private boolean markAsDefaultFlag;
	
	
	private String addressLine1;
	
	
	private String addressLine2;
	
	
	private String addressLine3;
	
	
	private String city;
	
	
	private String state;
	
	
	private String country;
	
	
	private String zipCode;
	
	
	private String phoneNumber;
	
	
	private String notes;
	
	
	private String createdDate;
	
	
	private String updatedDate;
	
	
	private String createdBy;
	
	
	private String updatedBy;
	
	
	private boolean deleteFlag;
	
	
	
	public UserAddressDetailInput()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	
	public String getUuid()
	{
		return uuid;
	}

	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public String getAddressName()
	{
		return addressName;
	}

	public void setAddressName(String addressName)
	{
		this.addressName = addressName;
	}

	public String getAddressType()
	{
		return addressType;
	}

	public void setAddressType(String addressType)
	{
		this.addressType = addressType;
	}

	public boolean isMarkAsDefaultFlag()
	{
		return markAsDefaultFlag;
	}

	public void setMarkAsDefaultFlag(boolean markAsDefaultFlag)
	{
		this.markAsDefaultFlag = markAsDefaultFlag;
	}

	public String getAddressLine1()
	{
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1)
	{
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2()
	{
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2)
	{
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3()
	{
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3)
	{
		this.addressLine3 = addressLine3;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public String getZipCode()
	{
		return zipCode;
	}

	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public String getNotes()
	{
		return notes;
	}

	public void setNotes(String notes)
	{
		this.notes = notes;
	}

	public String getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(String createdDate)
	{
		this.createdDate = createdDate;
	}

	public String getUpdatedDate()
	{
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate)
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
	
	public UserAddressDetail toUserAddressDetail() {
        UserAddressDetail userAddressDetail = new UserAddressDetail();

        // Map fields from UserAddressDetailInput to UserAddressDetail
        userAddressDetail.setUuid(this.uuid);
        userAddressDetail.setUser(this.user);
        userAddressDetail.setAddressName(this.addressName);
        userAddressDetail.setAddressType(this.addressType);
        userAddressDetail.setMarkAsDefaultFlag(this.markAsDefaultFlag);
        userAddressDetail.setAddressLine1(this.addressLine1);
        userAddressDetail.setAddressLine2(this.addressLine2);
        userAddressDetail.setAddressLine3(this.addressLine3);
        userAddressDetail.setCity(this.city);
        userAddressDetail.setState(this.state);
        userAddressDetail.setCountry(this.country);
        userAddressDetail.setZipCode(this.zipCode);
        userAddressDetail.setPhoneNumber(this.phoneNumber);
        userAddressDetail.setNotes(this.notes);
        userAddressDetail.setCreatedDate(Utils.convertStringToDateFormat(this.createdDate));
        userAddressDetail.setUpdatedDate(Utils.convertStringToDateFormat(this.updatedDate));
        userAddressDetail.setCreatedBy(this.createdBy);
        userAddressDetail.setUpdatedBy(this.updatedBy);
        userAddressDetail.setDeleteFlag(this.deleteFlag);

        return userAddressDetail;
    }

}
