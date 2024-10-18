<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<% 
	BoardVO bvo = (BoardVO) request.getAttribute("boardvo");
	String pg = (String) request.getAttribute("page");
	String sc = (String) request.getAttribute("sc");
	String kw = (String) request.getAttribute("keyword");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String writeDate = sdf.format(bvo.getWriteDate());
%>
<h3>수정화면</h3>
<form action="modifyBoard.do" method="post">
	<input type="hidden" name="bno" value="<%=bvo.getBoardNo() %>">
	<input type="hidden" name="page" value="<%=pg %>">
	<input type="hidden" name="sc" value="<%=sc %>">
	<input type="hidden" name="keyword" value="<%=kw %>">
	<table class="table">
		<tr>
			<th>글번호</th><td><%=bvo.getBoardNo() %></td>
			<th>조회수</th><td><%=bvo.getViewCnt() %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td colspan="3"><input type="text" name="title" class="form-control" value="<%=bvo.getTitle() %>"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="3"><textarea rows="8" cols="30" name="content" class="form-control"><%=bvo.getContent() %></textarea></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td colspan="3"><%=bvo.getWriter() %></td>
		</tr>
		<tr>
			<th>작성일시</th>
			<td colspan="3"><%=writeDate%></td>
		</tr>
		<tr>
			<td colspan="3" align="center">
				<input type="submit" value="저장" class="btn btn-secondary">
				<input type="reset" value="초기화" class="btn btn-light">
				<%
 				String msg = (String) request.getAttribute("msg");
 				if (msg != null) {
 				%>
				<span style="color: red"><%=msg%></span> 
				<%}%>
			</td>
		</tr>
	</table>
</form>


<jsp:include page="../includes/footer.jsp"></jsp:include>