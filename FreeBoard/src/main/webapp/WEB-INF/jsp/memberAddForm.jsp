<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>

	<h3>글쓰기</h3>
	<form action="memberAdd.do" method="post">
		<table border='1' class="table">
			<tr>
				<th>회원ID</th><td><input type="text" name="mid"></td>
			</tr>
			<tr>
				<th>회원이름</th><td><input type="text" name="mname"></td>
			</tr>
			<tr>
				<th>비밀번호</th><td><input type="password" name="passwd"></td>
			</tr>
			<tr>
				<th>연락처</th><td><input type="tel" name="phone"></td>
			</tr>
			<tr>
				<td colspan='2'><input type="submit" value="전송" class="btn btn-primary"></td>
			</tr>
		</table>
	</form>

<jsp:include page="../includes/footer.jsp"></jsp:include>