package com.study.service;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.study.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberServiceTestImpl {
	private static final Logger log =LoggerFactory.getLogger(MemberServiceTestImpl.class);
	@Autowired
	private MemberService service;
	
//	@Test
//	public void membeVONullTest() {
//		MemberVO vo = new MemberVO();
//		
//		vo.setMemberID("");
//		
//		log.info(vo.toString());
//		
//	}
	
	@Test
	public void joinMapperTest() throws DuplicateKeyException, SQLException {
		MemberVO member = new MemberVO() ;
		member.setMemberID("test14");
		member.setMemberPW("44444");
		member.setMemberName("234234");
		member.setMemberMail("22222");
		
		service.memberJoin(member);
		
		int result = service.idCheck(member.getMemberID());
		if(result == 1) {
			String joinResult = "join success";
			System.out.printf("result:", joinResult);
			service.memberDelete(member.getMemberID());
		}
		else {
			String joinResult = "join fail";
			System.out.printf("result:", joinResult);
		}
	}

}
