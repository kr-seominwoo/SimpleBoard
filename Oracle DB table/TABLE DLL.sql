CREATE SEQUENCE POST_NUMBER_SEQ START WITH 1 CACHE 20;

CREATE TABLE POST
(
    USER_ID NVARCHAR2(20) NOT NULL,
    PASSWORD NVARCHAR2(64) NOT NULL,
    TITLE NVARCHAR2(50) NOT NULL,
    CONTENT NCLOB NOT NULL,
    POST_DATE DATE NOT NULL,
    MODIFY_DATE DATE NOT NULL,
    HASHTAGS NVARCHAR2(50),
    "LIKE" NUMBER DEFAULT 0,
    UNLIKE NUMBER DEFAULT 0,
    HIT NUMBER DEFAULT 0,
    POST_NUMBER NUMBER NOT NULL
);

CREATE SEQUENCE COMMENT_NUMBER_SEQ START WITH 1 CACHE 20;

CREATE TABLE "COMMENT"
(
    USER_ID NVARCHAR2(20) NOT NULL,
    PASSWORD NVARCHAR2(64) NOT NULL,
    CONTENT NCLOB NOT NULL,
    COMMENT_DATE DATE NOT NULL,
    POST_NUMBER NUMBER NOT NULL,
    COMMENT_NUMBER NUMBER NOT NULL
);

CREATE VIEW POST_VIEW
AS
SELECT P.POST_NUMBER, P.TITLE, P.USER_ID, P.POST_DATE, P.HIT, P."LIKE", COUNT(C.COMMENT_NUMBER) COMMENT_COUNT
FROM POST P LEFT JOIN "COMMENT" C ON P.POST_NUMBER = C.POST_NUMBER AND C.CONTENT NOT LIKE '삭제된 댓글입니다'
GROUP BY P.POST_NUMBER, P.TITLE, P.USER_ID, P.POST_DATE, P.HIT, P."LIKE"
