<%@page import="com.yedam.common.SearchDTO"%>
<%@page import="com.yedam.common.PageDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h3>상세페이지</h3>
<table class="table">
	<tr>
		<th>글번호</th><td>${boardvo.boardNo }</td>
		<th>조회수</th><td>${boardvo.viewCnt }</td>
	</tr>
	<tr>
		<th>제목</th><td colspan="4"><input type="text" class="form-control" name="title" value="${boardvo.title }" readonly></td>
	</tr>
	<tr>
		<th>내용</th><td colspan="4"><textarea rows="8" cols="30" name="content" class="form-control" readonly>${boardvo.content }</textarea></td>
	</tr>
	<tr>
		<th>작성자</th><td colspan="4">${boardvo.writer }</td>
	</tr>
	<tr>
		<th>작성일시</th><td colspan="4"><fmt:formatDate value="${boardvo.writeDate }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
	</tr>
	<tr>
		<td colspan="4" align="center">
			<input type="button" class="btn btn-primary" value="목록">
			<input type="button" ${logId != null && boardvo.writer == logId ? "" : "disabled" } class="btn btn-secondary" value="수정">
			<input type="button" ${logId != null && boardvo.writer == logId ? "" : "disabled" } class="btn btn-light" value="삭제">
		</td>
	</tr>	
</table>

<jsp:include page="../includes/footer.jsp"></jsp:include>
<!-- 버튼이 여러개 있을 시, form action="링크" 으로 감싸도 되고 자바스크립트로 구현해도 됨. -->
<script>
	<%
	BoardVO bvo = (BoardVO) request.getAttribute("boardvo");
	String pg = (String) request.getAttribute("page");
	String sc = (String) request.getAttribute("sc");
	String kw = (String) request.getAttribute("kw");
	%>
	document.querySelector('input[value="목록"]').addEventListener('click', function(e){
		location.href = 'boardList.do?page=<%=pg %>&sc=<%=sc %>&keyword=<%=kw == null ? "" : kw %>';
	});

	document.querySelector('input[value="수정"]').addEventListener('click', function(e){
		location.href = 'modifyBoard.do?page=<%=pg %>&bno=<%=bvo.getBoardNo() %>&sc=<%=sc %>&keyword=<%=kw %>';
	});
	
	document.querySelector('input[value="삭제"]').addEventListener('click', function(e){
		location.href = 'removeBoard.do?page=<%=pg %>&bno=<%=bvo.getBoardNo() %>&sc=<%=sc %>&keyword=<%=kw %>';
	});
	
</script>