package com.study.vo;

/**
 * 회원가입과 로그인 등에 필요한 회원정보들(id, p/w, 이름, 이메일 등)의 객체 값을
 * 저장하기 위한 클래스입니다.
 * 
 * 
 * @author airpo
 * 
 *
 */
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
	
	/**
	 * 관리자 여부 판단하기 위한 값
	 * 현재는 기능상 구현이 안되어 사용하지 않음.
	 * default:0(관리자x)
	 */
	private int adminCheck;
	
	/**
	 * GETTER SETTER
	 */
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	
	public String getMemberPW() {
		return memberPW;
	}
	public void setMemberPW(String memberPW) {
		this.memberPW = memberPW;
	}
	
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	public String getMemberMail() {
		return memberMail;
	}
	public void setMemberMail(String memberMail) {
		this.memberMail = memberMail;
	}
	
	public int getAdminCheck() {
		return adminCheck;
	}
	public void setAdminCheck(int adminCheck) {
		this.adminCheck = adminCheck;
	}
	
	@Override
	public String toString() {
		return "MemberVO [memberID=" + memberID + ", memberPW=" + memberPW + ", memberName=" + memberName
				+ ", memberMail=" + memberMail + ", adminCheck=" + adminCheck + "]";
	}

	
}
