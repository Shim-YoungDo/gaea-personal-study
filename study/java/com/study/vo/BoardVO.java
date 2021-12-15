package com.study.vo;

import java.util.Date;

/*게시판 수정/작성등에 사용될 VO*/
public class BoardVO {
	
	private int boardNumber; //게시물 번호
	private String boardTitle; //게시물 제목
	private String boardContent; //게시물 내용
	private String boardWriter; //작성자
	private Date boardRegistrationDate; //등록날짜
	private Date boardUpdateDate; //수정날짜
	
	/*GETTER SETTER*/
	public int getBoardNumber() {
		return boardNumber;
	}
	public void setBoardNumber(int boardNumber) {
		this.boardNumber = boardNumber;
	}
	
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public Date getBoardRegistrationDate() {
		return boardRegistrationDate;
	}
	public void setBoardRegistrationDate(Date boardRegistrationDate) {
		this.boardRegistrationDate = boardRegistrationDate;
	}
	public Date getBoardUpdateDate() {
		return boardUpdateDate;
	}
	public void setBoardUpdateDate(Date boardUpdateDate) {
		this.boardUpdateDate = boardUpdateDate;
	}
	
	@Override
	public String toString() {
		return "BoardVO [boardNumber=" + boardNumber + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardWriter=" + boardWriter + ", boardRegistrationDate=" + boardRegistrationDate
				+ ", boardUpdateDate=" + boardUpdateDate + "]";
	}
	


}
