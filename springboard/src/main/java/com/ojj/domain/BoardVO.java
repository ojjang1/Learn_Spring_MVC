package com.ojj.domain;


import java.util.Date;

import lombok.Data;

// lombok 를 이용해서 데이터를 이동하기위해
// getter/setter 생성을.
// 만약 lombok 를 사용하지 않으면 getter.setter 수동 지정해 줘야 한다.
// Data를 자동 생성해달라고 하는 어노테이션 지정
@Data
public class BoardVO {
	
	private Long bno;
	// 정수형에서 가장 큰 Long 타입
	private String title;
	// 테이블에서 varchar2 로 잡았기 때문
	private String content;
	private String writer;
	private Date regdate;
	// 테이블에서도 date 타입으로 잡았기 때문
	private Date updateDate;
		

}
