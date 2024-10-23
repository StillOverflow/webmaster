<%@page import="com.yedam.common.SearchDTO"%>
<%@page import="com.yedam.common.PageDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
	.reply {
		margin: 0;
		max-width: 100%;
	}
	.reply span {
		display: inline-block;
		margin-top: 5px;
		white-space: pre-line;
	}
	.reply ul {
		list-style: none;
		padding: 0;
	}
	.pagination {
		justify-content: center;
	}
	.pagination * {
		cursor: pointer;
	}
</style>

<h3>상세페이지</h3>
<table class="table">
	<tr>
		<th>글번호</th><td data-id="bno">${boardvo.boardNo }</td>
		<th>조회수</th><td>${boardvo.viewCnt }</td>
	</tr>
	<tr>
		<th>제목</th><td colspan="4"><input type="text" class="form-control" name="title" value="${boardvo.title }" readonly></td>
	</tr>
	<tr>
		<th>내용</th><td colspan="4"><textarea rows="8" cols="30" name="content" class="form-control" readonly>${boardvo.content }</textarea></td>
	</tr>
	<tr>
		<th>이미지</th><td colspan="4">
		<c:if test="${boardvo.img != null }">
			<img src="images/${boardvo.img }" alt="img" width="500"></td>
		</c:if>
	</tr>
	<tr>
		<th>작성자</th><td colspan="4">${boardvo.writer }</td>
	</tr>
	<tr>
		<th>작성일시</th><td colspan="4"><fmt:formatDate value="${boardvo.writeDate }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
	</tr>
	<tr>
		<td colspan="4" align="center">
			<input type="button" class="btn btn-primary" value="목록">
			<input type="button" ${(logId != null && boardvo.writer == logId) || responsibility == "Admin" ? "" : "disabled" } class="btn btn-secondary" value="수정">
			<input type="button" ${(logId != null && boardvo.writer == logId) || responsibility == "Admin" ? "" : "disabled" } class="btn btn-light" value="삭제">
		</td>
	</tr>	
</table>

<h4>댓글</h4>
<!-- 댓글 작성 박스  -->
<div id="addReplyBox" class="row g-3">
	<span class="col-md-1">${logId }</span>
	<div class="col-md-9">
		<textarea id="reply" rows="1" cols="20" wrap="hard" ${logId == null ? "readonly" : "" } class="form-control">${logId == null ? "로그인 이후에 작성 가능합니다." : "" }</textarea>
	</div>
	<div class="col-md-2">
		<button type="button" id="addReplyBtn" ${logId == null ? "disabled" : "" } class="btn btn-secondary">등록</button>
	</div>
</div>

<hr>
<!-- 작성된 댓글 항목 -->
<div class="container reply">
	<div class="content">
		<ul>
			<li>
				<span class="col-sm-1">순서</span>
				<span class="col-sm-1">번호</span>
				<span class="col-sm-4">내용</span>
				<span class="col-sm-1">작성자</span>
				<span class="col-sm-3">작성일시</span>
				<span class="col-sm-1"></span>
			</li>
		</ul>
	</div>
</div>
<!-- 댓글 페이징 네비게이션 -->
<nav aria-label="Reply Page navigation">
  <ul class="pagination">
    <li class="page-item">
      <a class="page-link" href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    
    <li class="page-item">
      <a class="page-link" href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>

<!-- 
<table id="replyList" class="table">
	<tbody>
	
	</tbody>
</table>
-->

<jsp:include page="../includes/footer.jsp"></jsp:include>
<!-- 버튼이 여러개 있을 시, form action="링크" 으로 감싸도 되고 자바스크립트로 구현해도 됨. -->

<script>

	const bno = '${boardvo.boardNo }';
	const logId = '${logId}'
	console.log(bno);
	
	document.querySelector('input[value="목록"]').addEventListener('click', function(e){
		location.href = 'boardList.do?page=${page}&sc=${sc}&keyword=${kw == null ? "" : kw}';
	});

	document.querySelector('input[value="수정"]').addEventListener('click', function(e){
		location.href = 'modifyBoard.do?page=${page}&bno=${boardvo.boardNo}&sc=${sc}&keyword=${kw == null ? "" : kw}';
	});
	
	document.querySelector('input[value="삭제"]').addEventListener('click', function(e){
		location.href = 'removeBoard.do?page=${page}&bno=${boardvo.boardNo}&sc=${sc}&keyword=${kw == null ? "" : kw}';
	});
	
</script>
<script src="js/replyService.js"></script> <!-- 댓글 관련 메소드 정의 -->
<script src="js/reply.js"></script> <!-- 메소드 호출하여 사용 -->