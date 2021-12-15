package com.study.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.study.vo.MemberVO;

/*회원관리(회원가입, 로그인) 매퍼*/
@Mapper
public interface MemberMapper {

	//회원가입
	public void memberJoin(MemberVO member);
	
	//아이디 중복 체크
	public int idCheck(String memberID);
	
	//로그인
	public MemberVO memberLogin(MemberVO member);
	
	//난수 저장
	public Integer authRandomNumber(int mailCheckNumber);
	
	
	public int mailAuthInquire(int mailCheck);
}
