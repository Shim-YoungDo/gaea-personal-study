package com.study.service.transaction;

import com.study.vo.MemberVO;

public interface MemberTransactionService {
	/**
	 * 회원가입을 수행하는 method
	 * @param member 회원정보 처리를 위한 정보의 값들이 저장되어 있는 VO
	 * @return 
	 */
	public void memberJoin(MemberVO member);
}
