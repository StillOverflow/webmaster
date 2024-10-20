<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../includes/header.jsp"></jsp:include>
<h3>로그인화면</h3>
<form action="loginForm.do" method="post">
	<table class="table">
		<tr>
			<th>아이디</th>
			<td><input type="text" name="logId" class="form-control"></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="password" class="form-control"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="로그인" class="btn btn-secondary">
			</td>
		</tr>
	</table>
		<c:if test="${msg != null }">
			<span style="color: red">${msg }</span> 
		</c:if>
</form>


<jsp:include page="../includes/footer.jsp"></jsp:include>