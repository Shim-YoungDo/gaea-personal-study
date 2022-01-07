package com.study.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 공지게시판에 관련된 작업(게시물 등록, 조회, 수정, 삭제)을 할 떄 
 * 필요한 객체(게시물 제목, 내용, 작성자)들의 값을 
 * 저장하기 위한 클래스입니다.
 * 
 * 
 * @author ydshim
 *
 */
@Getter
@Setter
@ToString
public class CafeNoticeVO {
	
	public CafeNoticeVO(){
		
	}
	
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
	 * 게시글 삭제 여부
	 */
	private String noticeDeleteCheck;
	
	/**
	 * 게시물 등록날짜
	 */
	@JsonFormat(shape = Shape.STRING)
	private Date noticeRegistrationDate;
	
	/*
	 * 게시물 수정날짜
	 */
	@JsonFormat(shape = Shape.STRING)
	private Date noticeUpdateDate;
}