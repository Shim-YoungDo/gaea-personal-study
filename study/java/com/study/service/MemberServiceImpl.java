package com.study.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.study.mapper.MemberMapper;
import com.study.vo.MemberVO;

/**
 * 회원관리(회원가입, 로그인)와 관련된 비즈니스 로직을 처리하는
 * 구현체입니다.
 * @author airpo
 *
 */
@Service
public class MemberServiceImpl implements MemberService {
	
	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Autowired
	private MemberMapper mapper;
	
	/**
	 * 메일 발송기능을 지원하는 인터페이스
	 */
//	@Autowired
//	private JavaMailSender mailSender;
	
	/**
	 * 회원가입을 수행하는 구현체 method
	 */
	@Override
	public void memberJoin(MemberVO member)  {
		
		try{
			mapper.memberJoin(member);
	
		}catch(DataAccessException e) {
			log.info("[Member Join Mapping] error : {}", e);
		}
	}
	
	/**
	 * 중복 ID 검사를 수행하는 구현체 method
	 * @param memberID view에서 받아온 사용자가 입력한 id값
	 * @return 사용자가 입력한 id값을 담아 중복 id 검사를 처리하는 쿼리와 매핑
	 */
	@Override
	public int idCheck(String memberID){
		
		return mapper.idCheck(memberID);
	}
	
	/**
	 * 로그인을 수행하는 구현체 method
	 * @param member 회원정보 처리를 위한 정보의 값들이 저장되어 있는 VO
	 * @return 사용자가 입력한 id, pw값과 db에 저장된 id, pw값 중 일치하는 값을 찾아주는 쿼리와 매핑
	 */
	@Override
	public MemberVO memberLogin(MemberVO member){
		return mapper.memberLogin(member);
	}
}
