package com.study.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.study.controller.BoardController;
import com.study.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberMapperTest {
	
	private static final Logger log = LoggerFactory.getLogger(MemberMapperTest.class);
	@Autowired
	private MemberMapper mapper;
	
	/*
	//회원가입 테스트
	@Test
	public void joinMapperTest() {
		MemberVO member = new MemberVO();
		member.setMemberID("333333");
		member.setMemberPW("44444");
		member.setMemberName("234234");
		member.setMemberMail("22222");
		member.setAdminCheck(1);
		
		mapper.memberJoin(member);
		
		
	}
	*/
	
	/*
	//아이디 중복 테스트
	@Test
	public void idCheckTest() {
		String duplicateId="test123";
		String unDuplicateId="1111";
//		if(mapper.idCheck(duplicateId) == 1) {
//			log.info("중복됨");
//		}
//		else {
//			log.info("중복안됨");
//		}
		log.info("idCheck:", mapper.idCheck(duplicateId));
		log.info("idCheck:", mapper.idCheck(unDuplicateId));
		
	}
	*/
	
	//로그인 기능 테스트
	@Test
	public void memberLoginTest() {
		MemberVO member = new MemberVO();
		
		member.setMemberID("어어어");
		member.setMemberPW("asdf123");
//		mapper.memberLogin(member);
		
		System.out.println("결과: "+mapper.memberLogin(member));
	}
	

	
	

}
