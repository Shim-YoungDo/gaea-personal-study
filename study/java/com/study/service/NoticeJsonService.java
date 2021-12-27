package com.study.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.study.vo.CafeNoticeVO;
import com.study.vo.Criteria;

public interface NoticeJsonService {
	
	/**
	 * 페이징 적용된 게시글 목록을 가져오는 method
	 * @param cri 현재 페이지와 페이지 당 게시글 수 정보를 담고있음
	 */
	public List<CafeNoticeVO> getNoticePagingList(Criteria cri);
	
	/**
	 * 게시글 총 개수를 가져오는 method
	 * @param cri 현재 페이지와 페이지 당 게시글 수 정보를 담고있음
	 */
	public int getNoticeTotal(Criteria cri);
	
	/**
	 * 전체 게시글과 페이지 정보를 json데이터로 전송하는 method
	 * 
	 * @param cri 현재 페이지와 페이지 당 게시글 수 정보를 담고있음
	 */
	public JSONObject jsonList(Criteria cri);

}
