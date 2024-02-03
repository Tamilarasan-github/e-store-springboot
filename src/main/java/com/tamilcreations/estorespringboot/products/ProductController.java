package com.tamilcreations.estorespringboot.products;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import com.tamilcreations.estorespringboot.sellers.Seller;
import com.tamilcreations.estorespringboot.sellers.SellerService;
import com.tamilcreations.estorespringboot.users.User;
import com.tamilcreations.estorespringboot.users.UserService;
import com.tamilcreations.estorespringboot.utils.CursorUtils;
import com.tamilcreations.estorespringboot.utils.GenericService;
import com.tamilcreations.estorespringboot.utils.Roles;
import com.tamilcreations.estorespringboot.utils.Utils;

import graphql.relay.DefaultPageInfo;
import graphql.relay.PageInfo;
import io.jsonwebtoken.Claims;
import io.micrometer.common.lang.Nullable;

@Controller
public class ProductController
{
	@Autowired
	private GenericService genericService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private UserService userService;
	
	@Secured(value= {Roles.SELLER_ALL_MODULES_FULL_ACCESS, Roles.SELLER_PRODUCT_READ_ACCESS, Roles.SELLER_PRODUCT_WRITE_ACCESS })	
	@QueryMapping
	public ProductConnection getAllProductsForLoggedInSeller(@Argument String productName, @Argument int first, @Argument @Nullable String after, @Argument @Nullable String before) throws Exception {
		
		Claims claims = genericService.getClaims();
		String loggedInUser = claims.get("phoneNumber").toString();

		User user = userService.getUserByPhoneNumber(loggedInUser);
		Long loggedInSellerId = user.getSellerId();
		
		List<Product> products = null;
		int fetch = first + 1;
		products = productService.getAllProducts(loggedInSellerId, productName, fetch, after, before);

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
	
	@Secured(value= { Roles.CUSTOMER_SUPPORT_READ_ACCESS, Roles.CUSTOMER_SUPPORT_WRITE_ACCESS, Roles.ADMIN, Roles.SUPER_ADMIN })	
	@QueryMapping
	public ProductConnection getAllProductsBySeller(@Argument String sellerUuid, @Argument String productName, @Argument int first, @Argument @Nullable String after, @Argument @Nullable String before) throws Exception {
		Claims claims = genericService.getClaims();
		String loggedInUser = claims.get("phoneNumber").toString();

		User user = userService.getUserByPhoneNumber(loggedInUser);
		Long loggedInSellerId = user.getSellerId();
		
		Long sellerId = sellerService.getSellerIdBySellerUuid(sellerUuid);
		
		List<Product> products = null;
		int fetch = first + 1;
		products = productService.getAllProducts(sellerId, productName, fetch, after, before);

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
	

	@Secured(value= {Roles.SELLER_ALL_MODULES_FULL_ACCESS, Roles.SELLER_PRODUCT_READ_ACCESS, Roles.SELLER_PRODUCT_WRITE_ACCESS })
	@QueryMapping
	public ProductConnection getProductsByStatusForLoggedInSeller(@Argument String productName, @Argument String status, @Argument int first, @Argument @Nullable String after,  @Argument @Nullable String before) throws Exception {
		
		Claims claims = genericService.getClaims();
		String loggedInUser = claims.get("phoneNumber").toString();

		User user = userService.getUserByPhoneNumber(loggedInUser);
		Long loggedInSellerId = user.getSellerId();
		
		List<Product> products = null;
		int fetch = first + 1;
		products = productService.getProductsByStatus(loggedInSellerId, productName, status, fetch, after, before);

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
	
	@Secured(value= {Roles.SELLER_ALL_MODULES_FULL_ACCESS, Roles.SELLER_PRODUCT_WRITE_ACCESS })
	@MutationMapping
	public Product addNewProductForLoggedInSeller(@Argument ProductInput productInput) throws Exception
	{
		//User user = JwtAuthenticationFilter.getAuthorizationHeaderValueAndValidate(request);
		Claims claims = genericService.getClaims();
		String loggedInUser = claims.get("phoneNumber").toString();

		User user = userService.getUserByPhoneNumber(loggedInUser);
		Long loggedInSellerId = user.getSellerId();
		
		Long sellerIdInTheRequest = productInput.getSeller().getSellerId();
		
		if(loggedInSellerId.compareTo(sellerIdInTheRequest) == 0)
		{
			Product newProduct = Utils.applyNewCreationDefaultValues(productInput, loggedInUser).toProduct();
		
			return productService.addNewProduct(newProduct);
		}
		else
		{
			throw new Exception("You do not have permission to add new Products for other sellers.");
		}
	}
	
	@Secured(value= {Roles.CUSTOMER_SUPPORT_WRITE_ACCESS, Roles.ADMIN, Roles.SUPER_ADMIN})
	@MutationMapping
	public Product addNewProductOnBehalfOfSeller(@Argument String sellerUuid, @Argument ProductInput productInput) throws Exception
	{
		//User user = JwtAuthenticationFilter.getAuthorizationHeaderValueAndValidate(request);
		Claims claims = genericService.getClaims();
		String loggedInUser = claims.get("phoneNumber").toString();

		User user = userService.getUserByPhoneNumber(loggedInUser);
		Long loggedInSellerId = user.getSellerId();
		
		Long sellerIdInTheRequest = productInput.getSeller().getSellerId();
		
		Seller seller = sellerService.getSellerBySellerUuid(sellerUuid);
		productInput.setSeller(seller);
		
		Product newProduct = Utils.applyNewCreationDefaultValues(productInput, loggedInUser).toProduct();
		
		return productService.addNewProduct(newProduct);
	}
	
}
