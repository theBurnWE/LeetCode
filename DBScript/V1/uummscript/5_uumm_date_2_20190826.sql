INSERT INTO UM_USER (ID,
                     ORG_ID,
                     LOGIN_ID,
                     LOGIN_PASS,
                     NAME,
                     USER_LEVEL,
                     FLAG,
                     CREATE_DATE,
                     USER_TYPE)
     VALUES (SEQ_UM_USER.NEXTVAL,
             1,
             'admin',
             'Sd7F+4r07u98lef1xmyK5g==',
             '管理员',
             2,
             0,
             SYSDATE,
             0);

INSERT INTO OAUTH_BIZ_APP_USER (ID,
                                LOGIN_ID,
                                APP_CODE,
                                CREATE_DATE)
     VALUES (SEQ_OAUTH_BIZ_APP_USER.NEXTVAL,
             'admin',
             'SHDIPP',
             SYSDATE);

INSERT INTO UM_USER_ROLE (ID, USER_ID, ROLE_ID)
     VALUES (SEQ_UM_USER_ROLE.NEXTVAL, 1, 1);

COMMIT;