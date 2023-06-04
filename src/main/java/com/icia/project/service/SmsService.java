//package com.icia.project.service;
//
//import org.springframework.http.HttpHeaders;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.icia.project.dto.MessageDTO;
//import com.icia.project.dto.SmsRequestDTO;
//import com.icia.project.dto.SmsResponseDTO;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.tomcat.util.codec.binary.Base64;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.MediaType;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestClientException;
//import org.springframework.web.client.RestTemplate;
//
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//import java.io.UnsupportedEncodingException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class SmsService {
//    private final String accesskey;
//    private final String secretKey;
//    private final String serviceId;
//    private final String senderPhone;
//
//    public String makeSignature(Long time) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
//        String space = " ";                    // one space
//        String newLine = "\n";                    // new line
//        String method = "POST";                    // method
//        String url = "/sms/v2/service/" + this.serviceId + "/messages";    // url (include query string)
//        String timestamp = time.toString();            // current timestamp (epoch)
//        String accessKey = this.accesskey;            // access key id (from portal or Sub Account)
//        String secretKey = this.secretKey;
//        String message = new StringBuilder()
//                .append(method)
//                .append(space)
//                .append(url)
//                .append(newLine)
//                .append(timestamp)
//                .append(newLine)
//                .append(accessKey)
//                .toString();
//        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
//        Mac mac = Mac.getInstance("HmacSHA256");
//        mac.init(signingKey);
//        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
//        String encodeBase64String = Base64.encodeBase64String(rawHmac);
//        return encodeBase64String;
//    }
//
//    public SmsResponseDTO sendSms(MessageDTO messageDto) throws JsonProcessingException, RestClientException, URISyntaxException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
//        Long time = System.currentTimeMillis();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("x-ncp-apigw-timestamp", time.toString());
//        headers.set("x-ncp-iam-access-key", accesskey);
//        headers.set("x-ncp-apigw-signature-v2", makeSignature(time));
//        List<MessageDTO> messages = new ArrayList<>();
//        messages.add(messageDto);
//
//        SmsRequestDTO request = SmsRequestDTO.builder()
//                .type("SMS")
//                .contentType("COMM")
//                .countryCode("82")
//                .from(senderPhone)
//                .content(messageDto.getContent())
//                .messages(messages)
//                .build();
//        ObjectMapper objectMapper = new ObjectMapper();
//        String body = objectMapper.writeValueAsString(request);
//        HttpEntity<String> httpBody = new HttpEntity<>(body, headers);
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
//        SmsResponseDTO response = restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/" + serviceId + "/messages"), httpBody, SmsResponseDTO.class);
//        return response;
//
//    }
//}
