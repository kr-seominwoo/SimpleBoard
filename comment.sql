--------------------------------------------------------
--  파일이 생성됨 - 토요일-8월-06-2022   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table COMMENT
--------------------------------------------------------

  CREATE TABLE "POST_ADMIN"."COMMENT" 
   (	"USER_ID" NVARCHAR2(20), 
	"PASSWORD" NVARCHAR2(64), 
	"TITLE" NVARCHAR2(50), 
	"CONTENT" NCLOB
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "POST_DATA1" 
 LOB ("CONTENT") STORE AS SECUREFILE (
  TABLESPACE "POST_DATA1" ENABLE STORAGE IN ROW 4000 CHUNK 8192
  NOCACHE LOGGING  NOCOMPRESS  KEEP_DUPLICATES ) ;
--------------------------------------------------------
--  Constraints for Table COMMENT
--------------------------------------------------------

  ALTER TABLE "POST_ADMIN"."COMMENT" MODIFY ("USER_ID" NOT NULL ENABLE);
  ALTER TABLE "POST_ADMIN"."COMMENT" MODIFY ("PASSWORD" NOT NULL ENABLE);
  ALTER TABLE "POST_ADMIN"."COMMENT" MODIFY ("TITLE" NOT NULL ENABLE);
  ALTER TABLE "POST_ADMIN"."COMMENT" MODIFY ("CONTENT" NOT NULL ENABLE);
