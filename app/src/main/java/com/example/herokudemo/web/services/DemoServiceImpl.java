package com.example.herokudemo.web.services;

import com.example.herokudemo.web.model.CommonMessageDTO;
import com.example.herokudemo.web.model.MessageDTO;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.util.UUID;

@Service
public class DemoServiceImpl implements DemoService {

    private final ServletContext servletContext;
    private final String CONTEXT_KEY = "LATEST_MESSAGE";

    public DemoServiceImpl(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public CommonMessageDTO getSampleCommonMessage() {
        return CommonMessageDTO.builder()
                .topic("testTopic")
                .desc("testDesc")
                .message(MessageDTO.builder()
                        .brand("testBrand")
                        .email("testEmail")
                        .mobile(98989898)
                        .hkId(UUID.randomUUID().toString()).build()).build();
    }

    @Override
    public void saveMesage(CommonMessageDTO message) {
        this.servletContext.setAttribute(CONTEXT_KEY, message);
    }

    @Override
    public void forwardMessage(CommonMessageDTO message) {

    }

    @Override
    public CommonMessageDTO getMessage() {
        return (CommonMessageDTO) this.servletContext.getAttribute(CONTEXT_KEY);
    }
}
