<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3>글쓰기</h3>
<form action="addBoard.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="writer" class="form-control" value="${logId }">
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
			<th>이미지</th>
			<td><input type="file" name="img" class="form-control"></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${logId }</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="저장"
				class="btn btn-secondary"> <input type="reset" value="초기화"
				class="btn btn-light">
				<c:if test="${msg != null }">
					<span style="color: red">${msg }</span> 
				</c:if>
			</td>
		</tr>
	</table>
</form>