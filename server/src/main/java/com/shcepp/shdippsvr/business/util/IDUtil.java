/**
 * @Project: efep_jobs
 * @Title: IDUtil.java
 * @Package com.shcepp.util
 * @Description: TODO
 * @author zhaojianjun jjzhao@shcepp.com
 * @date 2017-4-27 上午11:15:08
 * @Copyright: 2017 Shanghai Cross-border e-Commerce Public Service Co.,Ltd.
 * @version V1.0
 * <p>
 * Change Log
 * Author      Change Date    Comments
 */

package com.shcepp.shdippsvr.business.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhaojianjun jjzhao@shcepp.com
 * @ClassName: IDUtil
 * @Description: TODO
 * @date 2017-4-27 上午11:15:08
 */
public class IDUtil {

    private static int seqNo = 1000;
    private static int serNo = 1000;
    private static int wSerNo = 1000;
    private static int accSeqNo = 1000;

    public String getSequenceNo(String pre) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmsss");
        String dateStr = sdf.format(new Date());

        synchronized (this) {
            // 流水号+1
            seqNo = seqNo + 1;
            if (seqNo > 9999)
                seqNo = 1000;
        }

        /**
         * 开头符号(1)+系统日期(13)+流水号(4)+随机数(2)
         */
        int i = (int) (Math.random() * 100);
        String orderNo = pre + dateStr.substring(2, dateStr.length()) + seqNo + String.format("%02d", i);

        return orderNo;
    }

    public String getSequenceNoByAccount() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        String dateStr = sdf.format(new Date());
        
/*        synchronized (this) {
            // 流水号+1
        	accSeqNo = accSeqNo+1;
            if (accSeqNo > 9999)
            	accSeqNo = 1000;
        }*/

        /**
         * 系统日期(10)
         */
        String orderNo = dateStr;

        return orderNo;
    }

    public String getSerialNo(String pre) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmsss");
        String dateStr = sdf.format(new Date());

        synchronized (this) {
            // 流水号+1
            serNo = serNo + 1;
            if (serNo > 999)
                serNo = 100;
        }

        /**
         * 开头符号+系统日期(13)+流水号(4)+随机数(2)
         */
        int i = (int) (Math.random() * 100);
        String orderNo = pre + dateStr.substring(2, dateStr.length()) + serNo + String.format("%02d", i);

        return orderNo;
    }

    /**
     * 生成东方支付的32位唯一数字
     * @param pre
     * @return
     */
    public String getEasiPySerialNo() {

        String pre = PropertiesUtil.get("EASIPAY.PPX000.PLATCODE");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = sdf.format(new Date());

        synchronized (this) {
            // 流水号+1
            wSerNo = wSerNo + 1;
            if (wSerNo > 9999)
                wSerNo = 1000;
        }

        /**
         * 开头符号+系统日期(14)+流水号(4)+随机数6)
         */
        int i = (int) ((Math.random()*9+1) * 100000);

        return pre + dateStr  + serNo + String.format("%02d", i);

    }
}
