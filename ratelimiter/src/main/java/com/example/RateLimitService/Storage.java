package com.example.RateLimitService;

import java.time.Instant;

public interface Storage {
    public boolean put(String ip,Instant currentTime);
}
