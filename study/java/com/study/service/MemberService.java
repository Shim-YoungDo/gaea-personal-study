package com.study.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DuplicateKeyException;

import com.study.vo.CafeNoticeVO;
import com.study.vo.MemberVO;

/**
 * 회원관리(회원가입, 로그인)와 관련된 비즈니스 로직을 처리하는
 * 인터페이스입니다.
 * 
 * @author ydshim
 *
 */
public interface MemberService {
	
	/**
	 * 회원가입을 수행하는 method
	 * @param member 회원정보 처리를 위한 정보의 값들이 저장되어 있는 VO
	 * @return 
	 */
	public void memberJoin(MemberVO member);
	
	/**
	 * 아이디 중복 체크 판단을 수행하는 method
	 * @param memberID view에서 받아온 사용자가 입력한 id값
	 */
	public int idCheck(String memberID);
	
	/**
	 * 로그인을 수행하는 method
	 */
	public MemberVO memberLogin(MemberVO member);
	
	
	public void memberDelete(String memberID);
}
