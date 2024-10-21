package com.yedam.vo;

import java.util.Date;
import lombok.Data;

//lombok.Data import해서 @Data 하면 한 번에 대부분의 메소드 가져옴.
@Data

public class BoardVO {
	private int boardNo;
	private String title;
	private String content;
	private String writer;
	private String writerName;
	private int viewCnt;
	private Date writeDate;
	private Date updateDate;
	private String img;
}
