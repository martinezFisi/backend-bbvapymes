package com.bbva.pymesbbva.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Data
@Component
public class PropertiesUtil {

    @Value("${api.infobip.url}")
    public String API_INFOBIP_URL;

    @Value("${api.infobip.api.key}")
    public String API_INFOBIP_API_KEY;

    @Value("${api.infobip.sms.uri}")
    public String API_INFOBIP_SMS_URI;

    @Value("${api.infobip.sms.from}")
    public String API_INFOBIP_SMS_FROM;

    @Value("${email.sender}")
    public String EMAIL_SENDER;

    @Value("${email.sender.password}")
    public String EMAIL_SENDER_PASSWORD;

    @Value("${email.subject}")
    public String EMAIL_SUBJECT;

    @Value("${email.message}")
    public String EMAIL_MESSAGE;

    @Value("${amazon.s3.url}")
    public String AMAZON_S3_URL;

    @Value("${amazon.s3.credentials.access-key}")
    public String AMAZON_S3_CREDENTIALS_ACCESS_KEY;

    @Value("${amazon.s3.credentials.secret-key}")
    public String AMAZON_S3_CREDENTIAL_SECRET_KEY;

    @Value("${amazon.s3.region.static}")
    public String AMAZON_S3_REGION_STATIC;

    @Value("${amazon.s3.stack.auto}")
    public String AMAZON_S3_STACK_AUTO;

    @Value("${amazon.s3.application.bucket.name}")
    public String AMAZON_S3_APPLICATION_BUCKET_NAME;

    @Value("${digital.sign.certificate}")
    public String DIGITAL_SIGN_CERTIFICATE;

    @Value("${digital.sign.certificate.password}")
    public String DIGITAL_SIGN_CERTIFICATE_PASSWORD;

    @Value("${pdfs.directory}")
    public String PDFS_DIRECTORY;

    @Value("${pdfs.signed.directory}")
    public String PDFS_SIGNED_DIRECTORY;

    @Value("${amazon.s3.utils.bbva-logo}")
    public String AMAZON_S3_UTILS_BBVA_LOGO;

    @PostConstruct
    private void postConstruct() {
        log.info(toString());
    }

}
