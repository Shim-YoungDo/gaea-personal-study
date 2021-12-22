package com.study.vo;

import java.util.Arrays;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * 페이징처리를 동적제어하기 위해 필요한 현재 페이지, 한 페이지내에 출력할 게시글 수 등
 * 을 담고있는 클래스입니다.
 * 
 * @author ydshim
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Criteria {

	/**
	 * 현재 페이지
	 */
	@NonNull
	private int pageNum;
	
	/**
	 * 한 페이지당 보일 게시물 수
	 */
	@NonNull
	private int amount;
	
	/**
	 * 검색 키워드
	 */
	private String keyword;
	
	/**
	 * 검색 타입
	 */
	private String type;
	
	/**
	 * 검색 타입 배열
	 */
	private String[] typeArr;
	
	/**
	 * 기본 생성자->기본 세팅:pageNum=1, amount=10
	 */
	public Criteria() {
		this(1,10);
	}

	/**
	 * GETTER SETTER
	 */

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		this.typeArr = type.split("");
	}

	public String[] getTypeArr() {
		return typeArr;
	}

	public void setTypeArr(String[] typeArr) {
		this.typeArr = typeArr;
	}
}
