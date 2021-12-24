package com.study.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.study.service.NoticeService;
import com.study.vo.CafeNoticeVO;
import com.study.vo.Criteria;
import com.study.vo.PageMakeDTO;


/**
 * 공지게시판의 게시글 등록, 조회, 수정, 삭제 등의 요청을 view단에서 받아 하위 레이어(service)로 
 * 넘겨주는 역할을 하는 컨트롤러입니다.
 * @author ydshim
 *
 */
@Controller
@RequestMapping("/")
public class NoticeController {
	
	private static final Logger log = LoggerFactory.getLogger(NoticeController.class);
	
	@Autowired
	private NoticeService service;
	
	/**
	 * 게시글 리스트 화면을 출력하는 method
	 * @param model view에 데이터를 전달하기 위한 객체
	 * @param cri 현재 페이지와 페이지 당 게시글 수 정보를 담고있음
	 */
	@RequestMapping(value="/notice/list", method = RequestMethod.GET)
	public void noticeList(Model model, Criteria cri) {
		log.info("[/notice/list] PARAM pagenum : {}", cri.getPageNum());
		
		log.info("[/notice/list] PARAM amount : {}", cri.getAmount()); 
		
		CafeNoticeVO vo = new CafeNoticeVO();

		/**
		 * 현재 페이지, 한 페이지 당 게시글 수 정보를 담아 게시글 리스트를 조회 후 list에 담아
		 * view에 전달
		 */
//		List<Map<String, Object>> pageList = service.getNoticePagingList(cri);
		model.addAttribute("list", service.getNoticePagingList(cri));
		log.info("[/notice/list] PARAM cri : {}, list: {}", cri.toString(), service.getNoticePagingList(cri)); 
		
		log.info("[/notice/list] PARAM vo list : {}", vo.toString()); 
		
		/**
		 * 총 게시글 갯수
		 */
		int noticeTotal = service.getNoticeTotal(cri);
		
		/**
		 * 현재 페이지와 한 페이지 당 게시글 수, 총 게시글 갯수 정보를
		 * 페이지 정보를 표시하기 위한 VO에 저장
		 */
		PageMakeDTO pageMake = new PageMakeDTO(cri, noticeTotal);
		
		/**
		 * 페이지 정보 pageMake라는 이름으로 model객체에 담아 view로 전달
		 */
		model.addAttribute("pageMake", pageMake);
	}
	
	/**
	 * 게시글 등록 화면으로 이동하는 method
	 * @param model view에 데이터를 전달하기 위한 객체
	 * @param cri 현재 페이지와 페이지 당 게시글 수 정보를 담고있음
	 * @return 게시글 등록화면으로 이동
	 */
	@RequestMapping(value="/notice/registeView", method=RequestMethod.POST)
	public String registerView(Model model, Criteria cri) {
		
		model.addAttribute("cri", cri);
		return "/notice/registeView";
	}
	
	/**
	 * 게시글 등록하는 method
	 * @param notice 공지게시판 관련 작업 처리 시 필요한 VO
	 * @return 공지사항 게시글 목록으로 리다이렉트
	 */
	@RequestMapping(value="/notice/register", method=RequestMethod.POST)
	public String noticeRegister(CafeNoticeVO notice) {
		
		log.info("[/notice/register] PARAM notice : {}  ", notice.toString());
		service.noticeRegister(notice);
		return "redirect:/notice/list";
	}
	
	/**
	 * 게시글 조회화면을 출력하는 method
	 * @param noticeNumber 게시글 고유 식별 번호 [PK]
	 * @param model view에 데이터를 전달하기 위한 객체
	 * @param cri 현재 페이지와 페이지 당 게시글 수 정보를 담고있음
	 * @return 게시글 상세조회 페이지로 이동
	 */
	@RequestMapping(value="/notice/getPage", method=RequestMethod.POST)
	public String noticeInquire(int noticeNumber, Model model, Criteria cri) {
		
		log.info("[/notice/getPage] PARAM noticeNumber : {}  ", noticeNumber);
		
		/**
		 * 게시글 고유 식별 번호를 이용해 해당되는 게시글 정보를 pageInfo에 담아
		 * view로 전달
		 */
		model.addAttribute("pageInfo", service.noticeInquired(noticeNumber));
		
		model.addAttribute("cri", cri);
		return "/notice/getPage";
	}
	
	/**
	 * 게시글 수정페이지 화면을 출력하는 method
	 * @param noticeNumber 게시글 고유 식별 번호 [PK]
	 * @param model view에 데이터를 전달하기 위한 객체
	 * @param cri 현재 페이지와 페이지 당 게시글 수 정보를 담고있음
	 * @return 게시글 수정 페이지로 이동
	 */
	@RequestMapping(value="/notice/modifyView", method=RequestMethod.POST)
	public String modifyView(int noticeNumber, Model model, Criteria cri) {
		
		log.info("[/notice/modifyView] PARAM noticeNumber : {} : ", noticeNumber);
		
		/**
		 * 게시글 고유 식별 번호를 이용해 해당되는 게시글 정보를 pageInfo에 담아
		 * view로 전달
		 * 수정페이지이기 때문에 기존 페이지에 저장되어 있던 제목, 내용, 글쓴이 정보가 필요하다.
		 */
		model.addAttribute("pageInfo", service.noticeInquired(noticeNumber));
		
		model.addAttribute("cri", cri);
		return "/notice/modifyView";
	}
	
	/**
	 * 게시글 수정하는 method
	 * @param notice 공지게시판 관련 작업 처리 시 필요한 VO
	 * @return 공지사항 게시글 목록으로 리다이렉트
	 */
	@RequestMapping(value="/notice/modify", method=RequestMethod.POST)
	public String noticeModify(CafeNoticeVO notice) {
		
		/**
		 * notice VO객체를 담아 서비스계층으로 전달함.
		 */
		service.noticeModify(notice);

		/**
		 * 수정된 데이터
		 */
		log.info("[/notice/modify] PARAM modify : {} : ", notice.toString());
		
		return "redirect:/notice/list";
	}
	
	/**
	 * 게시글 삭제
	 * @param noticeNumber 게시글 고유 식별 번호 [PK]
	 * @return 공지사항 게시글 목록으로 리다이렉트
	 */
	@RequestMapping(value="/notice/delete", method=RequestMethod.POST)
	public String noticeDelete(int noticeNumber) {
		
		/**
		 * 게시글 고유 식별 번호를 담아 
		 * 서비스계층으로 전달함.
		 */
		service.noticeDelete(noticeNumber);
		
		return "redirect:/notice/list";
	}

}