package com.study.service.Transaction;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.study.service.MemberService;
import com.study.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberServiceTransactionTestImpl {
	
	private static final Logger log =LoggerFactory.getLogger(MemberServiceTransactionTestImpl.class);

	@Autowired
	private MemberService service;
	
	@Test
	public void joinMapperTest() throws DuplicateKeyException, SQLException {
		MemberVO member = new MemberVO() ;
		member.setMemberID("test12");
		member.setMemberPW("12");
		member.setMemberName("234234");
		member.setMemberMail("22222");
		
		MemberVO member2 = new MemberVO() ;
		member2.setMemberID("test123");
		member2.setMemberPW("44444");
		member2.setMemberName("234234");
		member2.setMemberMail("22222");
		
		service.memberJoin(member);
		service.memberJoin(member2);
		
		int result = service.idCheck(member.getMemberID());
		log.info("[join test] result : "+ result);
		if(result == 1) {
			String joinResult = "join success";
			log.info("[join test] join result : "+ joinResult);
//			service.memberDelete(member.getMemberID());
		}
		else {
			String joinResult = "join fail";
			log.info("[join test] join result : "+ joinResult);
		}
	}

}
