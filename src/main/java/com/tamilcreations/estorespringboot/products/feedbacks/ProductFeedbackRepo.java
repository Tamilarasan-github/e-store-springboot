package com.tamilcreations.estorespringboot.products.feedbacks;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ProductFeedbackRepo extends JpaRepository<ProductFeedback, Long>, JpaSpecificationExecutor<ProductFeedback>
{
	@Query(value="SELECT * FROM ProductFeedbacks d WHERE d.product_id = :productId", nativeQuery=true)
	List<ProductFeedback> findByProductId(long productId);
}
