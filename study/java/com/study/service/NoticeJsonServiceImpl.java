package com.study.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.study.mapper.NoticeMapper;
import com.study.vo.CafeNoticeVO;
import com.study.vo.Criteria;
import com.study.vo.PageMakeDTO;

@Service
public class NoticeJsonServiceImpl implements NoticeJsonService {
	
	private static final Logger log = LoggerFactory.getLogger(NoticeJsonServiceImpl.class);
	
	@Autowired
	private NoticeMapper mapper;
	
	/**
	 * 페이징 적용된 게시글 목록을 가져오는 구현체 method
	 * 
	 * @param cri 현재 페이지와 페이지 당 게시글 수 정보를 담고있음
	 * @return 현재 페이지와 페이지 당 게시글 수 정보를 담아 게시글 리스트를 가져오는 쿼리와 매핑
	 */
	@Override
	public List<CafeNoticeVO> getNoticePagingList(Criteria cri) {
		return mapper.getNoticePagingList(cri);
	}
	
	/**
	 * 전체 게시글과 페이지 정보를 json데이터로 전송하는 method
	 * 
	 * @param cri 현재 페이지와 페이지 당 게시글 수 정보를 담고있음
	 * @return json데이터를 jsonlist view에 반환
	 */
	@Override
	public JSONObject noticeListJson(Criteria cri) {
		int noticeTotal = getNoticeTotal(cri);
		PageMakeDTO pageMake = new PageMakeDTO(cri, noticeTotal);
		
		HashMap<String, Object> noticeTotalList = new HashMap<>();
		HashMap<String, Object> noticePage = new HashMap<>();

		noticeTotalList.put("list", getNoticePagingList(cri));
		noticePage.put("pageMake", pageMake);
		
		//log.info("[/notice/jsonlist] PARAM list map : "+ listMap);
		//log.info("[/notice/jsonlist] PARAM page map : "+ pageMap);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.putAll(noticeTotalList);
		jsonObject.putAll(noticePage);
		
		log.info("[/notice/jsonlist] PARAM list to json : "+ jsonObject.toString());
		
		return jsonObject;
	
	}

	/**
	 * 게시글 총 개수를 가져오는 구현체 method
	 * 
	 * @param cri 현재 페이지와 페이지 당 게시글 수 정보를 담고있음
	 * @return 현재 페이지와 페이지 당 게시글 수 정보를 담아 게시글 총 개수를 가져오는 쿼리와 매핑
	 */
	@Override
	public int getNoticeTotal(Criteria cri) {
		return mapper.getNoticeTotal(cri);
	}
}
