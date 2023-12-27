package com.tamilcreations.estorespringboot.generic;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	public Date convertStringToDateFormat(String dateStringFormat)
	{
		Date convertedDate = null;

		if (dateStringFormat != null || !dateStringFormat.isEmpty())
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			try
			{
				convertedDate = dateFormat.parse(dateStringFormat);
			} catch (ParseException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return convertedDate;
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
