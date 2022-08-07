<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>글 수정</title>
</head>

<body>
    <form action="edit" method="get">
        <h2>${post.title}</h2>
        <table border="1">
            <tbody>
            	<tr>
            		<th>작성자</th>
            		<td>${post.userId}</td>
            	</tr>
            	<tr>
            		<th>작성일시</th>
            		<td><fmt:formatDate value="${post.postDate}" pattern="yyyy-MM-dd hh-mm-ss"/></td>
            	</tr>
            	<tr>
            		<th>수정일시</th>
            		<td><fmt:formatDate value="${post.modifyDate}" pattern="yyyy-MM-dd hh-mm-ss"/></td>
            	</tr>
                <tr>
                    <th>내용</th>
                    <td width="500px">${post.content}</td>
                </tr>
            </tbody>
        </table>    
        <span>&nbsp;&nbsp;
        <c:forEach var="hashtag" items="${post.hashtags}">
        #${hashtag}, &nbsp;
        </c:forEach>   
        </span>   
        <br>        
        <br>
        <input type="text" name="password" placeholder="비밀번호를 입력하세요">
        <input class="btn" type="submit" value="수정하기" />
    </form>
    <form action="delete" method="delete">    	
        <input type="text" name="password" placeholder="비밀번호를 입력하세요">
        <input class="btn" type="submit" value="삭제하기" />
    </form>
    
    <h3>댓글</h3>
    <form action="registComment" method="post">
    	<input style="display:none" type="number" readonly name="postNumber" value="${post.postNumber}"/>
	    <input type="text" name="writerId" placeholder="작성자"/>
	    <input type="text" name="password" placeholder="비밀번호"/>
	    <input type="text" name="content" placeholder="댓글을 입력하세요"/>
	    <input type="submit" value="제출"/>
    </form>
    <br>
    <br>
    <table border="1">
        <tbody>
            <c:forEach var="comment" items="${post.commentList}">
            <tr>
        		<td>${comment.userId}</td>
        		<td>${comment.content}</td>
        		<td><fmt:formatDate value="${comment.commentDate}" pattern="yyyy-MM-dd hh-mm-ss"/></td>
        	</tr>
        	</c:forEach> 
        	
        </tbody>
    </table>
</body>

</html>