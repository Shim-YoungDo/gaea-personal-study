package com.study.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.study.service.BoardService;
import com.study.vo.BoardVO;
import com.study.vo.Criteria;
import com.study.vo.PageMakeDTO;


/*게시글 CRUD관련 컨트롤러*/
@Controller
@RequestMapping("/")
public class BoardController {
	
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService service;
	
	//게시글 리스트 조회
	@RequestMapping(value="/board/list", method = RequestMethod.GET)
	public void boardList(Model model, Criteria cri) {
		log.info("[/board/list] PARAM pagenum : {}", cri.getPageNum()); //debug로 거는게 남. -> 상용에서 불필요한 데이터 입력 파라미터 필수. 검색안되는 로그는 쓰레기
		
		log.info("[/board/list] PARAM amount : {}", cri.getAmount()); 

		//페이징 적용
		model.addAttribute("list", service.getBoardPagingList(cri));
		
		int boardTotal = service.getBoardTotal(cri);
		
		PageMakeDTO pageMake = new PageMakeDTO(cri, boardTotal);
		
		model.addAttribute("pageMake", pageMake);
	}
	
	//게시글 등록 화면 이동
	@RequestMapping(value="/board/registeView", method=RequestMethod.GET)
	public void registerView(Model model, Criteria cri) {
		//log.info("게시판 등록페이지 진입");
		model.addAttribute("cri", cri);
	}
	
	//게시글 등록
	@RequestMapping(value="/board/register", method=RequestMethod.POST)
	public String boardRegister(BoardVO board) {
		log.info("[/board/register] PARAM board : {} : ", board.toString());
		service.boardRegister(board);
		return "redirect:/board/list";
	}
	
	//게시글 상세보기
	@RequestMapping(value="/board/getPage", method=RequestMethod.GET)
	public void boardInquire(int boardNumber, Model model, Criteria cri) {
		log.info("[/board/getPage] PARAM boardNumber : {} : ", boardNumber);
		
		model.addAttribute("pageInfo", service.boardInquire(boardNumber));
		
		model.addAttribute("cri", cri);
	}
	
	//게시글 수정페이지 이동
	@RequestMapping(value="/board/modifyView", method=RequestMethod.GET)
	public void modifyView(int boardNumber, Model model, Criteria cri) {
		log.info("[/board/modifyView] PARAM boardNumber : {} : ", boardNumber);
		
		model.addAttribute("pageInfo", service.boardInquire(boardNumber));
		
		model.addAttribute("cri", cri);
	}
	
	//게시글 수정
	@RequestMapping(value="/board/modify", method=RequestMethod.POST)
	public String boardModify(BoardVO board) {
		service.boardModify(board);

		log.info("[/board/modify] PARAM modify : {} : ", board.toString());
		
		return "redirect:/board/list";
	}
	
	//게시글 삭제
	@RequestMapping(value="/board/delete", method=RequestMethod.POST)
	public String boardDelete(int boardNumber) {
		
		service.boardDelete(boardNumber);
		
		
		return "redirect:/board/list";
	}

}
