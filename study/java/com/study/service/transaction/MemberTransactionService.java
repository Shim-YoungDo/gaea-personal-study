package com.study.service.transaction;

import com.study.vo.MemberVO;

/**
 * 회원관리(회원가입, 로그인)와 트랜잭션 관련된 비즈니스 로직을 테스트하는
 * 인터페이스입니다.
 * 
 * @author ydshim
 *
 */
public interface MemberTransactionService {
	/**
	 * 회원가입을 수행하는 method
	 * @param member 회원정보 처리를 위한 정보의 값들이 저장되어 있는 VO
	 * @return 
	 */
	public void memberJoin(MemberVO member);
}
