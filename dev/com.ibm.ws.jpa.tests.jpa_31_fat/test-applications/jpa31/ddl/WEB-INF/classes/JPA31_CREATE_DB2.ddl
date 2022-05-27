CREATE TABLE UUIDUUIDGENENTITY (ID VARCHAR(255) NOT NULL, STRDATA VARCHAR(255), PRIMARY KEY (ID));
CREATE TABLE UUIDIDCLASSENTITY (UUID_ID VARCHAR(255) NOT NULL, L_ID BIGINT NOT NULL, STRDATA VARCHAR(255), PRIMARY KEY (UUID_ID, L_ID));
CREATE TABLE UUIDAUTOGENENTITY (ID VARCHAR(255) NOT NULL, STRDATA VARCHAR(255), PRIMARY KEY (ID));
CREATE TABLE UUIDENTITY (ID VARCHAR(255) NOT NULL, STRDATA VARCHAR(255), PRIMARY KEY (ID));
CREATE TABLE UUIDEMBEDDABLEIDENTITY (STRDATA VARCHAR(255), ID VARCHAR(255) NOT NULL, PRIMARY KEY (ID));

CREATE TABLE XMLUUIDUUIDGENENTITY (ID VARCHAR(255) NOT NULL, STRDATA VARCHAR(255), PRIMARY KEY (ID));
CREATE TABLE XMLUUIDIDCLASSENTITY (UUID_ID VARCHAR(255) NOT NULL, L_ID BIGINT NOT NULL, STRDATA VARCHAR(255), PRIMARY KEY (UUID_ID, L_ID));
CREATE TABLE XMLUUIDAUTOGENENTITY (ID VARCHAR(255) NOT NULL, STRDATA VARCHAR(255), PRIMARY KEY (ID));
CREATE TABLE XMLUUIDENTITY (ID VARCHAR(255) NOT NULL, STRDATA VARCHAR(255), PRIMARY KEY (ID));
CREATE TABLE XMLUUIDEMBEDDABLEIDENTITY (STRDATA VARCHAR(255), EID VARCHAR(255) NOT NULL, PRIMARY KEY (EID));


CREATE TABLE QUERYDATETIMEENTITY (ID INTEGER NOT NULL, LOCALDATEDATA VARCHAR(255), LOCALDATETIMEDATA VARCHAR(255), LOCALTIMEDATA VARCHAR(255), PRIMARY KEY (ID));
CREATE TABLE QUERYENTITY (ID INTEGER NOT NULL, DOUBLEVAL FLOAT, FLOATVAL FLOAT, INTVAL INTEGER, LONGVAL BIGINT, STRVAL VARCHAR(255), PRIMARY KEY (ID));


INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0);
