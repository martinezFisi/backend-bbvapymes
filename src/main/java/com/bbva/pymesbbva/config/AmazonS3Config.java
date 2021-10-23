package com.bbva.pymesbbva.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.bbva.pymesbbva.util.PropertiesUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonS3Config {

    PropertiesUtil propertiesUtil;

    public AmazonS3Config(PropertiesUtil propertiesUtil) {
        this.propertiesUtil = propertiesUtil;
    }

    @Bean
    public AmazonS3 amazonS3() {
        var credentials = new BasicAWSCredentials(propertiesUtil.AMAZON_S3_CREDENTIALS_ACCESS_KEY, propertiesUtil.AMAZON_S3_CREDENTIAL_SECRET_KEY);
        return AmazonS3ClientBuilder.standard()
                                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                                    .withRegion(propertiesUtil.AMAZON_S3_REGION_STATIC).build();
    }

}
