ALTER TABLE BASE_IP_IN_LOG
 DROP PRIMARY KEY CASCADE;

DROP TABLE BASE_IP_IN_LOG CASCADE CONSTRAINTS;

CREATE TABLE BASE_IP_IN_LOG
(
  IP           VARCHAR2(100 BYTE),
  CREATE_TIME  DATE,
  SPT          VARCHAR2(100 BYTE),
  ID           VARCHAR2(50 BYTE)                NOT NULL
)
TABLESPACE SHDIPP_DATA
RESULT_CACHE (MODE DEFAULT)
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MAXSIZE          UNLIMITED
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
            FLASH_CACHE      DEFAULT
            CELL_FLASH_CACHE DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;

COMMENT ON COLUMN BASE_IP_IN_LOG.IP IS 'ip';

COMMENT ON COLUMN BASE_IP_IN_LOG.CREATE_TIME IS '访问时间';

COMMENT ON COLUMN BASE_IP_IN_LOG.ID IS '主键';



ALTER TABLE OAUTH_BIZ_APP
 DROP PRIMARY KEY CASCADE;

DROP TABLE OAUTH_BIZ_APP CASCADE CONSTRAINTS;

CREATE TABLE OAUTH_BIZ_APP
(
  ID            NUMBER                          NOT NULL,
  APP_CODE      VARCHAR2(100 CHAR)              NOT NULL,
  APP_NAME      VARCHAR2(100 CHAR)              NOT NULL,
  DOMAIN        VARCHAR2(100 CHAR),
  IP            VARCHAR2(50 BYTE),
  APP_SECRET    VARCHAR2(100 CHAR),
  IS_OPEN       VARCHAR2(2 CHAR)                NOT NULL,
  CLIENT_ID     VARCHAR2(100 BYTE)              NOT NULL,
  CREATE_DATE   DATE,
  WEB_URL       VARCHAR2(300 CHAR),
  ICON_URL      VARCHAR2(300 CHAR),
  ORDERNO       NUMBER,
  APPTYPE       VARCHAR2(10 BYTE),
  SERVICEURL    VARCHAR2(200 BYTE),
  DESPLAY_MENU  VARCHAR2(2 BYTE),
  AUTHOR_CODE   VARCHAR2(100 BYTE),
  PUBLIC_KEY    VARCHAR2(4000 CHAR),
  VALIDATE_NUM  NUMBER(10),
  ISSSO         VARCHAR2(2 BYTE)
)
TABLESPACE SHDIPP_DATA
RESULT_CACHE (MODE DEFAULT)
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          1M
            NEXT             1M
            MAXSIZE          UNLIMITED
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
            FLASH_CACHE      DEFAULT
            CELL_FLASH_CACHE DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;

COMMENT ON TABLE OAUTH_BIZ_APP IS '指需要和oauth进行集成的第三方app信息';

COMMENT ON COLUMN OAUTH_BIZ_APP.ID IS 'Sequence ：SEQ_OAUTH_BIZ_APP';

COMMENT ON COLUMN OAUTH_BIZ_APP.APP_CODE IS 'App代码';

COMMENT ON COLUMN OAUTH_BIZ_APP.APP_NAME IS 'App中文名字';

COMMENT ON COLUMN OAUTH_BIZ_APP.DOMAIN IS 'App域名';

COMMENT ON COLUMN OAUTH_BIZ_APP.IP IS 'App所部署的ip地址';

COMMENT ON COLUMN OAUTH_BIZ_APP.APP_SECRET IS 'App证书';

COMMENT ON COLUMN OAUTH_BIZ_APP.IS_OPEN IS '是否已经开通集成';

COMMENT ON COLUMN OAUTH_BIZ_APP.APPTYPE IS '如果是epCas 则表示当前app入口是epCas论证';

COMMENT ON COLUMN OAUTH_BIZ_APP.SERVICEURL IS '用来和epCas中UUMM的servcie相对应';

COMMENT ON COLUMN OAUTH_BIZ_APP.AUTHOR_CODE IS '服务器唯一码';



ALTER TABLE OAUTH_BIZ_APP_USER
 DROP PRIMARY KEY CASCADE;

DROP TABLE OAUTH_BIZ_APP_USER CASCADE CONSTRAINTS;

CREATE TABLE OAUTH_BIZ_APP_USER
(
  ID           NUMBER                           NOT NULL,
  LOGIN_ID     VARCHAR2(100 CHAR)               NOT NULL,
  APP_CODE     VARCHAR2(100 CHAR)               NOT NULL,
  CREATE_DATE  DATE                             NOT NULL,
  ISSSO        VARCHAR2(2 BYTE),
  COMMONUSE    VARCHAR2(2 BYTE)
)
TABLESPACE SHDIPP_DATA
RESULT_CACHE (MODE DEFAULT)
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          1M
            NEXT             1M
            MAXSIZE          UNLIMITED
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
            FLASH_CACHE      DEFAULT
            CELL_FLASH_CACHE DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;

COMMENT ON TABLE OAUTH_BIZ_APP_USER IS 'app模块控制';

COMMENT ON COLUMN OAUTH_BIZ_APP_USER.ID IS 'Sequence ：SEQ_OAUTH_BIZ_APP_MODUEL';

COMMENT ON COLUMN OAUTH_BIZ_APP_USER.LOGIN_ID IS '模块code';

COMMENT ON COLUMN OAUTH_BIZ_APP_USER.APP_CODE IS 'AppCode和表Um_oauth_app相关';

COMMENT ON COLUMN OAUTH_BIZ_APP_USER.CREATE_DATE IS '创建时间';

COMMENT ON COLUMN OAUTH_BIZ_APP_USER.ISSSO IS '当为Y表示进行单一登录，否则就进行不进行登录';

COMMENT ON COLUMN OAUTH_BIZ_APP_USER.COMMONUSE IS '常用应用标示';



ALTER TABLE OAUTH_BIZ_RT_TICKET
 DROP PRIMARY KEY CASCADE;

DROP TABLE OAUTH_BIZ_RT_TICKET CASCADE CONSTRAINTS;

CREATE TABLE OAUTH_BIZ_RT_TICKET
(
  ID                   NUMBER                   NOT NULL,
  REFRESHTOKEN         VARCHAR2(150 BYTE)       NOT NULL,
  CASTICKET            VARCHAR2(200 BYTE)       NOT NULL,
  REFRESHTOKENENDDATE  DATE,
  CASTICKETENDDATE     DATE,
  CREATEDATE           DATE                     NOT NULL
)
TABLESPACE SHDIPP_DATA
RESULT_CACHE (MODE DEFAULT)
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          1M
            NEXT             1M
            MAXSIZE          UNLIMITED
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
            FLASH_CACHE      DEFAULT
            CELL_FLASH_CACHE DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;

COMMENT ON TABLE OAUTH_BIZ_RT_TICKET IS 'RT和ticket的关系表';

COMMENT ON COLUMN OAUTH_BIZ_RT_TICKET.ID IS '序列号SEQ_ Oauth_Biz_Rt_Ticket';

COMMENT ON COLUMN OAUTH_BIZ_RT_TICKET.REFRESHTOKEN IS 'EpOa的rt';

COMMENT ON COLUMN OAUTH_BIZ_RT_TICKET.CASTICKET IS 'EpCas的ticket';

COMMENT ON COLUMN OAUTH_BIZ_RT_TICKET.REFRESHTOKENENDDATE IS 'Rt失效时间';

COMMENT ON COLUMN OAUTH_BIZ_RT_TICKET.CASTICKETENDDATE IS 'Ticket失效时间';

COMMENT ON COLUMN OAUTH_BIZ_RT_TICKET.CREATEDATE IS '当前记录创建时间';




ALTER TABLE OAUTH_BIZ_USER_RT
 DROP PRIMARY KEY CASCADE;

DROP TABLE OAUTH_BIZ_USER_RT CASCADE CONSTRAINTS;

CREATE TABLE OAUTH_BIZ_USER_RT
(
  ID             NUMBER                         NOT NULL,
  REFRESH_TOKEN  VARCHAR2(150 CHAR)             NOT NULL,
  LOGIN_ID       VARCHAR2(100 CHAR)             NOT NULL,
  CREATE_DATE    DATE                           NOT NULL,
  END_DATE       DATE,
  RT_STATUS      VARCHAR2(2 BYTE)
)
TABLESPACE SHDIPP_DATA
RESULT_CACHE (MODE DEFAULT)
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          1M
            NEXT             1M
            MAXSIZE          UNLIMITED
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
            FLASH_CACHE      DEFAULT
            CELL_FLASH_CACHE DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;

COMMENT ON TABLE OAUTH_BIZ_USER_RT IS 'User-Refreshtoken关系表';

COMMENT ON COLUMN OAUTH_BIZ_USER_RT.ID IS 'Sequence ：SEQ_OAUTH_BIZ_ACCESSTOKEN';

COMMENT ON COLUMN OAUTH_BIZ_USER_RT.REFRESH_TOKEN IS 'ACCESS_TOKEN';

COMMENT ON COLUMN OAUTH_BIZ_USER_RT.LOGIN_ID IS '记录ApiCode或者App Model Code';

COMMENT ON COLUMN OAUTH_BIZ_USER_RT.CREATE_DATE IS '创建时间';

COMMENT ON COLUMN OAUTH_BIZ_USER_RT.END_DATE IS '失效时间';

COMMENT ON COLUMN OAUTH_BIZ_USER_RT.RT_STATUS IS '当状态为L 表示当前RT是临时状态，当状态为Z，则表示为正常状态';



ALTER TABLE OAUTH_BIZ_WX_USER
 DROP PRIMARY KEY CASCADE;

DROP TABLE OAUTH_BIZ_WX_USER CASCADE CONSTRAINTS;

CREATE TABLE OAUTH_BIZ_WX_USER
(
  ID          NUMBER,
  WX_ID       VARCHAR2(100 CHAR)                NOT NULL,
  LOGIN_ID    VARCHAR2(100 CHAR)                NOT NULL,
  BOUND_DATE  DATE
)
TABLESPACE SHDIPP_DATA
RESULT_CACHE (MODE DEFAULT)
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MAXSIZE          UNLIMITED
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
            FLASH_CACHE      DEFAULT
            CELL_FLASH_CACHE DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE UNIQUE INDEX OAUTH_BIZ_WX_USER_PK ON OAUTH_BIZ_WX_USER
(ID)
LOGGING
TABLESPACE SHDIPP_IDX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MAXSIZE          UNLIMITED
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
            FLASH_CACHE      DEFAULT
            CELL_FLASH_CACHE DEFAULT
           )
NOPARALLEL;


CREATE INDEX IDX_OAUTH_BIZ_USER_RT_LOGIN_ID ON OAUTH_BIZ_USER_RT
(LOGIN_ID)
LOGGING
TABLESPACE SHDIPP_IDX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MAXSIZE          UNLIMITED
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
            FLASH_CACHE      DEFAULT
            CELL_FLASH_CACHE DEFAULT
           )
NOPARALLEL;


CREATE INDEX IDX_OAUTH_BIZ_USER_RT_RT ON OAUTH_BIZ_USER_RT
(REFRESH_TOKEN)
LOGGING
TABLESPACE SHDIPP_IDX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MAXSIZE          UNLIMITED
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
            FLASH_CACHE      DEFAULT
            CELL_FLASH_CACHE DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX PK_OAUTH_BIZ_USER_RT ON OAUTH_BIZ_USER_RT
(ID)
LOGGING
TABLESPACE SHDIPP_IDX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          1M
            NEXT             1M
            MAXSIZE          UNLIMITED
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
            FLASH_CACHE      DEFAULT
            CELL_FLASH_CACHE DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX PK_IP_LOG_ID ON BASE_IP_IN_LOG
(ID)
LOGGING
TABLESPACE SHDIPP_IDX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MAXSIZE          UNLIMITED
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
            FLASH_CACHE      DEFAULT
            CELL_FLASH_CACHE DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX PK_OAUTH_BIZ_RT_TICKET ON OAUTH_BIZ_RT_TICKET
(ID)
LOGGING
TABLESPACE SHDIPP_IDX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          1M
            NEXT             1M
            MAXSIZE          UNLIMITED
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
            FLASH_CACHE      DEFAULT
            CELL_FLASH_CACHE DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX PK_OAUTH_BIZ_APP_USER ON OAUTH_BIZ_APP_USER
(ID)
LOGGING
TABLESPACE SHDIPP_IDX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          1M
            NEXT             1M
            MAXSIZE          UNLIMITED
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
            FLASH_CACHE      DEFAULT
            CELL_FLASH_CACHE DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX PK_OAUTH_BIZ_APP ON OAUTH_BIZ_APP
(ID)
LOGGING
TABLESPACE SHDIPP_IDX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          1M
            NEXT             1M
            MAXSIZE          UNLIMITED
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
            FLASH_CACHE      DEFAULT
            CELL_FLASH_CACHE DEFAULT
           )
NOPARALLEL;

ALTER TABLE BASE_IP_IN_LOG ADD (
  CONSTRAINT PK_IP_LOG_ID
  PRIMARY KEY
  (ID)
  USING INDEX PK_IP_LOG_ID
  ENABLE VALIDATE);

ALTER TABLE OAUTH_BIZ_APP ADD (
  CONSTRAINT PK_OAUTH_BIZ_APP
  PRIMARY KEY
  (ID)
  USING INDEX PK_OAUTH_BIZ_APP
  ENABLE VALIDATE);

ALTER TABLE OAUTH_BIZ_APP_USER ADD (
  CONSTRAINT PK_OAUTH_BIZ_APP_USER
  PRIMARY KEY
  (ID)
  USING INDEX PK_OAUTH_BIZ_APP_USER
  ENABLE VALIDATE);

ALTER TABLE OAUTH_BIZ_RT_TICKET ADD (
  CONSTRAINT PK_OAUTH_BIZ_RT_TICKET
  PRIMARY KEY
  (ID)
  USING INDEX PK_OAUTH_BIZ_RT_TICKET
  ENABLE VALIDATE);

ALTER TABLE OAUTH_BIZ_SID_RT ADD (
  CONSTRAINT PK_OAUTH_BIZ_SID_RT
  PRIMARY KEY
  (ID)
  USING INDEX PK_OAUTH_BIZ_SID_RT
  ENABLE VALIDATE);

ALTER TABLE OAUTH_BIZ_USER_RT ADD (
  CONSTRAINT PK_OAUTH_BIZ_USER_RT
  PRIMARY KEY
  (ID)
  USING INDEX PK_OAUTH_BIZ_USER_RT
  ENABLE VALIDATE);

ALTER TABLE OAUTH_BIZ_WX_USER ADD (
  CONSTRAINT OAUTH_BIZ_WX_USER_PK
  PRIMARY KEY
  (ID)
  USING INDEX OAUTH_BIZ_WX_USER_PK
  ENABLE VALIDATE);