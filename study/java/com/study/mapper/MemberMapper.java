package com.study.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.study.vo.MemberVO;

/**
 * 회원가입, 로그인 등의 처리요청을 쿼리문으로 넘겨주는
 * Mapper 인터페이스입니다.
 * 
 * 
 * @author airpo
 *
 */
@Mapper
public interface MemberMapper {

	/**
	 * 회원가입을 수행하는 mapper method
	 * @param member 회원정보 처리를 위한 정보의 값들이 저장되어 있는 VO
	 */
	public void memberJoin(MemberVO member);
	
	/**
	 * 아이디 중복 체크 판단을 수행하는 mapper method
	 * 사용자가 입력한 id값을 받아와 db에 저장된 id값과 비교 후 중복체크 수행
	 * @param memberID view에서 받아온 사용자가 입력한 id값
	 */
	public int idCheck(String memberID);
	
	/**
	 * 로그인을 수행하는 mapper method
	 * @param member 회원정보 처리를 위한 정보의 값들이 저장되어 있는 VO
	 */
	public MemberVO memberLogin(MemberVO member);
	
//	//난수 저장
//	public Integer authRandomNumber(int mailCheckNumber);
//	
//	
//	public int mailAuthInquire(int mailCheck);
}
