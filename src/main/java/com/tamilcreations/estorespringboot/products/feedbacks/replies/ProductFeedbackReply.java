package com.tamilcreations.estorespringboot.products.feedbacks.replies;

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
import com.tamilcreations.estorespringboot.products.feedbacks.ProductFeedback;
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
@Table(name="ProductFeedbackReplies")
public class ProductFeedbackReply
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_feedback_reply_id")
	private Long productFeedbackReplyId;
	
	@Column(name = "uuid")
	private String uuid;	
	
	@ManyToOne
	@JoinColumn(name = "product_feedback_id")
	private ProductFeedback productFeedback;
		
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "review_comment")
	private String reviewComment;
	
	
	@Column(name = "up_votes")
	private int upVotes;
	
	@Column(name = "down_votes")
	private int downVotes;
	
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
	
	public Long getProductFeedbackReplyId()
	{
		return productFeedbackReplyId;
	}
	public void setProductFeedbackReplyId(Long productFeedbackReplyId)
	{
		this.productFeedbackReplyId = productFeedbackReplyId;
	}
	public String getUuid()
	{
		return uuid;
	}
	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}
	public ProductFeedback getProductFeedback()
	{
		return productFeedback;
	}
	public void setProductFeedback(ProductFeedback productFeedback)
	{
		this.productFeedback = productFeedback;
	}
	public User getUser()
	{
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
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
