1. 게시글(POST)

작성자 아이디, 비밀번호, 게시글 번호, 제목, 본문, 작성 날짜, 수정 날짜, 해시태그목록, 해시태그수, 좋아요, 싫어요, 조회수

COLUMN_NAME | DATA_TYPE | NULLABLE | DATA_DEFAULT
-- | -- | -- | --
USER_ID | NVARCHAR2(20) | No | (null)
PASSWORD | NVARCHAR2(64) | No | (null)
POST_NUMBER | NUMBER | No | (null) 
TITLE | NVARCHAR2(50) | No | (null)
CONTENT | NCLOB | No | (null)
POST_DATE | DATE | No | SYSDATE
MODIFY_DATE | DATE | No | SYSDATE
HASHTAGS | NVARCHAR2(50) | Yes | (null)
LIKE | NUMBER | No | 0
UNLIKE | NUMBER | No | 0
HIT | NUMBER | No | 0


2. 댓글(COMMENT)

사용자 아이디, 비밀번호, 본문, 작성 날짜, 수정 날짜

COLUMN_NAME | DATA_TYPE | NULLABLE | DATA_DEFAULT
-- | -- | -- | --
USER_ID | NVARCHAR2(20) | No | (null)
PASSWORD | NVARCHAR2(64) | No | (null)
CONTENT | NCLOB | No | (null)
COMMENT_DATE | DATE | No | SYSDATE
POST_NUMBER | NUMBER | No | (null)
COMMENT_NUMBER | NUMBER | No | (null)

3. 게시글 뷰(POST_VIEW)

사용자 아이디, 게시글 번호, 제목, 작성 날짜, 좋아요, 조회수, 댓글 수

COLUMN_NAME | DATA_TYPE | NULLABLE | DATA_DEFAULT
-- | -- | -- | --
USER_ID | NVARCHAR2(20) | No | (null)
POST_NUMBER | NUMBER | No | (null) 
TITLE | NVARCHAR2(50) | No | (null)
POST_DATE | DATE | No | SYSDATE
LIKE | NUMBER | No | 0
HIT | NUMBER | No | 0
COMMENT_COUNT | NUMBER | No | 0
