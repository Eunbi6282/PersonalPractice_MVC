<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List.jsp (파일 첨부 게시판 : MVC)</title>
<style> a{text-decoration : none;}</style>
</head>
<body>
	<h2> 파일 첨부형 게시판 목록 보기 (List)</h2>
	
	<!-- 검색 폼 -->
	<form method = "get">
		<table border = "1" width = "90%">
			<tr>
				<td align = "center">
					<select name = "searchField">
						<option value = "title"> 제목</option>
						<option value="content"> 내용</option>
					</select>
					<input type = "text" name = "searchWord" />
					<input type = "submit" value = "검색하기" />				
				</td>
			</tr>		
		</table>
	</form>
	
	<!--  목록 테이블 -->
	<table border = "1" width = "90%">
		<tr>
			<th width = "10%"> 번호 </th>
			<th width = "*"> 제목 </th>
			<th width = "15%"> 작성자 </th>
			<th width = "10%"> 조회수 </th>
			<th width = "15%"> 작성일 </th>
			<th width = "8%"> 첨부 </th>			
		</tr>
		<c:choose>
			<c:when test="${empty boardLists }"> <!--  boardLists값이 비어있을 때 -->
				<tr>
					<td colspan = "6" align = "center">
						등록된 게시물이 없습니다.
					</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items = "${boardLists }" var = "row" varStatus = "loop">
					<!-- req.setAttribute("boardLists", boardLists); 로 boardLists 에 저장된 값 넘겨받았음 -->
					<tr align = "center">
						<td>	<!-- 번호 출력 -->
							${map.totalCount - (((map.pageNum-1) * map.pageSize) + loop.index)}
						</td>
						<td align = "left"> <!--  링크 걸면서 제목 출력 -->
							<a href = "../mvc_board/view.do?idx=${row.idx }">${row.title }</a>
						</td>
						<td> <!-- 작성자 -->
							${row.name }
						</td>
						<td> <!-- 조회수 -->
							${row.visitcount }
						</td>
						<td> <!-- 작성일 -->
							${row.postdate }
						</td>
						<td> <!-- 첨부파일 -->
							<c:if test = "${not empty row.ofile }">
								<a href = "../mvc_board/downlaod.do?ofile=${row.ofile } & sfile=${row.sfile} & idx = ${row.idx}">[Down]</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:otherwise> 
		</c:choose>
	</table>
	
	<!--  하단메뉴 (페이징, 글쓰기) -->
	<table border = "1" width = "90%">
		<tr align = "center">
			<td>
				${map.pagingImg}
			</td>
			<td width="100"><button type="button"
                onclick="location.href='../mvc_board/write.do';">글쓰기</button>
            </td>
		</tr>
	
	</table>
	
</body>
</html>