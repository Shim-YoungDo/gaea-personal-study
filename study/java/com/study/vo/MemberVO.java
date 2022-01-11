package com.study.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 회원가입과 로그인 등에 필요한 회원정보들(id, p/w, 이름, 이메일 등)의 객체 값을
 * 저장하기 위한 클래스입니다.
 * 
 * 
 * @author ydshim
 * 
 *
 */
@Getter
@Setter
@ToString
public class MemberVO {
	/**
	 * 회원 ID   [PK]
	 */
	public String memberID;
	
	/**
	 * 회원 P/W
	 */
	private String memberPW;
	
	/**
	 * 회원 이름
	 */
	private String memberName;
	
	/**
	 * 회원 이메일
	 */
	private String memberMail;
	
	private Date memberJointDate;
	
	private Date memberLoginDate;
	
	private String memberDormancy;
}
