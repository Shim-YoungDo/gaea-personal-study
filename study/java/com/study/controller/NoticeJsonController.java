package com.study.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.study.vo.CafeNoticeVO;
import com.study.vo.Criteria;
import com.study.vo.PageMakeDTO;

@Controller
@RequestMapping("/")
public class NoticeJsonController {
	
	private static final Logger log = LoggerFactory.getLogger(NoticeJsonController.class);
	
//	@Autowired
//	private NoticeJsonService jsonService;
	
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping(value = "/notice/jsonlist", method=RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> noticeListJson(Criteria cri) {
//		CafeNoticeVO notice = new CafeNoticeVO();
	
		/**
		 * 총 게시글 갯수
		 */
		int noticeTotal = noticeService.getNoticeTotal(cri);
		
		/**
		 * 현재 페이지와 한 페이지 당 게시글 수, 총 게시글 갯수 정보를
		 * 페이지 정보를 표시하기 위한 VO에 저장
		 */
		PageMakeDTO pageMake = new PageMakeDTO(cri, noticeTotal);
	
		HashMap<String, Object> noticeTotalList = new HashMap<>();
		Map<String, Object> noticePage = new HashMap<>();
	
		noticeTotalList.put("list", noticeService.getNoticePagingList(cri));
		noticePage.put("pageMake", pageMake);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.putAll(noticeTotalList);
		jsonObject.putAll(noticePage);
		
		log.info("[/notice/jsonlist] PARAM hashmap : "+ noticeTotalList);		
		

//		json.putAll(noticePage);
		
//		return noticeTotalList;
	
		return jsonObject;
	}
	
	@RequestMapping(value="/notice/callList", method=RequestMethod.GET)
	public String callList() {
		
		return "/notice/jsonlist";
	}

}
