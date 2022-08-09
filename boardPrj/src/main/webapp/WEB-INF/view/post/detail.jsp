<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="en">

<head>
<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport"
		content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>글 수정</title>
	<link rel="stylesheet" type="text/css" href="/board/css/styles.css">
</head>

<body>
	<h2>${post.title}</h2>
	<table border="1" class="detailTable">
		<tbody>
			<tr>
				<th>작성자</th>
				<td>${post.userId}</td>
			</tr>
			<tr>
				<th>작성일시</th>
				<td><fmt:formatDate value="${post.postDate}" pattern="yyyy-MM-dd hh-mm-ss" /></td>
			</tr>
			<tr>
				<th>수정일시</th>
				<td><fmt:formatDate value="${post.modifyDate}" pattern="yyyy-MM-dd hh-mm-ss" /></td>
			</tr>
			<tr>
				<th>내용</th>
				<td width="500px">${post.content}</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${post.hit}</td>
			</tr>
			<tr>
				<th>좋아요</th>
				<td>${post.like}</td>
			</tr>
		</tbody>
	</table>
	<form></form>
	<form action="like" method="post">
		<input hidden type="number" readonly name="postNumber" value="${post.postNumber}" />  
		<input type="submit" value="👍" />
	</form>
	<form action="unlike" method="post">
		<input hidden type="number" readonly name="postNumber" value="${post.postNumber}" />  
		<input type="submit" value="👎">
	</form>
	
	<span>&nbsp;&nbsp; 
	<c:forEach var="hashtag" items="${post.hashtags}">
        #${hashtag}, &nbsp;
    </c:forEach>
	</span>
	<br>
	<br>
	<form action="edit" method="post">
		<input hidden type="text" readonly name="title" value="${post.title}" />
		<input hidden type="text" readonly name="content" value="${post.content}" />  
		<c:forEach var="hashtag" items="${post.hashtags}">
		<input hidden type="text" readonly name="hashTag" value="${hashtag}" />
		</c:forEach>
		<input hidden type="number" readonly name="postNumber" value="${post.postNumber}" />  
		<input type="text" name="password" required placeholder="비밀번호를 입력하세요">
		<input class="btn" type="submit" value="수정하기" />
	</form>
	<form action="delete" method="post">
		<input hidden type="number" readonly name="postNumber" value="${post.postNumber}" /> 
		<input type="text" name="password" required placeholder="비밀번호를 입력하세요">
		<input class="btn" type="submit" value="삭제하기" />
	</form>

	<h3>댓글</h3>
	<form action="registComment" method="post">
		<input hidden type="number" readonly name="postNumber" value="${post.postNumber}" /> 
		<input type="text" name="writerId" placeholder="작성자" />
		<input type="text" name="password" placeholder="비밀번호" />
		<input type="text" name="content" required placeholder="댓글을 입력하세요" /> <input type="submit" value="제출" />
	</form>
	<br>
	<br>
	<table border="1">
		<thead>
			<tr>
				<th scope="col">작성자</th>
				<th scope="col">내용</th>
				<th scope="col">작성일시</th>
				<th scope="col">삭제 비밀번호</th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>		
			<c:forEach var="comment" items="${post.commentList}">
			<form action="deleteComment" method="post">
				<input hidden type="number" readonly name="postNumber" value="${comment.postNumber}" />
				<input hidden type="number" readonly name="commentNumber" value="${comment.commentNumber}" />
				<tr>
					<td>${comment.userId}</td>
					<td width="300px">${comment.content}</td>
					<td><fmt:formatDate value="${comment.commentDate}" pattern="yyyy-MM-dd hh-mm-ss" /></td>
					<td><input type="text" name="password" required placeholder="비밀번호를 입력하세요"/></td>
					<td><input type="submit" value="X"></td>
				</tr>				
			</form>
			</c:forEach>

		</tbody>
	</table>
</body>

</html>