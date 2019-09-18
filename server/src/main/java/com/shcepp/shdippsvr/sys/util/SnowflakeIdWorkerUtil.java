package com.shcepp.shdippsvr.sys.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author wjsheng
 * 为创建不重复的有序订单ID而增加的类
 * 在配置的时候datacenterId保持一致而workID不一致即可保证生产的ID唯一且自增
 */
@Component
public class SnowflakeIdWorkerUtil {

    private static final long twepoch = 1288834974657L;
    private static final long workerIdBits = 5L;
    private static final long datacenterIdBits = 5L;
    private static final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private static final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private static final long sequenceBits = 12L;
    private static final long workerIdShift = sequenceBits;
    private static final long datacenterIdShift = sequenceBits + workerIdBits;
    private static final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private static final long sequenceMask = -1L ^ (-1L << sequenceBits);
    private static long workerId;
    private static String sysCode;
    private static long datacenterId;
    private static long sequence = 0L;
    private static long lastTimestamp = -1L;

    public static String nextIdStr() {
        return String.valueOf(nextId());
    }

    public static synchronized long nextId() {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }

        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                                                     lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
    }

    protected static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected static long timeGen() {
        return System.currentTimeMillis();
    }

    public long getWorkerId() {
        return workerId;
    }

    @Value("${snowflake.workerId}")
    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }

    public long getDatacenterId() {

        return datacenterId;
    }

    @Value("${snowflake.datacenterId}")
    public void setDatacenterId(long datacenterId) {
        this.datacenterId = datacenterId;
    }

    @Value("${shdippsvrcache.file.patten:shdipp}")
    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }
}
