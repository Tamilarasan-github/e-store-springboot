package com.tamilcreations.estorespringboot.products.feedbacks.replies;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


public interface ProductFeedbackReplyRepo extends JpaRepository<ProductFeedbackReply, Long>, JpaSpecificationExecutor<ProductFeedbackReply>
{
	@Query(value="SELECT * FROM ProductFeedbackReplies p WHERE p.product_feedback_id = :productFeedbackReplyId", nativeQuery=true)
	List<ProductFeedbackReply> findByProductFeedbackReplyId(long productFeedbackReplyId);
	
	@Query(value="SELECT * FROM ProductFeedbackReplies p WHERE p.uuid = :productFeedbackReplyUuid ", nativeQuery=true)
	Optional<ProductFeedbackReply> findProductFeedbackReplyByProductFeedbackReplyUuid(String productFeedbackReplyUuid);
			
	@Query(value="SELECT * FROM ProductFeedbackReplies p WHERE p.product_feedback_id = :productFeedbackId", nativeQuery=true)
	List<ProductFeedbackReply> findProductFeedbackRepliesByProductFeedbackId(Long productFeedbackId);
	
	@Query(value="SELECT COUNT(*) FROM ProductFeedbackReplies p WHERE p.product_feedback_id = :productFeedbackReplyId", nativeQuery=true)
	int getProductFeedbackReplyRepliesCount(Long productFeedbackReplyId);
}
