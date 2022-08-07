<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>글 작성</title>
</head>

<body>	
    <form action="regist" method="post">
        <h2>글 작성</h2>
        <table>
            <tbody>
            	<tr>
                    <th>작성자</th>
                    <td>
                        <input type="text" name="writerId" required placeholder="작성자" />
                    </td>
                </tr>
                <tr>
                    <th>제목</th>
                    <td>
                        <input type="text" name="title" required placeholder="제목" />
                    </td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td>
                        <input type="text" name="content" required placeholder="내용을 입력하세요" />
                    </td>
                </tr>
                <tr>
                    <th>비밀번호</th>
                    <td>
                        <input type="text" name="password" required placeholder="비밀번호를 입력하세요" />
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
    <script src="js/write.js"></script>
    <script src="js/jquery.min.js"></script>
</body>
</html>