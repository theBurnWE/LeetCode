package com.shcepp.shdippsvr.business.util;

import org.exolab.castor.xml.Marshaller;

import java.io.StringWriter;

public class CBTMarshaller {
    
    static StringWriter out = new StringWriter(2048);
    static Marshaller m = null;
    
    public static String marshal(Object object) throws Exception {
        if (m == null) {
            m = new Marshaller(out);
        }
        synchronized (m) {
            synchronized (out) {
                m.setMarshalAsDocument(true);
                StringBuffer sb = out.getBuffer();
                sb.delete(0, sb.length());
                m.marshal(object);
                return out.toString();
            }
        }
    }
    
}
