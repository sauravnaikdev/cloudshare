package com.cloudshare.controller;

import com.cloudshare.dto.ApiResponse;
import com.cloudshare.dto.HealthResponse;
import com.cloudshare.service.HealthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class HealthController {

    private final HealthService healthService;

//    public HealthController(HealthService healthService) {
//        this.healthService = healthService;
//    }

    @GetMapping("/api/v1/health")
    public ResponseEntity<ApiResponse<HealthResponse>>health(){
//        HealthResponse health = new HealthResponse("UP","CloudShare","1.0.0");
//        ApiResponse<HealthResponse> response = new ApiResponse<>(true,"Application is running successfully",health,LocalDateTime.now());
        return ResponseEntity.ok(healthService.getHealth());
    }
}
