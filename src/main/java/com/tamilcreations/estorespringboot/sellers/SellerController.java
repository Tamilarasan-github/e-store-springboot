package com.tamilcreations.estorespringboot.sellers;




import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.tamilcreations.estorespringboot.utils.CursorUtils;

import graphql.relay.DefaultConnectionCursor;
import graphql.relay.DefaultPageInfo;
import graphql.relay.PageInfo;
import io.micrometer.common.lang.Nullable;

@Controller
public class SellerController
{
	@Autowired
	SellerRepo sellerRepo;
	
	@Autowired
	SellerService sellerService;
	
	@QueryMapping
	public Seller getSeller(@Argument long sellerId)
	{
		Seller seller = sellerService.getSeller(sellerId);
		//new SellerEdge("",seller);
		//Connection<Seller> = new SellerConnection();
		return seller; 
	}
	
	@QueryMapping
	public SellerConnection getSellers( @Argument int first, @Argument @Nullable String after) {
		List<Seller> sellers = null;
		int fetch = first + 1;
		if(after==null)
		{
			sellers = sellerRepo.findSellers(fetch);
		}
		else
		{
			sellers = sellerRepo.findSellers(fetch, CursorUtils.decodeCursor(after));
		}

        List<SellerEdge> edges = sellers.stream()
            .map(seller -> new SellerEdge(CursorUtils.encodedCursorFor(seller.getSellerId()).getValue(), seller))
            .collect(Collectors.toList());

        PageInfo pageInfo = new DefaultPageInfo(
        		CursorUtils.encodedCursorFor(sellers.get(0).getSellerId()),  // startCursor
        		CursorUtils.encodedCursorFor(sellers.get(sellers.size() - 1).getSellerId()),  // endCursor
                sellers.size() > first,  // hasNextPage
                after != null  // hasPreviousPage
            );
        
        return new SellerConnection(pageInfo, edges);
    }
	


}
