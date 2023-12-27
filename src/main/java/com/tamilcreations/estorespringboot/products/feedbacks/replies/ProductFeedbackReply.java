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
	@JoinColumn(name = "product_id")
	private Product productId;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User userId;
	
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

}
