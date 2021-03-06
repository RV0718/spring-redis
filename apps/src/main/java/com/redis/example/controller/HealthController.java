package com.redis.example.controller;

import com.redis.example.constants.ApplicationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.redis.example.constants.ApplicationConstants.HEALTH_EP;

@RestController
@RequestMapping(ApplicationConstants.API_EP)
public class HealthController {

    private final static Logger LOG = LoggerFactory.getLogger(HealthController.class);

    @GetMapping(HEALTH_EP)
    public String checkHealthStatus() {
        return "{\"health" + " :" + "UP\"}";
    }

}
