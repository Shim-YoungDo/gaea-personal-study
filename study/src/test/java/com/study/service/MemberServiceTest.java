package com.study.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.study.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberServiceTest {
	private static final Logger log =LoggerFactory.getLogger(MemberServiceTest.class);
	
	@Test
	public void membeVONullTest() {
		MemberVO vo = new MemberVO();
		
		vo.setMemberID("");
		
		log.info(vo.toString());
		
	}

}
