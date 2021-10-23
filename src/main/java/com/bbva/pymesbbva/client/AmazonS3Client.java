package com.bbva.pymesbbva.client;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.bbva.pymesbbva.util.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Slf4j
@Component
public class AmazonS3Client {

    private final AmazonS3 amazonS3;
    private final PropertiesUtil propertiesUtil;

    public AmazonS3Client(AmazonS3 amazonS3, PropertiesUtil propertiesUtil) {
        this.amazonS3 = amazonS3;
        this.propertiesUtil = propertiesUtil;
    }

    public String uploadFile(String fileName, InputStream inputStream) {
        amazonS3.putObject(new PutObjectRequest(propertiesUtil.AMAZON_S3_APPLICATION_BUCKET_NAME, fileName, inputStream, null));
        return "File uploaded : " + fileName;
    }

}
