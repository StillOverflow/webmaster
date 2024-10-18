package com.yedam.common;

import lombok.Data;
@Data

public class PageDTO {
	private int startPage, endPage;
	private boolean prev, next; //isPrev(), isNext() lombok에서 자동 생성
	private int page; //현재페이지
	
	public PageDTO(int page, int totalCnt) {
		//페이지 이동버튼 계산 방식
//		int totalCnt = 1000; //실제 열의 갯수
		
		this.page = page;
		this.endPage = (int) Math.ceil(page / 10.0) * 10; //최대 10페이지까지 절삭
		this.startPage = this.endPage - 9; //1, 11, 21, 31~~~
		
		int realEnd = (int) Math.ceil(totalCnt / 5.0); //한 페이지당 5개씩 표시했을 때, 실제 최종 페이지
		this.endPage = this.endPage > realEnd ? realEnd : this.endPage;
		
		this.prev = this.startPage > 1; //true면 표시, false면 미표시
		this.next = this.endPage < realEnd;
	}
	
}
