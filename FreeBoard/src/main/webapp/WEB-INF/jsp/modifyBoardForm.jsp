<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<% 
	//BoardVO bvo = (BoardVO) request.getAttribute("boardvo");
	//String pg = (String) request.getAttribute("page");
	//String sc = (String) request.getAttribute("sc");
	//String kw = (String) request.getAttribute("keyword");
	//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//String writeDate = sdf.format(bvo.getWriteDate());
%>
<h3>수정화면</h3>
<form action="modifyBoard.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="bno" value="${boardvo.boardNo }">
	<input type="hidden" name="page" value="${page }">
	<input type="hidden" name="sc" value="${sc }">
	<input type="hidden" name="keyword" value="${kw }">
	<table class="table">
		<tr>
			<th>글번호</th><td><c:out value="${boardvo.boardNo }"></c:out></td>
			<th>조회수</th><td><c:out value="${boardvo.viewCnt }"></c:out></td>
		</tr>
		<tr>
			<th>제목</th>
			<td colspan="3"><input type="text" name="title" class="form-control" value="${boardvo.title }"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="3"><textarea rows="8" cols="30" name="content" class="form-control"><c:out value="${boardvo.content }"></c:out></textarea></td>
		</tr>
		<tr>
			<th>이미지</th><td colspan="3">
			<c:if test="${boardvo.img != null }">
				<img src="images/${boardvo.img }" alt="img" width="100"></td>
			</c:if>
		</tr>
		<tr>
			<th></th>
			<td colspan="3">
			<input type="file" name="img" class="form-control">
			</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td colspan="3"><c:out value="${boardvo.writer }"></c:out></td>
		</tr>
		<tr>
			<th>작성일시</th>
			<td colspan="3"><fmt:formatDate value="${boardvo.writeDate }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
		</tr>
		<tr>
			<td colspan="3" align="center">
				<input type="submit" value="저장" class="btn btn-secondary">
				<input type="reset" value="초기화" class="btn btn-light">
				<c:if test="msg != null">
					<span style="color: red">${msg }</span> 
				</c:if>
			</td>
		</tr>
	</table>
</form>
