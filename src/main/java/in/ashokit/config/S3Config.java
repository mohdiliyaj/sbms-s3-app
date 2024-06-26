package in.ashokit.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class S3Config {
	
	@Value("${amazonProperties.accessKey}")
	private String accessKey;
	
	@Value("${amazonProperties.secretKey}")
	private String secretKey;
	
	@Value("${amazonProperties.region}")
	private String region;
	
	@Bean
	public AmazonS3 s3Client() {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		
		AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
				.withRegion(region)
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.build();
			
		return amazonS3;
	}	
}
