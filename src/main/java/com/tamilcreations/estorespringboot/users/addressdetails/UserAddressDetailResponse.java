package com.tamilcreations.estorespringboot.users.addressdetails;

import java.util.List;

public class UserAddressDetailResponse
{
	private UserAddressDetail userAddressDetail;
	
	private List<UserAddressDetail> userAddressDetails;
	
	private String sucessMessage;
	
	private String errorMessage;

	
	
	
	
	public UserAddressDetailResponse(String errorMessage)
	{
		super();
		this.errorMessage = errorMessage;
	}
	
	

	public UserAddressDetailResponse(List<UserAddressDetail> userAddressDetails, String sucessMessage)
	{
		super();
		this.userAddressDetails = userAddressDetails;
		this.sucessMessage = sucessMessage;
	}



	public UserAddressDetailResponse(UserAddressDetail userAddressDetail, String sucessMessage)
	{
		super();
		this.userAddressDetail = userAddressDetail;
		this.sucessMessage = sucessMessage;
	}



	public UserAddressDetail getUserAddressDetail()
	{
		return userAddressDetail;
	}



	public void setUserAddressDetail(UserAddressDetail userAddressDetail)
	{
		this.userAddressDetail = userAddressDetail;
	}



	public List<UserAddressDetail> getUserAddressDetails()
	{
		return userAddressDetails;
	}



	public void setUserAddressDetails(List<UserAddressDetail> userAddressDetails)
	{
		this.userAddressDetails = userAddressDetails;
	}



	public String getSucessMessage()
	{
		return sucessMessage;
	}



	public void setSucessMessage(String sucessMessage)
	{
		this.sucessMessage = sucessMessage;
	}



	public String getErrorMessage()
	{
		return errorMessage;
	}



	public void setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	
	
	
	
	

	
	
	
}
