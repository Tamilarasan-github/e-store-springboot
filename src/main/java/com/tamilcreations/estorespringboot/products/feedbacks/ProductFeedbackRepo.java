package com.tamilcreations.estorespringboot.products.feedbacks;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;



public interface ProductFeedbackRepo extends JpaRepository<ProductFeedback, Long>, JpaSpecificationExecutor<ProductFeedback>
{
	@Query(value="SELECT * FROM ProductFeedbacks d WHERE d.product_id = :productId", nativeQuery=true)
	List<ProductFeedback> findProductFeedbackByProductId(long productId);
	
	@Query(value="SELECT * FROM ProductFeedbacks p WHERE p.uuid = :productFeedbackUuid ", nativeQuery=true)
	Optional<ProductFeedback> findProductFeedbackByProductFeedbackUuid(String productFeedbackUuid);
			
	@Query(value="SELECT * FROM ProductFeedbacks p WHERE p.product_id = :productId", nativeQuery=true)
	List<ProductFeedback> findProductFeedbacksByProductId(Long productId);
	
	@Query(value="SELECT COUNT(*) FROM ProductFeedbacks p WHERE p.product_feedback_id = :productFeedbackId", nativeQuery=true)
	int getProductFeedbackRepliesCount(Long productFeedbackId);
	
}
