<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function boardSearch() {
	searchform.submit();
}
</script>

</head>
<body>
	<h2>MemBoardMain입니다.</h2>

	<p>${sessionScope.loginId}님환영합니다.</p>
	<a href="memberview?mid=${sessionScope.loginId}">마이페이지</a>
	
		<c:if test="${sessionScope.loginId eq 'admin'}">
		<a href="memberlist">관리자 모드</a>

	
	</c:if>
<a href="memberlogout">로그아웃</a>


<table border="1">
		<tr>
			<td>글번호</td>
			<td>작성자</td>
			<td>글제목</td>
			<td>작성일자</td>
			<td>조회수</td>
		</tr>
		<c:forEach var="board" items="${boardList}" >
			<tr>
				<td>${board.bnumber}</td>
				<td><a
					href="#" onclick="popup('${board.bid}')">${board.bid}</a></td>
				<td><a
					href="boardview?bnumber=${board.bnumber}&page=${paging.page}">${board.btitle}</a></td>
				<td>${board.bdate}</td>
				<td>${board.bhits}</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5"><button onclick="location.href='writeform'">글쓰기</button> </td>
		</tr>
	</table>
	
	<!-- 페이징 처리 -->
	<c:if test="${paging.page<=1}">
	[이전]&nbsp;
	</c:if>
	
	<c:if test="${paging.page>1}">
		<a href="boardlist?page=${paging.page-1}">[이전]</a>&nbsp;
	</c:if>
	
	<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
		<c:choose>
			<c:when test="${i eq paging.page}">
			${i}
			</c:when>
			<c:otherwise>
				<a href="boardlist?page=${i}">${i}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>

	<c:if test="${paging.page>=paging.maxPage}">
		[다음]
	</c:if>
	
	<c:if test="${paging.page<paging.maxPage}">
		<a href="boardlist?page=${paging.page+1}">[다음]</a>
	</c:if>
	<br>
<form action="boardsearch" method="get" name="searchform">
		<select id="searchtype" name="searchtype">
			<option value="searchtitle">제목</option>
			<option value="searchwriter">작성자</option>
		</select>
		<input type="text" name="keyword" placeholder="검색어입력">
		<input type="button" onclick="boardSearch()" value="검색">
	</form>
</body>
</html>