package com.study.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.service.MemberService;
import com.study.service.transaction.MemberTransactionService;
import com.study.vo.MemberVO;

/**
 * 회원관리(회원가입, 로그인)등의 요청을 view단에서 받아 하위계층(service)로 넘겨주는 역할을 하는 컨트롤러입니다.
 * 
 * @author ydshim
 *
 */
@Controller
@RequestMapping("/")
public class MemberController {

	private static final Logger log = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberTransactionService transactionService;

	/**
	 * 회원가입 페이지 화면으로 이동하는 method
	 * 
	 * @param member 회원정보 처리를 위한 정보의 값들이 저장되어 있는 VO
	 * @return 회원가입 페이지로 리다이렉트
	 */
	@RequestMapping(value = "/member/joinView", method = RequestMethod.POST)
	public String joinView(MemberVO member) {

		return "/member/join";
	}

	/**
	 * 회원가입 요청을 처리하는 method
	 * 
	 * @param member 회원정보 처리를 위한 정보의 값들이 저장되어 있는 VO
	 * @return 공지게시판의 목록화면으로 리다이렉트
	 */
	@RequestMapping(value = "/member/join", method = RequestMethod.POST)
	public String memberJoin(MemberVO member) {

		/**
		 * member 객체를 담아 회원가입에 해당하는 서비스계층으로 전달.
		 */
		memberService.memberJoin(member);
		log.info("[/member/join] PARAM member : {}", member.toString());
		return "redirect:/notice/list";
	}

	/**
	 * ID 중복검사를 처리하는 method
	 * 
	 * @param memberID view에서 전달한 사용자가 입력한 id값
	 * @return 중복아이디 존재여부에 대한 결과값을 string형으로 view에 반환
	 */
	@RequestMapping(value = "/member/memberIdCheck", method = RequestMethod.POST)
	@ResponseBody
	public String memberIdCheck(String memberID) throws Exception {
		log.info("[/member/memberIdCheck] PARAM memberId : {}", memberID);

		/**
		 * 사용자가 입력한 id와 db에 저장되어 있는 id값의 일치여부를 판단 후 결과에 따라 존재할 시 1, 존재하지 않을 시 0을 저장
		 */
		int result = memberService.idCheck(memberID);
		log.info("[/member/memberIdCheck] PARAM id Check Result : {}", result);

		/**
		 * id 중복검사 일치하는 id가 db에 존재할 시 "fail" string값을 반환하고 id가 db에 존재하지 않을 시 "success"
		 * string값을 반환함.
		 */
		if (result != 0) {
			return "fail"; // 중복 아이디 존재
		} else {
			return "success"; // 중복 아이디 존재x
		}

	}

	/**
	 * 로그인 페이지 화면으로 이동하는 method
	 * 
	 * @return 로그인 페이지로 리다이렉트
	 */
	@RequestMapping(value = "/member/loginView", method = RequestMethod.GET)
	public String loginView() {

		
		return "/member/login";
	}

	/**
	 * 로그인 요청을 처리하는 method
	 * 
	 * @param member   회원정보 처리를 위한 정보의 값들이 저장되어 있는 VO
	 * @param request  로그인 성공 시 session에 멤버id값을 저장하기 위해 사용
	 * @param redirect 로그인 실패 시 리다이렉트된 로그인 페이지에 실패를 의미하는 데이터를 전송하기 위해 사용
	 * @return 로그인 실패 시 로그인 페이지 리다이렉트, 성공 시 공지게시판의 게시글 리스트 페이지로 리다이렉트
	 */
	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
	public String memberLogin(MemberVO member, HttpServletRequest request, RedirectAttributes redirect)
			throws Exception {

		/**
		 * 로그인 성공 시 session에 멤버id값을 저장하기 위해 사용
		 */
		HttpSession session = request.getSession();
		/**
		 * member객체의 정보를 담아 로그인요청을 처리하는 서비스계층으로 전송
		 */
		MemberVO loginMember = memberService.memberLogin(member);

		/**
		 * db에 저장된 member id,pw값과 사용자가 입력한 member id,pw값 일치 여부 판단 db에 일치하는 정보가 없으면
		 * result=0의 값을 view에 전달 후, 로그인 페이지로 리다이렉트하고 일치하는 정보가 있으면 해당 member객체의 id값을 가져와
		 * session에 저장 후, 공지게시판의 게시글 리스트 페이지로 리다이렉트한다
		 */
		if(loginMember == null) {
			int result = 0;
			redirect.addFlashAttribute("result", result);
			return "redirect:/member/loginView";
		}
//		log.info("[/member/login] memeberID param : {}  ", loginMember.getMemberID());
		
		String memberID = loginMember.getMemberID();
		int dormancyResult = memberService.dormancyCheck(memberID);
		log.info("[/member/login] memberID param : {}  ", memberID);
		log.info("[/member/login] humanResult : {}  ", dormancyResult);
		if(dormancyResult==0) {
			int memberDormancyResult = 0;
//			redirect.addFlashAttribute("viewResult", viewResult);
			session.setAttribute("memberDormancyResult", memberDormancyResult);
			session.setAttribute("member", memberID);
			log.info("[/member/login] session param ID : {}  ", session.getAttribute("member"));
//			return "redirect:/member/loginView";
			return "redirect:/notice/list";
		}
		/**
		 * session에 member객체의 id값 저장
		 */
		session.setAttribute("member", memberID);
		memberService.memberLoginDateUpdate(memberID);
		log.info("[/member/login] session param ID : {}  ", session.getAttribute("member"));
		return "redirect:/notice/list";
	}

	/**
	 * 로그아웃 요청을 처리하는 method
	 * 
	 * @param request 로그아웃 요청 시 session을 무효화시키기 위해 사용
	 * @return 로그아웃 후 공지게시판의 게시글 리스트 페이지로 리다이렉트
	 */
	@RequestMapping(value = "/member/logout", method = RequestMethod.POST)
	public String memberLogout(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();

		/**
		 * 로그아웃 요청 시 사용될 세션이 없기 때문에 세선을 무효화시키기 위해 invalidate() 사용
		 */
		session.invalidate();

		return "redirect:/notice/list";
	}
	
	/**
	 * 휴면해제 화면으로 이동하는 method
	 * @return 휴면해제 화면으로 이동
	 */
	@RequestMapping(value = "/member/dormancyView", method=RequestMethod.GET)
	public String memberDormancyOffView() {
		return "/member/dormancy";
	}
	
	/**
	 * 휴면해제 요청을 처리하는 method
	 * @param member 회원정보 처리를 위한 정보의 값들이 저장되어 있는 VO
	 * @param request 휴면상태를 session에 저장하기 위해 사용
	 * @param redirect 사용자 정보가 일치하지 않을 시 정보를 다시 입력하라는 데이터 출력하기 위해 사용
	 * @return 전체 페이지 리스트로 이동
	 */
	@RequestMapping(value = "/member/dormancy", method=RequestMethod.POST)
	public String memberDoramncyOff(MemberVO member, HttpServletRequest request, RedirectAttributes redirect) {
		HttpSession session = request.getSession();
		
		int result = memberService.infoMatch(member);
		log.info("[/member/dormancy] result: "+result);
//		log.info("[/member/dormancy] session param ID : {}  ", session.getAttribute("member"));
		if(result == 0) {
			int matchResult = 0;
			redirect.addFlashAttribute("result", matchResult);
			return "redirect:/member/dormancyView";
		}
		String memberID = member.getMemberID();
		session.setAttribute("memberDormancyResult", result);
		log.info("[/member/dormancy] memberID: "+memberID);
		
		memberService.memberConvertNormalcy(memberID);
		log.info("[/member/dormancy] session param ID : {}  ", session.getAttribute("member"));
		log.info("[/member/dormancy] session param dormancyResult : {}  ", session.getAttribute("memberDormancyResult"));
		return "redirect:/notice/list";
	}
}
