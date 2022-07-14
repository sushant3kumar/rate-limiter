package com.example.RateLimitService;

import java.time.Instant;
import java.util.HashMap;

import lombok.Getter;

@Getter
public class HashMapStorageImpl implements Storage {
    static HashMapStorageImpl hashMapStorageImpl;
    HashMap<String,Instant> firstOccurence;
    HashMap<String,Integer> freqMap;
    int limit;
    int timePeriod;

    
    private HashMapStorageImpl(int capacity,int time) {
        firstOccurence = new HashMap<>();
        freqMap = new HashMap<>();
        limit = capacity;
        timePeriod = time;
    }

    public static HashMapStorageImpl getHashMapStorage(int capacity,int time){
        if(hashMapStorageImpl==null){
            hashMapStorageImpl = new HashMapStorageImpl(capacity,time);
        }
        //can throw an expection when the asked capacity and time is different from that of instance
        return hashMapStorageImpl;
    }

    public static void setCapacity(int newCapacity){
        if(hashMapStorageImpl != null)hashMapStorageImpl.limit = newCapacity;
    }

    public static void setTimePeriodInSeconds(int seconds){
        if(hashMapStorageImpl != null)hashMapStorageImpl.timePeriod = seconds;
    }


    @Override
    public boolean put(String ip, Instant currentTime) {
        if(!firstOccurence.containsKey(ip) || (firstOccurence.get(ip).plusSeconds(timePeriod).isBefore(currentTime))){
            firstOccurence.put(ip, currentTime);
            freqMap.put(ip, 1);
            return true;
        }else if(freqMap.get(ip)<limit){
                freqMap.put(ip, freqMap.get(ip)+1);
                return true;
        }
        return false;
    }
    
}
