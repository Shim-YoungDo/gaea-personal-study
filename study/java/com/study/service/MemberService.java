package com.study.service;

import com.study.vo.MemberVO;

/*회원관리(회원가입, 로그인) 서비스 인터페이스*/
public interface MemberService {
	
	//회원가입
	public void memberJoin(MemberVO member) ;
	
	//아이디 중복 체크
	public int idCheck(String memberID);
	
	//인증번호 난수생성
	public Integer authRandomNumber();
	
	//메일 보내기
	//public void sendMail(String email, int mailCheckNumber) throws Exception;
	
	//로그인
	public MemberVO memberLogin(MemberVO member);

	public int mailAuthInquire(int mailCheck);
}
