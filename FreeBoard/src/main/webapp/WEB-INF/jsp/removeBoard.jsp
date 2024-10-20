<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../includes/header.jsp"></jsp:include>

<%
	/*BoardVO bvo = (BoardVO) request.getAttribute("boardvo");
	String pg = (String) request.getAttribute("page");
	String sc = (String) request.getAttribute("sc");
	String kw = (String) request.getAttribute("keyword");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");*/
	/*대문자 DD는 해당 연도의 nnn번째 일~ 나타남.
	MMM은 축약형 단어, MMMM은 풀네임 단어, F는 몇 번째 발생한 요일인지,E는 축약형 요일, u는 calendar 처럼 1월~7일 까지 숫자요일.
	w는 해당 연도 몇 번째 주인지, W는 월에서 몇 번째 주인지, h는 12시간형식, H는 0~23시간형식, K는 오전~오후로 0~11시까지 표시, k는 0~24시간형식
	s는 그냥 초, S는 밀리초, a는 AM/PM....*/
	//String writeDate = sdf.format(bvo.getWriteDate());
%>
<h3>삭제화면</h3>
<form action="removeBoard.do" method="post">
<input type="hidden" name="page" value="${page }">
<input type="hidden" name="bno" value="${boardvo.boardNo }">
<input type="hidden" name="sc" value="${sc }">
<input type="hidden" name="keyword" value="${kw }">
<table class="table">
	<tr>
		<th>글번호</th><td><c:out value="${boardvo.boardNo}" /></td>
		<th>조회수</th><td><c:out value="${boardvo.viewCnt}" /></td>
	</tr>
	<tr>
		<th>제목</th><td colspan="3"><input type="text" class="form-control" name="title" value="${boardvo.title }" readonly></td>
	</tr>
	<tr>
		<th>내용</th><td colspan="3"><textarea rows="8" cols="30" name="content" class="form-control" readonly>${boardvo.content} </textarea></td>
	</tr>
	<tr>
		<th>작성자</th><td colspan="3"><c:out value="${boardvo.writer}" /></td>
	</tr>
	<tr>
		<th>작성일시</th><td colspan="3"><fmt:formatDate value="${bvo.writeDate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	</tr>
	<tr>
		<td colspan="5" align="center">
			<p>삭제하면 복구할 수 없습니다. 정말 삭제하시겠습니까?</p>
			<input type="submit" class="btn btn-secondary" value="확인">
		</td>
	</tr>	
</table>
</form>

<jsp:include page="../includes/footer.jsp"></jsp:include>