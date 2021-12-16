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

/**
 * 회원관리(회원가입, 로그인)와 관련된 비즈니스 로직을 처리하는
 * 구현체입니다.
 * @author airpo
 *
 */
@Service
public class MemberServiceImpl implements MemberService {
	
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
	public void memberJoin(MemberVO member) {
		
		mapper.memberJoin(member);
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
	 * 사용자가 회원가입 시 입력한 메일로 인증번호를 발송하기 위해
	 * 메일 인증번호 난수를 생성하는 구현체 method
	 * @return 생성된 메일 인증번호
	 * 수정사항 현재 보안상 치명적인 문제가 있어 수정중
	 * 수정이 완료될 때까지 인증번호 기능은 사용하지 않을 예정.
	 */
//	@Override
//	public int authRandomNumber() {
//		Random random = new Random();
//		
//		int mailCheckNumber = random.nextInt(999999-111111)+111111;  //min~max사이 난수 생성 => (max-min)+min
//		
//		return mailCheckNumber;
////		return mapper.authRandomNumber(mailCheckNumber);           
//	}
	
	
	/**
	 * 사용자가 회원가입 시 입력한 메일로 인증번호 보내는 구현체 method
	 * @param email  사용자가 입력한 메일 주소
	 * @param mailCheckNumber  위 athRandomNumber() method에서 생성된 인증번호
	 * 수정사항 현재 보안상 치명적인 문제가 있어 수정중
	 * 수정이 완료될 때까지 인증번호 기능은 사용하지 않을 예정.
	 */
//	@Override
//	public void sendMail(String email, int mailCheckNumber) throws Exception{
//		String sendingMail = "shimdev1216@naver.com";
//		String userMail = email;
//		String mailTitle = "회원가입 인증 이메일입니다.";
//		String mailContent = 
//				"인증번호는" + mailCheckNumber + "입니다." + "<br>" + "해당 인증번호를 인증번호 입력란에 기입해 주세요.";
//		
//		try {
//			MimeMessage message = mailSender.createMimeMessage();
//			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "utf-8");
//			
//			messageHelper.setFrom(sendingMail);
//			messageHelper.setTo(userMail);
//			messageHelper.setSubject(mailTitle);
//			messageHelper.setText(mailContent, true);
//			mailSender.send(message);
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	
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
