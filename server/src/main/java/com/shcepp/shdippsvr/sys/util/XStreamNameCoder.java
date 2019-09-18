package com.shcepp.shdippsvr.sys.util;

import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

/**
 * @Title: XStreamNameCoder.java
 * @Description: 自定义XML解析器
 * @author EP-mlzhang
 * @date 2016年9月6日 下午1:53:08
 * @version V1.0
 */
public class XStreamNameCoder extends XmlFriendlyNameCoder {
	public XStreamNameCoder() {
		super("_-", "_");
	}
}
