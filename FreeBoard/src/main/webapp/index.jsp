<%@page import="com.yedam.service.MemberService"%>
<%@ page import="com.yedam.service.MemberServiceImpl" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSPL CORE 라이브러리 적용 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>
	<!-- MVC 디자인: View(JSP페이지), Model(DB처리), 컨트롤(흐름 제어) -->
	<!-- Expression Language: EL -->
	<!-- 간단한 boolean이나 삼항연산식 등 사용 가능. -->
	<p>${3+5 }</p>
	<p>${3+5 == 8 }</p>
	<p>${3+5 == 8 ? "OK" : "NG" }</p>
	<c:set var="name" value="Hong"></c:set>
	<c:out value="${name }"></c:out>
	<c:set var="age" value="20"></c:set>
	<c:if test="${age >= 20 }">
	<p>20세 이상입니다.</p>
	</c:if>
	
	<c:choose>
		<c:when test="${age >= 70 }">
			<p>노인</p>
		</c:when>
		<c:when test="${age >= 20 } }">
			<p>성인</p>
		</c:when>
		<c:otherwise>
			<p>미성년</p>
		</c:otherwise>
	</c:choose>
	
	<c:forEach var="i" begin="1" end="5" step="2">
		<p>i의 값은 ${i }입니다.</p>
	</c:forEach>
	
	<c:set var="page" value="boardList.do"></c:set>
	<jsp:forward page="${page }"></jsp:forward>
</body>
</html>