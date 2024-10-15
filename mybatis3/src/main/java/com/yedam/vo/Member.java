package com.yedam.vo;

import java.util.Date;

//lombok 라이브러리 활용
//1. lombok 다운받아 이클립스가 설치된 폴더에 넣고, eclipse.exe 경로로 설치
//2. maven repository 에서 lombok 검색하여 최신버전 jar 다운받고 WEB-INF lib 안에 붙여넣기
//   (파일 인식)
//** mybatis3 프로젝트를 configure > Convert to Maven Project > xml파일 생성하면
//   이러한 번거로운 라이브러리 설치 과정 없이, 태그 복사하여 xml파일의 dependencies 안에 붙여넣는 것만으로 사용 가능
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString

public class Member {
	//tbl_member 테이블의 값을 담기 위한 클래스
	private String memberId;
	private String password;
	private String memberName;
	private String phone;
	private String responsibility;
	private Date creationDate;
	
//	@Override
//	public String toString() {
//		return memberId + memberName + password + phone + responsibility + creationDate;
//	}
}
