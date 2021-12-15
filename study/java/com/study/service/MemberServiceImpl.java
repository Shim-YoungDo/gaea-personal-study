package com.study.service;

import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.study.mapper.MemberMapper;
import com.study.vo.MemberVO;

/*회원관리(회원가입, 로그인) 서비스 구현체*/
@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	private JavaMailSender mailSender;
	
	//회원가입
	@Override
	public void memberJoin(MemberVO member) {
		
		mapper.memberJoin(member);
	}
	
	//중복 ID 검사
	@Override
	public int idCheck(String memberID){
		
		return mapper.idCheck(memberID);
	}
	
	//메일 인증번호 난수 생성
	@Override
	public Integer authRandomNumber(){
		Random random = new Random();
		
		int mailCheckNumber = random.nextInt(999999-111111)+111111;  //min~max사이 난수 생성 => (max-min)+min
		
		return mapper.authRandomNumber(mailCheckNumber);           
	}//->원래대로
	
	@Override
	public int mailAuthInquire(int mailCheck) {
		return mapper.mailAuthInquire(mailCheck);
	}
	
	/*
	//smtp
	@Override
	public void sendMail(String email, int mailCheckNumber) throws Exception{
		String sendingMail = "shimdev1216@naver.com";
		String userMail = email;
		String mailTitle = "회원가입 인증 이메일입니다.";
		String mailContent = 
				"인증번호는" + mailCheckNumber + "입니다." + "<br>" + "해당 인증번호를 인증번호 입력란에 기입해 주세요.";
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "utf-8");
			
			messageHelper.setFrom(sendingMail);
			messageHelper.setTo(userMail);
			messageHelper.setSubject(mailTitle);
			messageHelper.setText(mailContent, true);
			mailSender.send(message);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
	//로그인
	@Override
	public MemberVO memberLogin(MemberVO member){
		return mapper.memberLogin(member);
	}
}
