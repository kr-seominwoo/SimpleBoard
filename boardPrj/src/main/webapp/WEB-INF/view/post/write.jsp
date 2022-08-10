<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>글 작성</title>
    <link rel="stylesheet" type="text/css" href="/board/css/styles.css">
</head>

<body>	
	<div class="container">
	    <form action="regist" method="post">
	        <h2>글 작성</h2>
	        <table>
	            <tbody>
	            	<tr>
	                    <th>작성자</th>
	                    <td>
	                        <input type="text" name="writerId" placeholder="작성자" required/>
	                    </td>
	                </tr>
	                <tr>
	                    <th>제목</th>
	                    <td>
	                        <input type="text" name="title" placeholder="제목" required/>
	                    </td>
	                </tr>
	                <tr>
	                    <th>내용</th>
	                    <td>
	                    	<textarea class="content" name="content" placeholder="내용을 입력하세요" required></textarea>
	                    </td>
	                </tr>
	                <tr>
	                    <th>비밀번호</th>
	                    <td>
	                        <input type="text" name="password" placeholder="비밀번호를 입력하세요" required/>
	                    </td>
	                </tr>
	            </tbody>
	        </table>
	        <input id="hashtag-input" type="text" placeholder="해시태그" />
	        <button type="button" onclick="addHashTag()">추가</button>
	        <table id="hashtag-table">  
	            <thead>
	                <tr>
	                  <th scope="col">해시태그</th>
	                </tr>
	              </thead>      
	            <tbody>
	            </tbody>            
	        </table>	        
	        <input class="btn" type="submit" value="게시하기" />	
	    </form>
	</div>

    <script src="/board/js/jquery.min.js"></script>
    <script src="/board/js/write.js"></script>
</body>
</html>