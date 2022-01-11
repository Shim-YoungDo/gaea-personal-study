package com.study.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.study.mapper.MemberMapper;
import com.study.vo.CafeNoticeVO;
import com.study.vo.MemberVO;

/**
 * 회원관리(회원가입, 로그인)와 관련된 비즈니스 로직을 처리하는
 * 구현체입니다.
 * 
 * @author ydshim
 *
 */
@Service
//@Transactional(propagation=Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService {
	
	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Autowired
	private MemberMapper mapper;
	
	/**
	 * 회원가입을 수행하는 구현체 method
	 */
	@Override
//	@Transactional(propagation=Propagation.REQUIRED)
	public void memberJoin(MemberVO member) {
		
		
//		String result = "fail";
//		try{	
//			mapper.memberJoin(member);
//			result = "success";
//	
//		}catch(DataAccessException joinException) {
//			log.info("[member Join Mapping] error : "+ joinException.getRootCause());
//		}
//		return result;
		
		
		
//		MemberVO cafeMember = new MemberVO();
//		
//		cafeMember.setMemberID("test123");
//		cafeMember.setMemberPW("222");
//		cafeMember.setMemberName("dfdf");
//		cafeMember.setMemberMail("9999");
//		
		mapper.memberJoin(member);
//		mapper.memberJoin(cafeMember);
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
	
	@Override
	public void memberLoginDateUpdate(String memberID) {
		mapper.memberLoginDateUpdate(memberID);
	}
	
	@Override
	public List<MemberVO> memberLoginDate() {
		return mapper.memberLoginDate();
	}
	
	@Override
	public int humanCheck(String memberID) {
		return mapper.humanCheck(memberID);
	}
	
	@Override
	public void memberConvertHuman(String memberID) {
		mapper.memberConvertHuman(memberID);
	}
	
	@Override
	public void memberDelete(String memberID) {
		mapper.memberDelete(memberID);
	}
}
