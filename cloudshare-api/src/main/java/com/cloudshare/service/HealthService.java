package com.cloudshare.service;

import com.cloudshare.dto.ApiResponse;
import com.cloudshare.dto.HealthResponse;

public interface HealthService {
    ApiResponse<HealthResponse>getHealth();
}
