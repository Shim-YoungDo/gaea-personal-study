package com.study.service;

import com.study.vo.MemberVO;

/**
 * 회원관리(회원가입, 로그인)와 관련된 비즈니스 로직을 처리하는
 * 인터페이스입니다.
 * @author airpo
 * @version 1.1
 *
 */
public interface MemberService {
	
	/**
	 * 회원가입을 수행하는 method
	 * @param member 회원정보 처리를 위한 정보의 값들이 저장되어 있는 VO
	 */
	public void memberJoin(MemberVO member) ;
	
	/**
	 * 아이디 중복 체크 판단을 수행하는 method
	 * @param memberID view에서 받아온 사용자가 입력한 id값
	 */
	public int idCheck(String memberID);
	
	/**
	 * 사용자가 회원가입 시 입력한 메일로 인증번호를 전송하기 위해
	 * 인증번호 난수생성을 하는 method
	 * 수정사항 현재 보안상 치명적인 문제가 있어 수정중
	 * 수정이 완료될 때까지 인증번호 기능은 사용하지 않을 예정.
	 */
//	public int authRandomNumber();
	
	/**
	 * 사용자가 회원가입 시 입력한 메일로 인증번호 보내는 method
	 * @param email  사용자가 입력한 메일 주소
	 * @param mailCheckNumber  위 athRandomNumber() method에서 생성된 인증번호
	 * 수정사항 현재 보안상 치명적인 문제가 있어 수정중
	 * 수정이 완료될 때까지 인증번호 기능은 사용하지 않을 예정.
	 */
//	public void sendMail(String email, int mailCheckNumber) throws Exception;
	
	/**
	 * 로그인을 수행하는 method
	 */
	public MemberVO memberLogin(MemberVO member);

//	public int mailAuthInquire(int mailCheck);
}
