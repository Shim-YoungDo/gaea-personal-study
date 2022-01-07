package com.study.controller;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.service.NoticeService;
import com.study.vo.Criteria;
import com.study.vo.PageMakeDTO;

/**
 * 공지게시판의 전체 게시글 조회를 json으로 처리한 컨트롤러입니다.
 * @author ydshim
 *
 */
@Controller
@RequestMapping("/")
public class NoticeJsonController {

	private static final Logger log = LoggerFactory.getLogger(NoticeJsonController.class);

//	@Autowired
//	private NoticeJsonService jsonService;

	@Autowired
	private NoticeService noticeService;

	/**
	 * 공지 게시글 전체 리스트 데이터를 json형태로 변경해 전송해주는 method
	 * @param cri 현재 페이지와 페이지 당 게시글 수 정보를 담고있음
	 * @return json형태로 변환한 공지 게시글 전체 리스트 데이터 view로 반환
	 */
	@RequestMapping(value = "/notice/jsonlist", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> noticeTotalListConvertJson(Criteria cri) {
//		CafeNoticeVO notice = new CafeNoticeVO();

		/**
		 * 총 게시글 갯수
		 */
		int noticeTotal = noticeService.getNoticeTotal(cri);

		/**
		 * 현재 페이지와 한 페이지 당 게시글 수, 총 게시글 갯수 정보를 페이지 정보를 표시하기 위한 VO에 저장
		 */
		PageMakeDTO pageMake = new PageMakeDTO(cri, noticeTotal);

		HashMap<String, Object> noticeTotalList = new HashMap<>();
		HashMap<String, Object> noticePage = new HashMap<>();

		noticeTotalList.put("list", noticeService.getNoticePagingList(cri));
		noticePage.put("pageMake", pageMake);

		JSONObject convertJson = new JSONObject();
		convertJson.putAll(noticeTotalList);
		convertJson.putAll(noticePage);

		log.info("[/notice/jsonlist] PARAM noticeTotalList : " + noticeTotalList);
		log.info("[/notice/jsonlist] PARAM noticePage : " + noticePage);
		log.info("[/notice/jsonlist] PARAM JSON : " + convertJson.toJSONString());

		return convertJson;
	}

	/**
	 * view에서 넘어온 페이지 데이터를 받아 다시 view로 반환하는
	 * 역할을 하는 method
	 * @param cri 현재 페이지와 페이지 당 게시글 수 정보를 담고있음
	 * @param model view에 데이터를 전달하기 위한 객체
	 * @return 공지 게시글 전체 리스트 데이터를 json형태로 변경해주는 method 호출
	 */
	@RequestMapping(value = "/notice/totalList", method = RequestMethod.GET)
	public String noticeChangePageNumber(Criteria cri, Model model) {
		// PageMakeDTO pageMake = new PageMakeDTO(cri, noticeTotal);

		log.info("[/notice/totalList] PARAM noticePage : " + cri);
		 model.addAttribute("pageNum",cri.getPageNum());
		 model.addAttribute("amount",cri.getAmount());
		return "/notice/jsonlist";
	}

}
