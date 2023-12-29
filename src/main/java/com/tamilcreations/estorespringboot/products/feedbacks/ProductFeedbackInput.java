package com.tamilcreations.estorespringboot.products.feedbacks;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import org.springframework.stereotype.Component;

import com.tamilcreations.estorespringboot.products.Product;
import com.tamilcreations.estorespringboot.products.feedbacks.replies.ProductFeedbackReply;
import com.tamilcreations.estorespringboot.users.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




public class ProductFeedbackInput
{
	private Long productFeedbackId;
	
	private String uuid;	
	
	private Product product;
	
	private User user;
	
	private int productRating;
	
	private String reviewComment;
	
	private int upVotes;
	
	private int downVotes;
	
	private int feedbackRepliesCount;
		
	private Date createdDate;
	
	private Date updatedDate;
	
	private String createdBy;
	
	private String updatedBy;
	
	private boolean deleteFlag;
	
	
	
	public Long getProductFeedbackId()
	{
		return productFeedbackId;
	}



	public void setProductFeedbackId(Long productFeedbackId)
	{
		this.productFeedbackId = productFeedbackId;
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



	public User getUser()
	{
		return user;
	}



	public void setUser(User user)
	{
		this.user = user;
	}



	public int getProductRating()
	{
		return productRating;
	}



	public void setProductRating(int productRating)
	{
		this.productRating = productRating;
	}



	public String getReviewComment()
	{
		return reviewComment;
	}



	public void setReviewComment(String reviewComment)
	{
		this.reviewComment = reviewComment;
	}



	public int getUpVotes()
	{
		return upVotes;
	}



	public void setUpVotes(int upVotes)
	{
		this.upVotes = upVotes;
	}



	public int getDownVotes()
	{
		return downVotes;
	}



	public void setDownVotes(int downVotes)
	{
		this.downVotes = downVotes;
	}

	public int getFeedbackRepliesCount()
	{
		return feedbackRepliesCount;
	}

	public void setFeedbackRepliesCount(int feedbackRepliesCount)
	{
		this.feedbackRepliesCount = feedbackRepliesCount;
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



	public ProductFeedback toProductFeedback() {
        ProductFeedback productFeedback = new ProductFeedback();
        productFeedback.setUuid(this.getUuid());
        productFeedback.setProduct(this.getProduct()); 
        productFeedback.setUser(this.getUser()); 
        productFeedback.setProductRating(this.getProductRating());
        productFeedback.setReviewComment(this.getReviewComment());
        productFeedback.setUpVotes(this.getUpVotes());
        productFeedback.setDownVotes(this.getDownVotes());
        productFeedback.setCreatedDate(this.getCreatedDate());
        productFeedback.setUpdatedDate(this.getUpdatedDate());
        productFeedback.setCreatedBy(this.getCreatedBy());
        productFeedback.setUpdatedBy(this.getUpdatedBy());
        productFeedback.setDeleteFlag(this.isDeleteFlag());

        return productFeedback;
    }

}
