<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<%
	Date date = new Date();
	long time3Day = date.getTime() - (long)(24 * 60 * 60 * 1000 * 3);
	pageContext.setAttribute("time", time3Day);
%>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>글 목록</title>
  <link rel="stylesheet" type="text/css" href="/board/css/styles.css">
</head>

<body>
  <h1>글 목록</h1>
  <span>전체 글 : ${board.totalPostCount} &nbsp;&nbsp; 전체 댓글 수 : ${board.totalCommentCount}</span>
  
  <table class="listTable" border="1">
    <thead>
      <tr>
        <th scope="col">번호</th>
        <th scope="col" width="400px">제목</th>
        <th scope="col">작성자</th>
        <th scope="col">작성일시</th>
        <th scope="col">댓글수</th>
        <th scope="col">조회수</th>
        <th scope="col">좋아요</th>
      </tr>
    </thead>
    <tbody>    
    <c:forEach var="postView" items="${board.list}">
    <c:set var="newPost" value=""></c:set>
    <c:if test="${postView.postDate.time >= time}">
    <c:set var="newPost" value="[new]"></c:set>
    </c:if>
   	  <tr>
        <td>${postView.postNumber}</td>
        <td class="title">
        	<form action="detail" method="post">
        		<input hidden type="text" readonly name="postNumber" value="${postView.postNumber}" />  
        		<input class="listTitleHyperLink" type="submit" value="${postView.title} ${newPost}" readonly />
        	</form>
        <%-- <a href="detail?postNumber=${postView.postNumber}">${postView.title} ${newPost}</a> --%>
        </td>
        <td>${postView.writerId}</td>
        <td><fmt:formatDate value="${postView.postDate}" pattern="yyyy-MM-dd"/></td>
        <td>${postView.commentCount}</td>
        <td>${postView.hit}</td>
        <td>${postView.like}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <br>
  <form action="list" method="post">
  	<select name="field">
 		<option value='title' selected>제목</option>
 		<option value='writerId'>작성자</option>
 		<option value='hashtag'>해시태그</option>
 		<option value='content'>내용</option>
	</select>
	<input type="text" name="searchContent" placeholder="검색어를 입력하세요"/> 		
	<input type="submit" value="검색" />  	
  </form>  
  <br>
  <button><a href="write">새 글 작성하기</a></button>
</body>

</html>