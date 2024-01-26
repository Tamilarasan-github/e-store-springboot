package com.tamilcreations.estorespringboot.products;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.tamilcreations.estorespringboot.security.JwtAuthenticationFilter;
import com.tamilcreations.estorespringboot.sellers.Seller;
import com.tamilcreations.estorespringboot.sellers.SellerConnection;
import com.tamilcreations.estorespringboot.sellers.SellerEdge;
import com.tamilcreations.estorespringboot.users.User;
import com.tamilcreations.estorespringboot.utils.CursorUtils;

import graphql.relay.DefaultPageInfo;
import graphql.relay.PageInfo;
import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ProductController
{
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private ProductService productService;
	
		
	@QueryMapping
	public ProductConnection getAllProducts(@Argument String productName, @Argument int first, @Argument @Nullable String after) {
		List<Product> products = null;
		int fetch = first + 1;
		products = productService.getAllProducts(productName, fetch, after);

        List<ProductEdge> edges = products.stream()
            .map(product -> new ProductEdge(CursorUtils.encodedCursorFor(product.getProductId()).getValue(), product))
            .collect(Collectors.toList());

        PageInfo pageInfo = new DefaultPageInfo(
        		CursorUtils.encodedCursorFor(products.get(0).getProductId()),  // startCursor
        		CursorUtils.encodedCursorFor(products.get(products.size() - 1).getProductId()),  // endCursor
                products.size() > first,  // hasNextPage
                after != null  // hasPreviousPage
            );
        
        return new ProductConnection(pageInfo, edges);
    }
	

	
	@QueryMapping
	public ProductConnection getAllActiveProducts(@Argument String productName, @Argument int first, @Argument @Nullable String after) {
		List<Product> products = null;
		int fetch = first + 1;
		products = productService.getAllActiveProducts(productName, fetch, after);

        List<ProductEdge> edges = products.stream()
            .map(product -> new ProductEdge(CursorUtils.encodedCursorFor(product.getProductId()).getValue(), product))
            .collect(Collectors.toList());

        PageInfo pageInfo = new DefaultPageInfo(
        		CursorUtils.encodedCursorFor(products.get(0).getProductId()),  // startCursor
        		CursorUtils.encodedCursorFor(products.get(products.size() - 1).getProductId()),  // endCursor
                products.size() > first,  // hasNextPage
                after != null  // hasPreviousPage
            );
        
        return new ProductConnection(pageInfo, edges);
    }
	
	@MutationMapping
	public Product addNewProduct( @Argument ProductInput productInput) throws Exception
	{
		User user = JwtAuthenticationFilter.getAuthorizationHeaderValueAndValidate(request);
		
		productInput.setCreatedDate(new Timestamp(new Date().getTime()));
		productInput.setUpdatedDate(null);
		
		Product newProduct = productInput.toProduct();
		return productService.addNewProduct(newProduct);
	}
	
}
