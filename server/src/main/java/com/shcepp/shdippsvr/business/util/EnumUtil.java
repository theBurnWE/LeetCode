//package com.shcepp.shdippsvr.business.util;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
///**
// * @author BrunE
// * @date 2019-09-05 16:54
// **/
//@Component
//public class EnumUtil implements ApplicationContextAware {
//
//    protected final static Log logger = LogFactory.getLog(EnumUtil.class);
//    Method toName = em.getMethod("getValue");
//    Method toCode = em.getMethod("getKey");
//    //得到enum的所有实例
//    Object[] objs = em.getEnumConstants();
//    EnumDto dto;
//    List<EnumDto> list = new ArrayList<>();
//		for (Object obj : objs) {
//        dto = new EnumDto();
//        dto.setKey((Integer)toCode.invoke(obj));
//        dto.setValue((String)toName.invoke(obj));
//        list.add(dto);
//    }
//		return list;
//}
