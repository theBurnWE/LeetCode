INSERT INTO UM_SUBSYSTEM (SUBSYSTEM_ID,
                          NAME,
                          URL,
                          FLAG,
                          MENO,
                          CREATE_DATE,
                          MODIFY_DATE,
                          LOGIN_URL,
                          BROKER_URL,
                          SUB_SYSTEM_CODE,
                          MODIFY_PASS_URL)
     VALUES (SEQ_UM_SUBSYSTEM.NEXTVAL,
             '数字贸易交易促进平台',
             'F1927A432776AB6FD2CE7E6FB9114820',
             0,
             '',
             SYSDATE,
             SYSDATE,
             '',
             '',
             'SHDIPP',
             '');

INSERT INTO UM_ROLE (NAME,
                     FLAG,
                     MENO,
                     SUBSYSTEM_ID,
                     CREATE_DATE,
                     MODIFY_DATE,
                     ROLE_CODE,
                     ID,
                     TOWINXPORTAL)
     VALUES ('管理员',
             '0',
             '',
             1,
             SYSDATE,
             SYSDATE,
             'SHDIPP_ADMIN',
             SEQ_UM_ROLE.NEXTVAL,
             '');
			 
INSERT INTO UM_ROLE (NAME,
                     FLAG,
                     MENO,
                     SUBSYSTEM_ID,
                     CREATE_DATE,
                     MODIFY_DATE,
                     ROLE_CODE,
                     ID,
                     TOWINXPORTAL)
     VALUES ('注册用户',
             '0',
             '',
             1,
             SYSDATE,
             SYSDATE,
             'Default-App-User-SHDIPP_USER',
             SEQ_UM_ROLE.NEXTVAL,
             '');			 

/* Formatted on 2019/8/23 19:02:18 (QP5 v5.256.13226.35538) */
INSERT INTO UM_ORG (ID,
                    ORG_PID,
                    ORG_CODE,
                    NAME,
                    FLAG,
                    MENO,
                    CREATE_DATE,
                    MODIFY_DATE,
                    RESVER1,
                    RESVER2,
                    RESVER3,
                    BODY_TYPE,
                    ISP_ORG)
     VALUES (SEQ_UM_ORG.NEXTVAL,
             0,
             'SHDIPP_ORG',
             '数字贸易交易促进平台',
             '0',
             NULL,
             SYSDATE,
             SYSDATE,
             NULL,
             NULL,
             NULL,
             'person',
             NULL);
			 
INSERT INTO OAUTH_BIZ_APP (ID,
                           APP_CODE,
                           APP_NAME,
                           DOMAIN,
                           IP,
                           APP_SECRET,
                           IS_OPEN,
                           CLIENT_ID,
                           CREATE_DATE,
                           WEB_URL,
                           ICON_URL,
                           ORDERNO,
                           APPTYPE,
                           SERVICEURL,
                           DESPLAY_MENU,
                           AUTHOR_CODE,
                           PUBLIC_KEY,
                           VALIDATE_NUM,
                           ISSSO)
     VALUES (SEQ_OAUTH_BIZ_APP.NEXTVAL,
             'SHDIPP',
             '数字贸易交易促进平台',
             'shdipp',
             '',
             '',
             'Y',
             'F1927A432776AB6FD2CE7E6FB9114820',
             SYSDATE,
             '/',
             '/',
             0,
             'SCP',
             '/',
             '',
             '',
             '',
             '',
             '');

COMMIT;