<%@page import="org.w3c.dom.html.HTMLSelectElement"%>
<%@page import="com.yedam.common.SearchDTO"%>
<%@page import="com.yedam.common.PageDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>

<%
List<BoardVO> list = (List<BoardVO>) request.getAttribute("boardList");
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*대문자 DD는 해당 연도의 nnn번째 일~ 나타남.
MMM은 축약형 단어, MMMM은 풀네임 단어, F는 몇 번째 발생한 요일인지,E는 축약형 요일, u는 calendar 처럼 1월~7일 까지 숫자요일.
w는 해당 연도 몇 번째 주인지, W는 월에서 몇 번째 주인지, h는 12시간형식, H는 0~23시간형식, K는 오전~오후로 0~11시까지 표시, k는 0~24시간형식
s는 그냥 초, S는 밀리초, a는 AM/PM....*/
%>
<%
PageDTO paging = (PageDTO) request.getAttribute("page");
int currentP = paging.getPage(); //현재페이지
SearchDTO search = (SearchDTO) request.getAttribute("search");
String sc = search.getSearchCondition() == null? "" : search.getSearchCondition();
String kw = search.getKeyword() == null? "" : search.getKeyword();
%>
<!--<p><%//=list %></p>-->
<h3>자유게시판</h3>
<!-- 검색조건 넣기 -->
<form class="row g-3" action="boardList.do" method="get">
	<input type="hidden" name="page" value="<%=paging.getPage() %>">
	<div class="col-md-4">
		<select id="inputState" name="sc" class="form-select">
			<option selected>선택</option>
			<option value="T">제목</option>
			<option value="W">글쓴이</option>
			<option value="TW">제목+글쓴이</option>
		</select>
	</div>
	<div class="col-md-2">
		<input type="text" class="form-control" id="inputZip" name="keyword" value=<%=kw %>>
	</div>
	<div class="col-md-2">
		<button type="submit" class="btn btn-primary">검색</button>
	</div>
</form>

<table class="table">
	<thead>
		<tr>
			<th>순서</th>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일자</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody>
		<%
		int count = currentP * 5 - 5;
		for (BoardVO bvo : list) {
			count++;
			String wdate = sdf.format(bvo.getWriteDate());
		%>
		<tr>
			<td><%=count %></td>
			<td><%=bvo.getBoardNo()%></td>
			<td><a
				href="board.do?page=<%=paging.getPage()%>&bno=<%=bvo.getBoardNo()%>&sc=<%=sc %>&keyword=<%=kw %>"><%=bvo.getTitle()%></a></td>
			<!-- 내용은 안에 클릭하면 상세화면에 나오도록 -->
			<td><%=bvo.getWriter()%></td>
			<td><%=wdate%></td>
			<td><%=bvo.getViewCnt()%></td>
		</tr>
		<%
		}
		%>
	</tbody>
</table>

<!-- 부트스트랩 코드 활용한 페이지 네비게이션 -->
<nav aria-label="Page navigation example">
	<ul class="pagination justify-content-center">
		<%
		if (paging.isPrev()) {
		%>
		<li class="page-item"><a class="page-link"
			href="boardList.do?page=<%=paging.getStartPage() - 1%>&sc=<%=sc %>&keyword=<%=kw %>">Previous</a></li>
		<%
		}
		%>
		<!-- <%//else {%>
        <li class="page-item disabled">
      		<a class="page-link">Previous</a>
    	</li>
    <%//} %> -->

		<%
		for (int p = paging.getStartPage(); p <= paging.getEndPage(); p++) {
		%>
		<%
		if (currentP == p) {
		%>
		<li class="page-item active" aria-current="page"><a
			class="page-link"><%=p%></a></li>
		<%
		} else {
		%>
		<li class="page-item"><a class="page-link"
			href="boardList.do?page=<%=p%>&sc=<%=sc %>&keyword=<%=kw %>"><%=p%></a></li>
		<%
		}
		%>
		<%
		}
		%>

		<%
		if (paging.isNext()) {
		%>
		<li class="page-item"><a class="page-link"
			href="boardList.do?page=<%=paging.getEndPage() + 1%>&sc=<%=sc %>&keyword=<%=kw %>">Next</a></li>
		<%
		}
		%>
		<!-- <%//else {%>
        <li class="page-item disabled">
      		<a class="page-link">Next</a>
    	</li>
    <%//} %> -->

	</ul>
	<br>
	<input type="button" value="보기" class="btn btn-light">
	<p id="toString"><%=paging.toString()%></p>
</nav>

<jsp:include page="../includes/footer.jsp"></jsp:include>

<script>
	let toString = document.querySelector('#toString');
	toString.style.display = 'none';
	document.querySelector('input[value="보기"]').addEventListener('click',
			function(e) {
				if (toString.style.display == 'none') {
					toString.style.display = 'block';
				} else {
					toString.style.display = 'none';
				}
			});
</script>