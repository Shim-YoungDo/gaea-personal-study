package com.study.vo;

import java.util.Date;

/**
 * 공지게시판에 관련된 작업(게시물 등록, 조회, 수정, 삭제)을 할 떄 
 * 필요한 객체(게시물 제목, 내용, 작성자)들의 값을 
 * 저장하기 위한 클래스입니다.
 * 
 * 
 * @author airpo
 *
 */
public class CafeNoticeVO {
	
	/**
	 * 게시글 고유 식별 번호 [PK]
	 */
	private int noticeNumber;
	
	/**
	 * 게시물 제목
	 */
	private String noticeTitle;
	
	/*
	 * 게시물 내용
	 */
	private String noticeContent;
	
	/**
	 * 게시물 작성자
	 */
	private String noticeWriter;
	
	/**
	 * 게시물 등록날짜
	 */
	private Date noticeRegistrationDate;
	
	/*
	 * 게시물 수정날짜
	 */
	private Date noticeUpdateDate;
	
	
	/**
	 * GETTER SETTER
	 */
	public int getNoticeNumber() {
		return noticeNumber;
	}
	public void setNoticeNumber(int noticeNumber) {
		this.noticeNumber = noticeNumber;
	}
	
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	
	
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	
	public String getNoticeWriter() {
		return noticeWriter;
	}
	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}
	
	
	public Date getNoticeRegistrationDate() {
		return noticeRegistrationDate;
	}
	public void setNoticeRegistrationDate(Date noticeRegistrationDate) {
		this.noticeRegistrationDate = noticeRegistrationDate;
	}
	
	public Date getNoticeUpdateDate() {
		return noticeUpdateDate;
	}
	public void setNoticeUpdateDate(Date noticeUpdateDate) {
		this.noticeUpdateDate = noticeUpdateDate;
	}
	
	@Override
	public String toString() {
		return "BoardVO [noticeNumber=" + noticeNumber + ", noticeTitle=" + noticeTitle + ", noticeContent="
				+ noticeContent + ", noticeWriter=" + noticeWriter + ", noticeRegistrationDate="
				+ noticeRegistrationDate + ", noticeUpdateDate=" + noticeUpdateDate + "]";
	}
}