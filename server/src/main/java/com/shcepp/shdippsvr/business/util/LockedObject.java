package com.shcepp.shdippsvr.business.util;

public class LockedObject<T> {
    
    T object;
    String locked;
    String key;
    public static final String ST_LOCKED = "LOCKED";
    public static final String ST_FREE = "FREE";
    public static final int size = 10000;
    
    public String toString() {
        return ("key[" + key + "] is " + locked);
    }
    
}
