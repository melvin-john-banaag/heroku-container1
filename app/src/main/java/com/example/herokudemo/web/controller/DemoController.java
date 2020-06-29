package com.example.herokudemo.web.controller;

import com.example.herokudemo.web.client.MulesoftClient;
import com.example.herokudemo.web.model.CommonMessageDTO;
import com.example.herokudemo.web.services.DemoService;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.util.logging.Logger;

/**
 * @author Joseph Garcia
 * @since 21/06/2020
 */
@RestController
@RequestMapping("/api")
public class DemoController {

    private Logger logger = Logger.getLogger(DemoController.class.getName());

    private final DemoService demoService;
    private final MulesoftClient mulesoftClient;

    public DemoController(DemoService demoService,
                          MulesoftClient mulesoftClient) {
        this.demoService = demoService;
        this.mulesoftClient = mulesoftClient;
    }

    @GetMapping("/test")
    public ResponseEntity<CommonMessageDTO> testMessage(){
        return new ResponseEntity<CommonMessageDTO>(this.demoService.getSampleCommonMessage(), HttpStatus.OK);
    }

    /**
     * Customer update email
     * http://eda-cloud-app-kafka-app.sg-s1.cloudhub.io/api/cust_update_email
     * {
     *                 "topic":"CUST_update_email",
     *                 "desc":"Customer update email address",
     *                 "message":{
     *                                 "userId":"U231353674",
     *                                 "oldEmail":"ewjilkfw@netvigator.com",
     *                                 "newEmail":"abcdefg@netvigator.com"
     *                 }
     * }
     */
    @PostMapping("/cust_update_email")
    public ResponseEntity<CommonMessageDTO> customerUpdate(@RequestBody CommonMessageDTO message){
        logger.info("Received customer update request.");
        this.mulesoftClient.sendCustomerUpdateEmail(message);
        logger.info("Saving message.");
        this.demoService.saveMesage(message);
        return new ResponseEntity<CommonMessageDTO>(message, HttpStatus.CREATED);
    }


    /**
     * Customer opt-in/out
     * http://eda-cloud-app-kafka-app.sg-s1.cloudhub.io/api/CUST_optOut_optIn
     * {
     *                 "topic":"CUST_optOut_optIn",
     *                 "desc":"Customer request opt-out/opt-in",
     *                 "message":{
     *                                 "userId:"U2313532432",
     *                                 "mobile":98989898,
     *                                 "email":"",
     *                                 "hkId":"",
     *                                 "brand":"csl",
     *                                 "opt-out":true
     *                 }
     * }
     *
     * {
     *                 "topic":"CUST_optOut_optIn",
     *                 "desc":"Customer request opt-out/opt-in",
     *                 "message":{
     *                                 "userId":"U231353674",
     *                                 "mobile":null,
     *                                 "email":"abcdefg@netvigator.com",
     *                                 "hkId":"",
     *                                 "brand":"netvigator",
     *                                 "opt-out":true
     *                 }
     * }
     */
    @PostMapping("/CUST_optOut_optIn")
    public ResponseEntity<CommonMessageDTO> customerOpt(@RequestBody CommonMessageDTO message){
        logger.info("Received customer opt-in-out request.");
        logger.info("Opt value: "+Boolean.toString(message.getMessage().isOptOut()));
        this.mulesoftClient.sendCustomerOpt(message);
        logger.info("Saving message.");
        this.demoService.saveMesage(message);
        return new ResponseEntity<CommonMessageDTO>(message, HttpStatus.CREATED);
    }

    /**
     * Product offer
     * http://eda-cloud-app-kafka-app.sg-s1.cloudhub.io/api/prod_offer
     * {
     *                 "topic":"PROD_offer",
     *                 "desc":"New/update product offer",
     *                 "message":{
     *                                 "offerId":"O19840328490",
     *                                 "brand":"csl",
     *                                 "type":"new"
     *                 }
     * }
     *
     * {
     *                 "topic":"PROD_offer",
     *                 "desc":"New/update product offer",
     *                 "message":{
     *                                 "offerId":"O19840327565",
     *                                 "brand":"1010",
     *                                 "type":"update"
     *                 }
     * }
     */
    @PostMapping("/prod_offer")
    public ResponseEntity<CommonMessageDTO> productOffer(@RequestBody CommonMessageDTO message){
        logger.info("Received customer product offer request.");
        this.mulesoftClient.sendProductOffer(message);
        logger.info("Saving message.");
        this.demoService.saveMesage(message);
        return new ResponseEntity<CommonMessageDTO>(message, HttpStatus.CREATED);
    }
}
