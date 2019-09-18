package com.shcepp.shdippsvr.business.util;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class CBTUnmarshaller {
    
    // 缓存策略
    // lock() 获取一个锁定的 对象
    // free() 使用完毕后，还原对象
    
    private static Map<String, LockedObject<Unmarshaller>> mapUnmarshaller = new HashMap<String, LockedObject<Unmarshaller>>();
    
    private synchronized static LockedObject<Unmarshaller> lock(Class<? extends Object> klass) {
        
        String key = "";
        LockedObject<Unmarshaller> lockedObj = null;
        
        for (int i = 0; i < LockedObject.size; i++) {
            key = klass.getName() + "," + i;
            lockedObj = mapUnmarshaller.get(key);
            // 该key不存在，或 未被锁定使用，可用
            if ((null == lockedObj)
                    || (!LockedObject.ST_LOCKED.equalsIgnoreCase(lockedObj.locked))) {
                break;
            }
            if (i >= LockedObject.size - 1) {
                throw new RuntimeException("Unmarshaller并发数量已超设计标准("
                        + LockedObject.size
                        + "个),本次请求异常");
            }
        }
        
        if ((null != lockedObj)
                && (LockedObject.ST_FREE.equalsIgnoreCase(lockedObj.locked))) {
            // 说明之前已经存在了，直接使用
        } else {
            // 说明没有可用的，new 一个对象，并加入 map
            Unmarshaller unmarshallerGet = new Unmarshaller(klass);
            unmarshallerGet.setWhitespacePreserve(true);
            lockedObj = new LockedObject<Unmarshaller>();
            lockedObj.object = unmarshallerGet;
            mapUnmarshaller.put(key, lockedObj);
        }
        lockedObj.locked = LockedObject.ST_LOCKED;
        lockedObj.key = key;
        return lockedObj;
    }
    
    private synchronized static void free(LockedObject<Unmarshaller> freeObj) {
        if (null != freeObj) {
            freeObj.locked = LockedObject.ST_FREE;
        }
    }
    
    public static Object unmarshal(Class<? extends Object> klass, Reader reader)
            throws MarshalException, ValidationException {
        LockedObject<Unmarshaller> lockObject = lock(klass);
        Object ret = null;
        try {
            ret = lockObject.object.unmarshal(reader);
        } finally {
            free(lockObject);
        }
        return ret;
    }
    
    public static Object unmarshal(Class<? extends Object> klass, Node node)
            throws MarshalException, ValidationException {
        LockedObject<Unmarshaller> lockObject = lock(klass);
        Object ret = null;
        try {
            ret = lockObject.object.unmarshal(node);
        } finally {
            free(lockObject);
        }
        
        return ret;
    }
    
    public static Object unmarshal(Class<? extends Object> klass,
                                   InputSource source) throws MarshalException,
            ValidationException {
        LockedObject<Unmarshaller> lockObject = lock(klass);
        Object ret = null;
        try {
            ret = lockObject.object.unmarshal(source);
        } finally {
            free(lockObject);
        }
        
        return ret;
    }
}
