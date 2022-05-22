<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ page import = "common.DBConnPool" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h3> DBCP 커넥션 테스트</h3>
<%
	DBConnPool pool = new DBConnPool();	//DBConnecion 기본생성자 불러오기
	pool.close();  /// 커넥션 객체 반납

%>
</body>
</html>