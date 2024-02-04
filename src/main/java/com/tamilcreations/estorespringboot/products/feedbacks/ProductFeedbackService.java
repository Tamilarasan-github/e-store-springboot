package com.tamilcreations.estorespringboot.products.feedbacks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tamilcreations.estorespringboot.productStocks.ProductStocks;
import com.tamilcreations.estorespringboot.productStocks.ProductStocksConnection;
import com.tamilcreations.estorespringboot.productStocks.ProductStocksEdge;
import com.tamilcreations.estorespringboot.products.Product;
import com.tamilcreations.estorespringboot.products.ProductService;
import com.tamilcreations.estorespringboot.utils.CursorUtils;

import graphql.relay.DefaultPageInfo;
import graphql.relay.PageInfo;
import io.micrometer.common.lang.Nullable;

@Service
public class ProductFeedbackService
{
	@Autowired
	ProductFeedbackRepo productFeedbackRepo;
	
	@Autowired
	ProductService productService;
	
	@Transactional
	public int getProductFeedbackRepliesCount(Long productFeedbackId)
	{
		return productFeedbackRepo.getProductFeedbackRepliesCount(productFeedbackId);
	}

	@Transactional
	public ProductFeedbackConnection getProductFeedbacksList(String productUuid,  int first, @Nullable String after, @Nullable String before) throws Exception
	{
					
			Product product= productService.findProductByProductUuid(productUuid);
			
			Long productId = product.getProductId();
			int fetch = first + 1;
					
			List<ProductFeedback> productFeedbacks = productFeedbackRepo
					.findProductFeedbacksByProductId(
					productId, fetch, CursorUtils.decodeCursor(after), CursorUtils.decodeCursor(before));	
			
			List<ProductFeedbackEdge> edges = productFeedbacks.stream().map(productStock->
				new ProductFeedbackEdge(CursorUtils.encodedCursorFor(productStock.getProductFeedbackId()).getValue(), productStock))
	        	.collect(Collectors.toList());
			
			 PageInfo pageInfo = new DefaultPageInfo(
		        		CursorUtils.encodedCursorFor(productFeedbacks.get(0).getProductFeedbackId()),  // startCursor
		        		CursorUtils.encodedCursorFor(productFeedbacks.get(productFeedbacks.size() - 1).getProductFeedbackId()),  // endCursor
		        		productFeedbacks.size() > first,  // hasNextPage
		                after != null  // hasPreviousPage
		            );
			return new ProductFeedbackConnection(pageInfo, edges);
		
	}

	@Transactional
	public ProductFeedbackResponse addNewProductFeedback(ProductFeedback productFeedback) throws Exception
	{
		String productUuid = productFeedback.getProduct().getUuid();
		Product product = productService.findProductByProductUuid(productUuid);

		Long productId = product.getProductId();

		productFeedback.getProduct().setProductId(productId);
		ProductFeedback savedProductFeedback = productFeedbackRepo.saveAndFlush(productFeedback);
		List<ProductFeedback> productFeedbackList = new ArrayList<ProductFeedback>();
		productFeedbackList.add(savedProductFeedback);
		
		return new ProductFeedbackResponse(productFeedbackList, "Thank you for your valuable feedback!");

	}
}
