/**
 * Copyright : www.easipay.net , 2009-2010
 * Project : PEPP
 * $Id$
 * $Revision$
 * Last Changed by $Author$ at $Date$
 * $URL$
 * 
 * Change Log
 * Author      Change Date    Comments
 *-------------------------------------------------------------
 * your name     2016年9月5日        Initailized
 */

package com.shcepp.shdippsvr.sys.exception;


/**
 * TODO Add class descriptions
 *
 *
 */
public class ParseException extends Exception {
    public ParseException(Throwable ex) {
        super(ex);
    }
    public ParseException()
    {
        super();
    }

    public ParseException(String message)
    {
        super(message);
    }
}
