package com.yedam.common;

import lombok.Data;
@Data

public class SearchDTO {
	private String searchCondition;
	private String keyword;
	private int page;
}	
