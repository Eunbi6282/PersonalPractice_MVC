<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���� ÷���� �Խ��� - ��й�ȣ ���� (pass)</title>
</head>
<body>
	<h2>���� ÷���� �Խ��� - ��й�ȣ ���� (pass) </h2>
	
	<form name "WriteFrm" method = "post" action = "../mvc_board/pass.do" onsubmit = "return validateForm(this);">
		<input type = "hidden" name = "idx" value = "${param.idx }" />
		<input type = "hidden" name = "mode" value = "${param.mode }" />
		
		<table border = "1" width = "90%">
			<tr>
				<td> ��й�ȣ : </td>
				<td>
					<input type = "password" name = "pass" style = "width:100px" />
				</td>
			</tr>
		
		
		
		
		</table>
	
	
	
	
	
	
	
	</form>
</body>
</html>