<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>글 수정</title>
</head>

<body>
    <form>
        <h2>제목</h2>
        <table border="1">
            <tbody>
            	<tr>
            		<th>작성자</th>
            		<td>작성자</td>
            	</tr>
                <tr>
                    <th>내용</th>
                    <td>
                    내용                     
                    </td>
                </tr>
                <tr>
                    <th>해시 태그</th>
                    <td>백엔드, 게시판</td>
                </tr>
            </tbody>
        </table>
        <br>
        <input class="btn" type="submit" value="수정하기" />
    </form>
    
    <h3>댓글</h3>
    <form>
	    <input type="text" placeholder="작성자"/>
	    <input type="text" placeholder="비밀번호"/>
	    <input type="text" placeholder="댓글을 입력하세요"/>
	    <input type="submit" value="제출"/>
    </form>
    <br>
    <br>
    <table border="1">
        <tbody>
        	<tr>
        		<td>제임슨</td>
        		<td>글이 좋아요</td>
        	</tr>
        </tbody>
    </table>
</body>

</html>