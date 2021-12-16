package com.study.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.service.MemberService;
import com.study.vo.MemberVO;


/**
 * 회원관리(회원가입, 로그인)등의 요청을 view단에서 받아 하위계층(service)로 
 * 넘겨주는 역할을 하는 컨트롤러입니다.
 * @author airpo
 * @version 1.1
 *
 */
@Controller
@RequestMapping("/")
public class MemberController {

	private static final Logger log = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;

	/**
	 * 회원가입 페이지 화면으로 이동하는 method
	 * @param member 회원정보 처리를 위한 정보의 값들이 저장되어 있는 VO
	 * @return 회원가입 페이지로 리다이렉트
	 */
	@RequestMapping(value = "/member/joinView", method = RequestMethod.POST)
	public String joinView(MemberVO member) {

		return "/member/join";
	}
	

	/**
	 * 회원가입 요청을 처리하는 method
	 * @param member 회원정보 처리를 위한 정보의 값들이 저장되어 있는 VO
	 * @return 공지게시판의 목록화면으로 리다이렉트
	 */
	@RequestMapping(value = "/member/join", method = RequestMethod.POST)
	public String memberJoin(MemberVO member) {
		
		/**
		 * member 객체를 담아 회원가입에 해당하는 서비스계층으로 전달.
		 */
		try {
			memberService.memberJoin(member);
			
		}catch(Exception e) {
			log.info("[/member/join] error : {}", e);
//			return "redirect:/member/join";
		}
		log.info("[MEMBER] PARAM member : {}", member.toString());
		return "redirect:/notice/list";
	}
	
	//유효성검사 함수
	/*
	static boolean joinNullCheck(String str) {
		return str == null || str.trim().isEmpty(); //null값일 시 true
	}*/

	/**
	 * ID 중복검사를 처리하는 method
	 * @param memberID view에서 전달한 사용자가 입력한 id값
	 * @return 중복아이디 존재여부에 대한 결과값을 string형으로 view에 반환
	 */
	@RequestMapping(value = "/member/memberIdCheck", method = RequestMethod.POST)
	@ResponseBody
	public String memberIdCheck(String memberID) throws Exception {
		log.info("[MEMBER] PARAM memberId : {}", memberID);

		/**
		 * 사용자가 입력한 id와 db에 저장되어 있는 id값의 일치여부를 판단 후
		 * 결과에 따라 존재할 시 1, 존재하지 않을 시 0을 저장
		 */
		int result = memberService.idCheck(memberID);
		log.info("[MEMBER] PARAM id Check Result : {}", result);
		
		/**
		 * id 중복검사
		 * 일치하는 id가 db에 존재할 시 "fail" string값을 반환하고
		 * id가 db에 존재하지 않을 시 "success" string값을 반환함.
		 */
		if (result != 0) {
			return "fail"; //중복 아이디 존재
		} else {
			return "success";  //중복 아이디 존재x
		}

	}

	
	/**
	 * 메일 인증번호 검사를 처리하는 method
	 * @param email 사용자가 입력한 메일 주소
	 * @return 메일 인증번호를 view로 반환-> 수정사항 현재 보안상 치명적인 문제가 있어 수정중
	 * 수정이 완료될 때까지 인증번호 기능은 사용하지 않을 예정.
	 */
//	@RequestMapping(value = "/member/mailCheck", method = RequestMethod.POST)
//	@ResponseBody
//	public String mailCheck(String email) throws Exception {
//		log.info("[MEMBER] PARAM mail Check", email);
//
////		memberService.authRandomNumber();
//		
////		memberService.mailAuthInquire(mailAuth);
//		
////		log.info("인증번호:{}", memberService.mailAuthInquire(mailAuth));
//		log.info("인증번호:{}", memberService.authRandomNumber());
//		
//		int mailCheckNumber = memberService.authRandomNumber();
//		log.info("인증번호" + mailCheckNumber);
//		memberService.sendMail(email, mailCheckNumber);
//
//		String sendMailCheckNumber = Integer.toString(mailCheckNumber);
//
//		return sendMailCheckNumber;
////		return mailCheckNumber;
////		return null;
//	}

	/**
	 *  로그인 페이지 화면으로 이동하는 method
	 * @return 로그인 페이지로 리다이렉트
	 */
	@RequestMapping(value = "/member/loginView", method = RequestMethod.POST)
	public String loginView() {

		return "/member/login";
		}
	
	/**
	 * 로그인 요청을 처리하는 method
	 * @param member 회원정보 처리를 위한 정보의 값들이 저장되어 있는 VO
	 * @param request  로그인 성공 시 session에 멤버id값을 저장하기 위해 사용
	 * @param redirect 로그인 실패 시 리다이렉트된 로그인 페이지에 실패를 의미하는 데이터를 전송하기 위해 사용
	 * @return 로그인 실패 시 로그인 페이지 리다이렉트, 성공 시 공지게시판의 게시글 리스트 페이지로 리다이렉트
	 */
	@RequestMapping(value="/member/login", method = RequestMethod.POST)
	public String memberLogin(MemberVO member, HttpServletRequest request, RedirectAttributes redirect) throws Exception {

		/**
		 * 로그인 성공 시 session에 멤버id값을 저장하기 위해 사용
		 */
		HttpSession session = request.getSession();
		/**
		 * member객체의 정보를 담아 로그인요청을 처리하는 서비스계층으로 전송
		 */
		MemberVO loginMember = memberService.memberLogin(member);
		
		
		/**
		 * db에 저장된 member id,pw값과 사용자가 입력한 member id,pw값 일치 여부 판단
		 * db에 일치하는 정보가 없으면 result=0의 값을 view에 전달 후, 로그인 페이지로 리다이렉트하고
		 * 일치하는 정보가 있으면 해당 member객체의 id값을 가져와 session에 저장 후, 공지게시판의 게시글 리스트 페이지로 리다이렉트한다.
		 * session에 member객체의 모든 데이터가 아닌 id값만 저장하는 이유는
		 * pw같은 치명적인 정보를 탈취당할 위험이 있어 최소한의 식별 정보인 id값만 저장해둔다.
		 */
		if(loginMember == null) {
			int result = 0;
			redirect.addFlashAttribute("result", result);
			return "redirect:/member/login";
		}
		log.info("[/MEMBER/LOGIN] memeberID param : {}  ", loginMember.getMemberID());
		/**
		 * session에 member객체의 id값 저장
		 */
		session.setAttribute("member", loginMember.getMemberID());
		log.info("[/MEMBER/LOGIN] session param : {}  ", session.getAttribute("member"));
		return "redirect:/notice/list";
		}
	
	/**
	 * 로그아웃 요청을 처리하는 method
	 * @param request 로그아웃 요청 시 session을 무효화시키기 위해 사용
	 * @return 로그아웃 후 공지게시판의 게시글 리스트 페이지로 리다이렉트
	 */
	@RequestMapping(value="/member/logout", method=RequestMethod.POST)
	public String memberLogout(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		
		/**
		 * 로그아웃 요청 시 사용될 세션이 없기 때문에 
		 * 세선을 무효화시키기 위해 invalidate() 사용
		 */
		session.invalidate();
		
		return "redirect:/notice/list";
	}

}
