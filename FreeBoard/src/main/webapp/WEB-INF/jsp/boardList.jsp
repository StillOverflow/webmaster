<%@page import="org.w3c.dom.html.HTMLSelectElement"%>
<%@page import="com.yedam.common.SearchDTO"%>
<%@page import="com.yedam.common.PageDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../includes/header.jsp"></jsp:include>

<h3>자유게시판</h3>
<!-- 검색조건 넣기 -->
<form class="row g-3" action="boardList.do" method="get">
	<input type="hidden" name="page" value="${page.page }">
	<div class="col-md-4">
		<select id="inputState" name="sc" class="form-select">
			<option selected>선택</option>
			<option value="T">제목</option>
			<option value="W">글쓴이</option>
			<option value="TW">제목+글쓴이</option>
		</select>
	</div>
	<div class="col-md-2">
		<input type="text" class="form-control" id="inputZip" name="keyword" value=${search.keyword }>
	</div>
	<div class="col-md-2">
		<button type="submit" class="btn btn-primary">검색</button>
	</div>
</form>

<table class="table">
	<thead>
		<tr>
			<th>순서</th>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일자</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="p" value="${page.page * 5 - 5 }"></c:set>
		<c:forEach var="bvo" items="${boardList }"> <!-- 속성을 직접 쓸 수 있음. -->
			<c:set var="p" value="${p + 1 }"></c:set>
			<tr>
				<td>${p }</td>
				<td><c:out value="${bvo.boardNo }"></c:out></td>
				<td><a
					href="board.do?page=${page.page }&bno=${bvo.boardNo }&sc=${search.searchCondition }&keyword=${search.keyword }">${bvo.title }</a></td>
				<!-- 내용은 안에 클릭하면 상세화면에 나오도록 -->
				<td><c:out value="${bvo.writer }"></c:out></td>
				<td><fmt:formatDate value="${bvo.writeDate }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
				<td><c:out value="${bvo.viewCnt }"></c:out></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<!-- 부트스트랩 코드 활용한 페이지 네비게이션 -->
<nav aria-label="Page navigation example">
	<ul class="pagination justify-content-center">
	
		<c:if test="${page.prev == true }">
		<li class="page-item"><a class="page-link"
			href="boardList.do?page=${page.startPage - 1 }&sc=${search.searchCondition }&keyword=${search.keyword }">Previous</a></li>
		</c:if>
		
		<c:forEach var="i" begin="${page.startPage }" end="${page.endPage }" step="1">
			<c:choose>
				<c:when test="${page.page == i}">
					<li class="page-item active" aria-current="page"><a
						class="page-link">${i }</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link"
						href="boardList.do?page=${i }&sc=${search.searchCondition }&keyword=${search.keyword }">${i }</a></li>			
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<c:if test="${page.next == true}">
			<li class="page-item"><a class="page-link"
				href="boardList.do?page=${page.endPage + 1 }&sc=${search.searchCondition }&keyword=${search.keyword }">Next</a></li>
		</c:if>

	</ul>
	<br>
	<input type="button" value="보기" class="btn btn-light">
	<p id="toString">${page }</p>
</nav>

<jsp:include page="../includes/footer.jsp"></jsp:include>

<script>
	let toString = document.querySelector('#toString');
	toString.style.display = 'none';
	document.querySelector('input[value="보기"]').addEventListener('click',
			function(e) {
				if (toString.style.display == 'none') {
					toString.style.display = 'block';
				} else {
					toString.style.display = 'none';
				}
			});
</script>