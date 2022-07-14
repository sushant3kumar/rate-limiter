package com.example.RateLimitServiceTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.example.RateLimitService.HashMapStorageImpl;
import com.example.RateLimitService.RateLimiter;
import com.example.RateLimitService.Storage;

public class RateLimiterTest {
    Storage hashMapStorageImpl;
    RateLimiter rateLimiter;

    @Before
    public void setup(){
        hashMapStorageImpl = HashMapStorageImpl.getHashMapStorage(5, 1);
        rateLimiter = new RateLimiter(hashMapStorageImpl);
    }
    @Test
    public void rateLimiterTest() throws InterruptedException{
        int count1 = 0;
        int count2 = 0;
        for(int i=0;i<5;i++){
            for(int j=0;j<10;j++){
                if(rateLimiter.isValid("ip")){
                    count1++;
                }
                if(rateLimiter.isValid("ip1")){
                    count2++;
                }
            }
            Thread.sleep(1000);
        }
        assertEquals(25, count1);
        assertEquals(25, count2);
    }
}
