package com.tamilcreations.estorespringboot.products.feedbacks.replies;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ProductFeedbackReplyRepo extends JpaRepository<ProductFeedbackReply, Long>, JpaSpecificationExecutor<ProductFeedbackReply>
{
	@Query(value="SELECT * FROM ProductFeedbackReplies p WHERE p.product_feedback_id = :productFeedbackId", nativeQuery=true)
	List<ProductFeedbackReply> findByProductFeedbackId(long productFeedbackId);
}
