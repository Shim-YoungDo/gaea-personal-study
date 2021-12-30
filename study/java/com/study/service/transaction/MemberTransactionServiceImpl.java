package com.study.service.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.mapper.MemberMapper;
import com.study.service.MemberServiceImpl;
import com.study.vo.MemberVO;

@Service
public class MemberTransactionServiceImpl implements MemberTransactionService {
	
	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);

	
	@Autowired
	private MemberMapper mapper;
	
	@Override
	public void memberJoin(MemberVO member) {
		
		MemberVO memberTxTest = new MemberVO();
		
		memberTxTest.setMemberID("test123");
		memberTxTest.setMemberPW("222");
		memberTxTest.setMemberName("dfdf");
		memberTxTest.setMemberMail("9999");
		
		mapper.memberJoin(member);
		mapper.memberJoin(memberTxTest);

	}

}
