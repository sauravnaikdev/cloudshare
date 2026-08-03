package com.cloudshare.service.impl;

import com.cloudshare.dto.ApiResponse;
import com.cloudshare.dto.HealthResponse;
import com.cloudshare.service.HealthService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HealthServiceImpl implements HealthService {

    @Override
    public ApiResponse<HealthResponse>getHealth(){
//        throw  new RuntimeException("Testing global exception");
        HealthResponse healthResponse = new HealthResponse("UP","CloudShare","1.0.0");
        return  new ApiResponse<>(true,"Application is running successfully",healthResponse, LocalDateTime.now());
    }
}
