package com.bbva.pymesbbva.client;

import com.bbva.pymesbbva.config.ResponseEntityBbva;
import com.bbva.pymesbbva.dto.infobip.sms.DestinationSmsInfobipDto;
import com.bbva.pymesbbva.dto.infobip.sms.MessageSmsInfobipDto;
import com.bbva.pymesbbva.dto.infobip.sms.RequestSmsInfobipDto;
import com.bbva.pymesbbva.util.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@Component
public class SMSInfobipClient {

    private final RestTemplate restTemplate;
    private final PropertiesUtil propertiesUtil;

    @Autowired
    public SMSInfobipClient(RestTemplate restTemplate, PropertiesUtil propertiesUtil) {
        this.restTemplate = restTemplate;
        this.propertiesUtil = propertiesUtil;
    }

    public ResponseEntity<String> sendSms(String sendTo, String message) {
        var httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(APPLICATION_JSON);
        httpHeaders.set("Authorization", "App ".concat(propertiesUtil.API_INFOBIP_API_KEY));

        var requestSmsInfobipDto = buildRequest(sendTo, propertiesUtil.API_INFOBIP_SMS_FROM, message);
        log.info("Request SMS: {}", requestSmsInfobipDto);

        var httpEntity = new HttpEntity<>(requestSmsInfobipDto, httpHeaders);

        return trySendSms(httpEntity);
    }

    private RequestSmsInfobipDto buildRequest(String sendTo, String subject, String message) {
        var destinationSmsInfobipDto = DestinationSmsInfobipDto.builder().to(sendTo).build();
        var messageSmsInfobipDto = MessageSmsInfobipDto.builder()
                                                       .from(subject)
                                                       .destinations(List.of(destinationSmsInfobipDto))
                                                       .text(message)
                                                       .build();
        return RequestSmsInfobipDto.builder()
                                   .messages(List.of(messageSmsInfobipDto))
                                   .build();
    }

    private ResponseEntity<String> trySendSms(HttpEntity<RequestSmsInfobipDto> httpEntity) {
        try {
            var infobipApiSmsUrl = propertiesUtil.API_INFOBIP_URL.concat(propertiesUtil.API_INFOBIP_SMS_URI);

            log.info("URL: {}, Request: {}", infobipApiSmsUrl, httpEntity);
            var responseEntity = restTemplate.postForEntity(infobipApiSmsUrl, httpEntity, String.class);
            log.info("Response: {}", responseEntity);

            return responseEntity;
        } catch (RestClientException restClientException) {
            var responseEntity = ResponseEntityBbva.internalServerError(restClientException.getMessage());
            log.info("Response: {}", responseEntity);

            return  responseEntity;
        }
    }

}
