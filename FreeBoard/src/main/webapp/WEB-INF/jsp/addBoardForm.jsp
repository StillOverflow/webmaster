<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<%
HttpSession sess = request.getSession();
String logId = String.valueOf(sess.getAttribute("logId"));
%>
<h3>글쓰기</h3>
<form action="addBoard.do" method="post">
	<input type="hidden" name="writer" class="form-control" value="<%=logId %>">
	<table class="table">
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" class="form-control"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="8" cols="30" name="content"
					class="form-control"></textarea></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%=logId %></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="저장"
				class="btn btn-secondary"> <input type="reset" value="초기화"
				class="btn btn-light">
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