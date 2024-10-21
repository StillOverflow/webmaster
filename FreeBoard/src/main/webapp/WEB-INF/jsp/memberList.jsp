<%@ page import="com.yedam.vo.MemberVO"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<h3>회원목록</h3>
	<%
	List<MemberVO> list = (List<MemberVO>) request.getAttribute("memberList");
	System.out.println(list);
	%>
	<table border="2" class="table">
		<tbody>
			<c:forEach var="mvo" items="${memberList }">
				<tr><td><c:out value="${mvo.memberId }"></c:out></td>
					<td><c:out value="${mvo.memberName }"></c:out></td>
					<td><c:out value="${mvo.phone }"></c:out></td>
				</tr>			
			</c:forEach>
		</tbody>
	</table>
