package com.tamilcreations.estorespringboot.products.feedbacks;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface ProductFeedbackRepo extends JpaRepository<ProductFeedback, Long>, JpaSpecificationExecutor<ProductFeedback>
{
	@Query(value="SELECT * FROM ProductFeedbacks d WHERE d.product_id = :productId AND d.delete_flag =0 ", nativeQuery=true)
	List<ProductFeedback> findProductFeedbackByProductId(long productId);
	
	@Query(value="SELECT * FROM ProductFeedbacks p WHERE p.uuid = :productFeedbackUuid AND p.delete_flag =0 ", nativeQuery=true)
	Optional<ProductFeedback> findProductFeedbackByProductFeedbackUuid(String productFeedbackUuid);
			
	@Query(value="SELECT * FROM ProductFeedbacks p WHERE CASE WHEN :after IS NOT NULL THEN product_feedback_id > :after AND product_id =:productId AND delete_flag =0 WHEN :before IS NOT NULL THEN product_feedback_id < :before AND delete_flag =0 AND product_id =:productId ELSE product_id =:productId AND delete_flag =0 END ORDER BY product_feedback_id ASC LIMIT :limit ", nativeQuery=true)
	List<ProductFeedback> findProductFeedbacksByProductId(@Param("productId") Long productId, @Param("limit") int limit, @Param("after") Long after, @Param("before") Long before);
	
	@Query(value="SELECT * FROM ProductFeedbacks p WHERE p.product_id = :productId AND p.delete_flag =0 ", nativeQuery=true)
	List<ProductFeedback> findProductFeedbacksByProductId(Long productId);
		
	@Query(value="SELECT COUNT(*) FROM ProductFeedbacks p WHERE p.product_feedback_id = :productFeedbackId AND p.delete_flag =0 ", nativeQuery=true)
	int getProductFeedbackRepliesCount(Long productFeedbackId);
	
}
