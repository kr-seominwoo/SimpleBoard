--------------------------------------------------------
--  파일이 생성됨 - 토요일-8월-06-2022   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table POST
--------------------------------------------------------

  CREATE TABLE "POST_ADMIN"."POST" 
   (	"USER_ID" NVARCHAR2(20), 
	"PASS_WORD" NVARCHAR2(64), 
	"TITLE" NVARCHAR2(50), 
	"CONTENT" NCLOB, 
	"POST_DATE" DATE, 
	"FIX_DATE" DATE, 
	"HASHTAGS" NVARCHAR2(50), 
	"HASHTAGS_COUNT" NUMBER, 
	"LIKE" NUMBER DEFAULT 0, 
	"UNLIKE" NUMBER DEFAULT 0, 
	"HIT" NUMBER DEFAULT 0
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "POST_DATA1" 
 LOB ("CONTENT") STORE AS SECUREFILE (
  TABLESPACE "POST_DATA1" ENABLE STORAGE IN ROW 4000 CHUNK 8192
  NOCACHE LOGGING  NOCOMPRESS  KEEP_DUPLICATES ) ;
--------------------------------------------------------
--  Constraints for Table POST
--------------------------------------------------------

  ALTER TABLE "POST_ADMIN"."POST" MODIFY ("USER_ID" NOT NULL ENABLE);
  ALTER TABLE "POST_ADMIN"."POST" MODIFY ("PASS_WORD" NOT NULL ENABLE);
  ALTER TABLE "POST_ADMIN"."POST" MODIFY ("TITLE" NOT NULL ENABLE);
  ALTER TABLE "POST_ADMIN"."POST" MODIFY ("CONTENT" NOT NULL ENABLE);
  ALTER TABLE "POST_ADMIN"."POST" MODIFY ("POST_DATE" NOT NULL ENABLE);
  ALTER TABLE "POST_ADMIN"."POST" MODIFY ("FIX_DATE" NOT NULL ENABLE);
