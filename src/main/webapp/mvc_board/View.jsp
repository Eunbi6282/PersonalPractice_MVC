<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ tablib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 정보 보기</title>
</head>
<body>
	<h2> 파일 첨부형 게시판 -상세보기 (View)</h2>
	
	<table border = "1" width = "90%">
		<colgroup>
			<col width = "15%"/> <col width = "35%" />
			<col width = "15%"/> <col width = "*" />
		</colgroup>
		
		<!-- 게시글 정보 출력 -->
		<tr>
			<td> 번호</td> <td> ${dto.idx }</td>
			<td> 작성자</td> <td> ${dto.name }</td>
			
			
		</tr>
	
	
	</table>
</body>
</html>