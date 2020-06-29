package com.example.herokudemo.web.client;

import com.example.herokudemo.web.model.CommonMessageDTO;
import com.google.gson.Gson;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Component
@ConfigurationProperties("heroku.demo")
public class MulesoftClient {

    private Logger logger = Logger.getLogger(MulesoftClient.class.getName());

    private final String CUST_UPDATE_PATH = "/api/cust_update_email";
    private final String CUST_OPT_PATH = "/api/CUST_optOut_optIn";
    private final String PROD_OFFER_PATH = "/api/prod_offer";

    private String apiHost;

    private RestTemplate restTemplate;

    public MulesoftClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void sendCustomerUpdateEmail(CommonMessageDTO commonMessageDTO){
        StringBuilder generateUrl = new StringBuilder(apiHost).append(CUST_UPDATE_PATH);
        this.forwardMessagePost(generateUrl.toString(), commonMessageDTO);
    }

    public void sendCustomerOpt(CommonMessageDTO commonMessageDTO){
        StringBuilder generateUrl = new StringBuilder(apiHost).append(CUST_OPT_PATH);
        this.testConvertedObjectJson(commonMessageDTO);
        this.forwardMessagePost(generateUrl.toString(), commonMessageDTO);
    }

    public void sendProductOffer(CommonMessageDTO commonMessageDTO){
        StringBuilder generateUrl = new StringBuilder(apiHost).append(PROD_OFFER_PATH);
        this.forwardMessagePost(generateUrl.toString(), commonMessageDTO);
    }

    private void testConvertedObjectJson(CommonMessageDTO commonMessageDTO){
        Gson gson = new Gson();
        String json = gson.toJson(commonMessageDTO);
    }

    private void forwardMessagePost(String url, CommonMessageDTO message){
        logger.info("Forwarding to URL: " + url);
        this.restTemplate.postForLocation(url, message);
        logger.info("Done sending to URL: " + url);
    }



    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }
}
