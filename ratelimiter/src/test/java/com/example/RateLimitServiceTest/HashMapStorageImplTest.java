package com.example.RateLimitServiceTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.example.RateLimitService.HashMapStorageImpl;

public class HashMapStorageImplTest {
    HashMapStorageImpl hashMapStorageImpl;

    @Before
    public void setUp(){
        hashMapStorageImpl = HashMapStorageImpl.getHashMapStorage(10, 1);
    }
    @Test
    public void testGetHashMapStorageImpl(){
        assertNotNull(hashMapStorageImpl);
    }
    @Test
    public void testSetTimer(){
        assertEquals(hashMapStorageImpl.getLimit(), 10);
        HashMapStorageImpl.setCapacity(11);
        assertEquals(hashMapStorageImpl.getLimit(), 11);
    }
}
