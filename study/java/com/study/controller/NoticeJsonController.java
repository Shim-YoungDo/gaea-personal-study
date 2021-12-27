package com.study.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.study.service.NoticeJsonService;
import com.study.vo.Criteria;
import com.study.vo.PageMakeDTO;

@Controller
@RequestMapping("/")
public class NoticeJsonController {
	
	private static final Logger log = LoggerFactory.getLogger(NoticeJsonController.class);
	
	@Autowired
	private NoticeJsonService service;
	
	@RequestMapping(value = "/notice/jsonlist", method=RequestMethod.GET)
	public JSONObject jsonList(Criteria cri) {
//		CafeNoticeVO notice = new CafeNoticeVO();
	
		/**
		 * 총 게시글 갯수
		 */
//		int noticeTotal = service.getNoticeTotal(cri);
//		
//		/**
//		 * 현재 페이지와 한 페이지 당 게시글 수, 총 게시글 갯수 정보를
//		 * 페이지 정보를 표시하기 위한 VO에 저장
//		 */
//		PageMakeDTO pageMake = new PageMakeDTO(cri, noticeTotal);
//	
//		Map<String, List> listMap = new HashMap<String, List>();
//		Map<String, PageMakeDTO> pageMap = new HashMap<String, PageMakeDTO>();
//	
//		listMap.put("list", service.getNoticePagingList(cri));
//		pageMap.put("pageMake", pageMake);
//		
//		log.info("[/notice/jsonlist] PARAM hashmap : "+ listMap);		
//		
//		JSONObject json = new JSONObject();
//		json.putAll(listMap);
//		json.putAll(pageMap);
//		log.info("[/notice/jsonlist] PARAM json : "+ json);
//		return json;
		
		
		return service.jsonList(cri);
	}

}
