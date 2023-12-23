package com.tamilcreations.estorespringboot.generic;

import java.util.Base64;

import com.tamilcreations.estorespringboot.sellers.Seller;

import graphql.relay.DefaultConnectionCursor;

public class CursorUtils
{

    public static DefaultConnectionCursor encodedCursorFor(Long id) {
        // Implement a logic to generate a cursor for the seller.
        // It could be based on the seller's ID or any other unique attribute.
    	
        return new DefaultConnectionCursor(Base64.getEncoder().encodeToString(String.valueOf(id).getBytes()));
    }
    
    public static Long decodeCursor(String cursor) {
        // Implement a logic to generate a cursor for the seller.
        // It could be based on the seller's ID or any other unique attribute.
    	byte[] decodedBytes = Base64.getDecoder().decode(cursor);
    	Long decodedCursor = Long.parseLong(new String(decodedBytes));
    	return decodedCursor;
    }
}
