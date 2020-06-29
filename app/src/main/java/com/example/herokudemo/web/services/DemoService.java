package com.example.herokudemo.web.services;

import com.example.herokudemo.web.model.CommonMessageDTO;

public interface DemoService {

    public CommonMessageDTO getSampleCommonMessage();
    public void saveMesage(CommonMessageDTO message);
    public void forwardMessage(CommonMessageDTO message);
    public CommonMessageDTO getMessage();
}
