package com.example.RateLimitService;

import java.time.Instant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RateLimiter {
    Storage rateLimiterStorage;

    public boolean isValid(String ip){
        return rateLimiterStorage.put(ip, Instant.now());
    }
}
