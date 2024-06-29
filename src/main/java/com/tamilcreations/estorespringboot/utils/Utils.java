package com.tamilcreations.estorespringboot.utils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class Utils
{
	public static String getCurrentDateAndTime(String format)
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		return dtf.format(now);
	}
	
	public static String generateCorrelationId() {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        return correlationId;
    }

	public static Date convertStringToDateFormat(String dateStringFormat)
	{
		Date convertedDate = null;

		if (dateStringFormat != null && !dateStringFormat.isEmpty())
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
	
	public static <T> T applyNewCreationDefaultValues(T anyObject, String userIdentity) {
        Date currentDate = new Date();
        
        try {
        	  anyObject.getClass().getMethod("setUuid", String.class).invoke(anyObject, UUID.randomUUID().toString());
        	  anyObject.getClass().getMethod("setCreatedBy", String.class).invoke(anyObject, userIdentity);
        	  anyObject.getClass().getMethod("setCreatedDate", Date.class).invoke(anyObject, currentDate);
              anyObject.getClass().getMethod("setUpdatedBy", String.class).invoke(anyObject, (String)null);
              anyObject.getClass().getMethod("setUpdatedDate", Date.class).invoke(anyObject, (Date) null);
              anyObject.getClass().getMethod("setDeleteFlag", boolean.class).invoke(anyObject, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return anyObject;
    }
	
	public static <T> T applyUpdateDefaultValues(T anyObject,  String userIdentity) {
        Date currentDate = new Date();
        
        try {
      	  	anyObject.getClass().getMethod("setUpdatedBy", String.class).invoke(anyObject, userIdentity);
            anyObject.getClass().getMethod("setUpdatedDate", Date.class).invoke(anyObject, currentDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return anyObject;
    }
	
	public static <T> T applySoftDelete(T anyObject,  String userIdentity) {
        Date currentDate = new Date();
        
        try {
      	  	anyObject.getClass().getMethod("setUpdatedBy", String.class).invoke(anyObject, userIdentity);
            anyObject.getClass().getMethod("setUpdatedDate", Date.class).invoke(anyObject, currentDate);
            anyObject.getClass().getMethod("setDeleteFlag", boolean.class).invoke(anyObject, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return anyObject;
    }
	
	public static File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
		
		String originalFilename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	    System.out.println("MultipartFile File size: " + multipartFile.getSize());

	    // Get the absolute path of the resources folder
	    //ClassPathResource resource = new ClassPathResource("static"); // assuming static is your resources folder
	    String uploadDir = "src/main/resources/";

//	    // Ensure the upload directory exists
//	    if (!uploadDir.exists()) {
//	    	uploadDir.mkdirs(); // Create the directory if it doesn't exist
//	    }

	    // Create the file within the specified directory
	    File file = new File("src/main/resources/", originalFilename);

	    // Use FileCopyUtils to copy the content of the MultipartFile to the created file
	    FileCopyUtils.copy(multipartFile.getBytes(), file);

	    System.out.println("After conversion, File size: " + file.length());
	    return file;
	}
}
