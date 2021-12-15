package com.study.vo;

/*로그인, 회원가입에 사용될 VO*/
public class MemberVO {
	
	public String memberID;  //멤버 ID
	private String memberPW; //멤버 P/W
	private String memberName; //멤버 이름
	private String memberMail; //멤버 이메일
	private int adminCheck;   //관리자 여부
	
	/*GETTER SETTER*/
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
