<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>글 수정</title>
    <link rel="stylesheet" type="text/css" href="/board/css/styles.css">
</head>

<body>
	<div class="container">
	    <form action="update" method="post">
	        <h2>글 수정</h2>
	        <table>
	            <tbody>
	                <tr>
	                    <th>제목</th>
	                    <td>
	                        <input type="text" name="title" placeholder="제목" value="${title}" required/>
	                    </td>
	                </tr>
	                <tr>
	                    <th>내용</th>
	                    <td>
	                    	<textarea class="content" name="content placeholder="내용을 입력하세요" required>${content }</textarea>
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
	            <c:forEach var="tag" items="${hashTag}">
	            	<tr><td><input type="text" name="hashTag" readonly value="${tag}"/><button type="button" onclick="removeHashTag(this)">X</button></td></tr>
	            </c:forEach>
	            </tbody>            
	        </table>
	        <input hidden type="number" readonly name="postNumber" value="${postNumber}" />
	        <input class="btn" type="submit" value="수정하기" />
	    </form>
	</div>

    <script src="/board/js/jquery.min.js"></script>
    <script src="/board/js/write.js"></script>
</body>
</html>