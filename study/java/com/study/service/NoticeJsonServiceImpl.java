package com.study.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	 * return json데이터를 리턴
	 */
	@Override
	public JSONObject jsonList(Criteria cri) {
		int noticeTotal = getNoticeTotal(cri);
		PageMakeDTO pageMake = new PageMakeDTO(cri, noticeTotal);
		
		Map<String, List> listMap = new HashMap<String, List>();
		Map<String, PageMakeDTO> pageMap = new HashMap<String, PageMakeDTO>();
		
		listMap.put("list", getNoticePagingList(cri));
		pageMap.put("pageMake", pageMake);
		
		JSONObject json = new JSONObject();
		json.putAll(listMap);
		json.putAll(pageMap);
		
		log.info("[/notice/jsonlist] PARAM list to json : "+ json);
		
		return json;
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
