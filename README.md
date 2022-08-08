# 게시판 프로젝트
spring을 활용하여 글과 댓글을 작성할 수 있는 게시판을 구현한다

## 기능
1. 작성
    * 사용자는 게시글과 댓글을 작성할 수 있으며 텍스트만 가능하다
    * 게시글에는 해시태그를 최대 5개를 등록할 수 있다    

2. 삭제
    * 사용자는 본인이 작성한 게시글을 삭제할 수 있다
    * 사용자는 본인이 작성한 댓글을 삭제할 수 있다

3. 조회
    * 사용자는 전체 글 목록을 조회할 수 있다
    * 게시글 목록에는 제목, 작성자, 작성일시, 댓글 수, 조회수, 좋아요수를 표시한다
    * 사용자는 글의 제목을 클릭하여 세부내용을 볼 수 있다

4. 본인 확인을 위한 비밀번호는 암호화하여 DB에 저장한다

## 설치방법 (IDE에서 사용)
### Prerequisites
* JRE 17
* IDE
   * Spring Tools Suite(STS)
      -> maven 사용
* git
* apahce tomcat 9 version

### Steps
1. git command line

   ``` shell
   git clone https://github.com/TotheMoon12/nts-0825-tjalsdnss.git
   ```

2. STS에서 불러오기
   ``` shell
   FILE -> Import -> Maven -> Existing Maven Projects
   ```

3. Maven build
   ``` shell
   내 프로젝트 -> Run As -> Maven install
   내 프로젝트 -> Maven -> Update project
   ```
   
4. Server 생성 및 실행
   ``` shell
   Windows -> Show View -> other -> server
   server 탭
   creat a new Server -> Apache -> tomcat v9.0 server ->
   add -> Tomcat installation directory에 설치한 톰캣 디렉토리 지정 -> finish
   ```

