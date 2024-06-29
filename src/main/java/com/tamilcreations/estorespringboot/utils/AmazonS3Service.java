package com.tamilcreations.estorespringboot.utils;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Service
public class AmazonS3Service
{
			
	@Value("${aws.access.key}")
	private String awsAccessKey;
	
	@Value("${aws.secret.key}")
	private  String awsSecretKey;
	
	@Value("${aws.s3.bucket.name}")
	private  String awsS3BucketName;
	
	@Value("${aws.s3.product.display.pic.folder}")
	private  String awsS3ProductDisplayPicFolder;
	
	@Value("${aws.s3.product.pic.folder}")
	private  String awsS3ProductPicFolder;
	
	@Value("${aws.s3.user.display.pic.folder}")
	private  String awsS3UserDisplayPicFolder;
	
		
	


	public String getAwsAccessKey()
	{
		return awsAccessKey;
	}

	public void setAwsAccessKey(String awsAccessKey)
	{
		this.awsAccessKey = awsAccessKey;
	}

	public String getAwsSecretKey()
	{
		return awsSecretKey;
	}

	public void setAwsSecretKey(String awsSecretKey)
	{
		this.awsSecretKey = awsSecretKey;
	}

	public String getAwsS3BucketName()
	{
		return awsS3BucketName;
	}

	public void setAwsS3BucketName(String awsS3BucketName)
	{
		this.awsS3BucketName = awsS3BucketName;
	}

	public String getAwsS3ProductDisplayPicFolder()
	{
		return awsS3ProductDisplayPicFolder;
	}

	public void setAwsS3ProductDisplayPicFolder(String awsS3ProductDisplayPicFolder)
	{
		this.awsS3ProductDisplayPicFolder = awsS3ProductDisplayPicFolder;
	}

	public String getAwsS3ProductPicFolder()
	{
		return awsS3ProductPicFolder;
	}

	public void setAwsS3ProductPicFolder(String awsS3ProductPicFolder)
	{
		this.awsS3ProductPicFolder = awsS3ProductPicFolder;
	}

	public String getAwsS3UserDisplayPicFolder()
	{
		return awsS3UserDisplayPicFolder;
	}

	public void setAwsS3UserDisplayPicFolder(String awsS3UserDisplayPicFolder)
	{
		this.awsS3UserDisplayPicFolder = awsS3UserDisplayPicFolder;
	}

	public String uploadProductDisplayPicToAwsS3Bucket(MultipartFile file, String dynamicFolderName) throws IOException
	{
		return uploadToAwsS3Bucket(file, awsS3BucketName, awsS3ProductDisplayPicFolder, dynamicFolderName);
	}
	
	public String uploadProductPicToAwsS3Bucket(MultipartFile file, String dynamicFolderName) throws IOException
	{
		return uploadToAwsS3Bucket(file, awsS3BucketName, awsS3ProductPicFolder, dynamicFolderName);
	}


	public String uploadToAwsS3Bucket(MultipartFile file, String bucketName, String folderName, String dynamicFolderName) throws IOException
	{
		// Set up S3 client
		S3Client s3Client = S3Client.builder().region(Region.US_EAST_1) // US East (N. Virginia) us-east-1
				.credentialsProvider(
						StaticCredentialsProvider.create(AwsBasicCredentials.create(awsAccessKey, awsSecretKey)))
				.build();

		// Specify the file to be uploaded
		//File fileToUpload = new File("C:\\Users\\tamil\\Downloads\\Dhoni.jpg"); // Replace with the actual file path

		// Specify the destination folder in the S3 bucket
		String destinationKey = folderName  + "/" + dynamicFolderName + "/" + file.getOriginalFilename();

		try
		{
			// Now you can use 'convertedFile' directly in your 'putObject' method
			PutObjectRequest putObjectRequest = PutObjectRequest.builder()
			        .bucket(bucketName)
			        .key(destinationKey)
			        .build();

			InputStream inputStream = file.getInputStream();
			PutObjectResponse response = s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, file.getSize()));

			System.out.println("File uploaded successfully to " + destinationKey);
			System.out.println("Response: " + response.toString());
		} 
		catch (S3Exception e)
		{
			System.err.println(e.awsErrorDetails().errorMessage());
			System.exit(1);
		}
		return destinationKey;
	}
}
