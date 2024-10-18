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
	/*대문자 DD는 해당 연도의 nnn번째 일~ 나타남.
	MMM은 축약형 단어, MMMM은 풀네임 단어, F는 몇 번째 발생한 요일인지,E는 축약형 요일, u는 calendar 처럼 1월~7일 까지 숫자요일.
	w는 해당 연도 몇 번째 주인지, W는 월에서 몇 번째 주인지, h는 12시간형식, H는 0~23시간형식, K는 오전~오후로 0~11시까지 표시, k는 0~24시간형식
	s는 그냥 초, S는 밀리초, a는 AM/PM....*/
	String writeDate = sdf.format(bvo.getWriteDate());
	
	HttpSession sess = request.getSession();
	String logId = String.valueOf(sess.getAttribute("logId"));
%>
<h3>상세페이지</h3>
<table class="table">
	<tr>
		<th>글번호</th><td><%=bvo.getBoardNo()%></td>
		<th>조회수</th><td><%=bvo.getViewCnt()%></td>
	</tr>
	<tr>
		<th>제목</th><td colspan="4"><input type="text" class="form-control" name="title" value="<%=bvo.getTitle()%>" readonly></td>
	</tr>
	<tr>
		<th>내용</th><td colspan="4"><textarea rows="8" cols="30" name="content" class="form-control" readonly><%=bvo.getContent() %></textarea></td>
	</tr>
	<tr>
		<th>작성자</th><td colspan="4"><%=bvo.getWriter()%></td>
	</tr>
	<tr>
		<th>작성일시</th><td colspan="4"><%=writeDate%></td>
	</tr>
	<tr>
		<td colspan="4" align="center">
			<input type="button" class="btn btn-primary" value="목록">
			<input type="button" <%= logId != null & bvo.getWriter().equals(logId)? "" : "disabled" %> class="btn btn-secondary" value="수정">
			<input type="button" <%= logId != null & bvo.getWriter().equals(logId)? "" : "disabled" %> class="btn btn-light" value="삭제">
		</td>
	</tr>	
</table>

<jsp:include page="../includes/footer.jsp"></jsp:include>
<!-- 버튼이 여러개 있을 시, form action="링크" 으로 감싸도 되고 자바스크립트로 구현해도 됨. -->
<script>
	document.querySelector('input[value="목록"]').addEventListener('click', function(e){
		location.href = 'boardList.do?page=<%=pg %>&sc=<%=sc %>&keyword=<%=kw %>';
	});

	document.querySelector('input[value="수정"]').addEventListener('click', function(e){
		location.href = 'modifyBoard.do?page=<%=pg %>&bno=<%=bvo.getBoardNo() %>&sc=<%=sc %>&keyword=<%=kw %>';
	});
	
	document.querySelector('input[value="삭제"]').addEventListener('click', function(e){
		location.href = 'removeBoard.do?page=<%=pg %>&bno=<%=bvo.getBoardNo() %>&sc=<%=sc %>&keyword=<%=kw %>';
	});
	
</script>