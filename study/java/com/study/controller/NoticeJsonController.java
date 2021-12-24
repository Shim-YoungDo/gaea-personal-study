package com.study.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.study.service.NoticeService;
import com.study.vo.CafeNoticeVO;
import com.study.vo.Criteria;
import com.study.vo.PageMakeDTO;

@RestController
@RequestMapping("/")
public class NoticeJsonController {
	
	private static final Logger log = LoggerFactory.getLogger(NoticeJsonController.class);
	
	@Autowired
	private NoticeService service;
	
	@RequestMapping(value = "/notice/jsonlist", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView jsonTest(ModelAndView modelView, Criteria cri, HttpServletRequest request, HttpServletResponse response) throws IOException {
//		CafeNoticeVO notice = new CafeNoticeVO();
	
		/**
		 * 총 게시글 갯수
		 */
		int noticeTotal = service.getNoticeTotal(cri);
		
		/**
		 * 현재 페이지와 한 페이지 당 게시글 수, 총 게시글 갯수 정보를
		 * 페이지 정보를 표시하기 위한 VO에 저장
		 */
		PageMakeDTO pageMake = new PageMakeDTO(cri, noticeTotal);
		
		
		JSONObject json = new JSONObject();
		log.info("[/notice/jsonlist] PARAM json list before : "+ json);

		json.put("list", service.getNoticePagingList(cri));
		json.put("pageMake", pageMake);
		
		log.info("[/notice/jsonlist] PARAM json list : "+ json);
		
		modelView.setViewName("/notice/jsonlist");
		
		modelView.addAllObjects(json);
		
		return modelView;
	}

}
