package com.study.vo;

import java.util.Arrays;


/*페이징 쿼리를 동적제어하기 위해 필요한 현재페이지(pageNum),
 * 한 페이지에 보일 게시물 수(amount)를 
 * 한 파라미터로 넘기기 위한 클래스 */
public class Criteria {

	//현재 페이지
	private int pageNum;
	
	//한 페이지당 보일 게시물 수
	private int amount;
	
	//검색 키워드
	private String keyword;
	
	//검색 타입
	private String type;
	
	//검색 타입 배열
	private String[] typeArr;
	
	//스킵할 게시물 수 ((pageNum-1)*amount)
	private int skip;
	
	//기본 생성자->기본 세팅:pageNum=1, amount=10
	public Criteria() {
		this(1,10);
		this.skip = 0;
	}
	
	//생성자->원하는 pageNum, amount
	public Criteria(int pageNum, int amount) {
	
		this.pageNum = pageNum;
		this.amount = amount;
		this.skip = (pageNum-1)*amount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.skip = (pageNum-1)*this.amount;
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.skip=(this.pageNum-1)*amount;
		this.amount = amount;
	}

	public int getSkip() {
		return skip;
	}

	public void setSkip(int skip) {
		this.skip = skip;
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
				+ ", typeArr=" + Arrays.toString(typeArr) + ", skip=" + skip + "]";
	}
}
