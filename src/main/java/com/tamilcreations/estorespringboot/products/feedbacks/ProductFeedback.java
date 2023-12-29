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
import jakarta.persistence.Transient;

import org.springframework.stereotype.Component;

import com.tamilcreations.estorespringboot.products.Product;
import com.tamilcreations.estorespringboot.products.feedbacks.replies.ProductFeedbackReply;
import com.tamilcreations.estorespringboot.users.User;

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
@Table(name="ProductFeedbacks")
public class ProductFeedback
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_feedback_id")
	private Long productFeedbackId;
	
	@Column(name = "uuid")
	private String uuid;	
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "product_rating")
	private int productRating;
	
	@Column(name = "review_comment")
	private String reviewComment;
	
	@Column(name = "up_votes")
	private int upVotes;
	
	@Column(name = "down_votes")
	private int downVotes;
	
	@Transient
	private int feedbackRepliesCount;
		
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
	
	
	

}
