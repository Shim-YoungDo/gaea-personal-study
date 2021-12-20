package com.study.vo;

import java.util.Arrays;


/**
 * 페이징처리를 동적제어하기 위해 필요한 현재 페이지, 한 페이지내에 출력할 게시글 수 등
 * 을 담고있는 클래스입니다.
 * 
 * @author airpo
 */
public class Criteria {

	/**
	 * 현재 페이지
	 */
	private int pageNum;
	
	/**
	 * 한 페이지당 보일 게시물 수
	 */
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
	 * 생성자->원하는 pageNum, amount
	 * @param pageNum 현재 페이지
	 * @param amount  한 화면에 출력될 총 게시글 수
	 */
	public Criteria(int pageNum, int amount) {
	
		this.pageNum = pageNum;
		this.amount = amount;
	}

	/**
	 * GETTER SETTER
	 */
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

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

	@Override
	public String toString() {
		return "Criteria [pageNum=" + pageNum + ", amount=" + amount + ", keyword=" + keyword + ", type=" + type
				+ ", typeArr=" + Arrays.toString(typeArr) + "]";
	}
}
