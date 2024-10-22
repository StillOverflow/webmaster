<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h3>회원등록</h3>
<table border='1' class="table">
	<tr>
		<th>회원ID</th><td><input type="text" id="mid"></td>
	</tr>
	<tr>
		<th>회원이름</th><td><input type="text" id="mname"></td>
	</tr>
	<tr>
		<th>비밀번호</th><td><input type="password" id="passwd"></td>
	</tr>
	<tr>
		<th>연락처</th><td><input type="tel" id="phone"></td>
	</tr>
	<tr>
		<td colspan='2'><button type="button" id="addBtn" class="btn btn-primary">등록</button></td>
	</tr>
</table>

<h3>회원목록</h3>
<div id="show">

</div>
	
<script src="js/members.js"></script>