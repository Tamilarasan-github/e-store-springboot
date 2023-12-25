package com.tamilcreations.estorespringboot.generic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class GenericService
{
	public String getCurrentDateAndTime(String format)
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		return dtf.format(now);
	}
	
	public String generateUUID()
	{
		// Generate a random UUID
        UUID uuid = UUID.randomUUID();

        // Convert UUID to a string representation
        String uuidString = uuid.toString();
        
        return uuidString;

	}
}
