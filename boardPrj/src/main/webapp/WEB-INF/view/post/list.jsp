<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>글 목록</title>
</head>

<body>
  <h1>글 목록</h1>
  <span>전체 글 : ${board.totalPostCount} &nbsp;&nbsp; 전체 댓글 수 : ${board.totalCommentCount}</span>
  
  <table border="1">
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
    <tbody>
    <c:forEach var="postView" items="${board.list}">
    <form>
      <tr>
        <td>${postView.postNumber}</td>
        <td><a href="detail?postNumber=${postView.postNumber}">${postView.title}</a></td>
        <td>${postView.writerId}</td>
        <td><fmt:formatDate value="${postView.postDate}" pattern="yyyy-MM-dd"/></td>
        <td>${postView.commentCount}</td>
        <td>${postView.hit}</td>
        <td>${postView.like}</td>
      </tr>
    </form>

    </c:forEach>

    </tbody>
  </table>
  <br>
  <button><a href="write">새 글 작성하기</a></button>
</body>

</html>