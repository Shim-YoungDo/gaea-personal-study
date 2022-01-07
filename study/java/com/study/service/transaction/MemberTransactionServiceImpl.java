package com.study.service.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.mapper.MemberMapper;
import com.study.service.MemberServiceImpl;
import com.study.vo.MemberVO;

/**
 * 회원관리(회원가입, 로그인)와 트랜잭션 관련된 비즈니스 로직을 테스트하는
 * 구현체입니다.
 * 
 * @author ydshim
 *
 */
@Service
public class MemberTransactionServiceImpl implements MemberTransactionService {
	
	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);

	
	@Autowired
	private MemberMapper mapper;
	
	@Override
	public void memberJoin(MemberVO member) {
		
		MemberVO cafeMember = new MemberVO();
		
		cafeMember.setMemberID("test123");
		cafeMember.setMemberPW("222");
		cafeMember.setMemberName("dfdf");
		cafeMember.setMemberMail("9999");
		
		mapper.memberJoin(member);
		mapper.memberJoin(cafeMember);

	}

}
