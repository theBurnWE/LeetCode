--创建对应的sequence
CREATE SEQUENCE SHDIPP.COMMON_SEQ --序列名
INCREMENT BY 1 -- 每次加几个  
START WITH 1 -- 从1开始计数  
NOMAXVALUE -- 不设置最大值  
NOCYCLE -- 一直累加，不循环  
CACHE 10;
