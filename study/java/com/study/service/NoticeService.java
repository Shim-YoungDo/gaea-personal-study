package com.study.service;

import java.util.List;
import java.util.Map;

import com.study.vo.CafeNoticeVO;
import com.study.vo.Criteria;

/**
 * 공지게시판의 게시물 등록, 조회, 수정, 삭제 등과 관련된 비즈니스 로직을 처리하는
 * 인터페이스입니다.
 * 
 * @author ydshim
 *
 */
public interface NoticeService {
	
	/**
	 * 게시글 등록을 처리하는 method
	 * @param notice 공지게시판 관련 작업 처리 시 필요한 VO
	 */
	public void noticeRegister(CafeNoticeVO notice);
	
	/**
	 * 게시글 조회를 처리하는 method
	 * @param noticeNumber  게시글 고유  식별 번호 [PK]
	 */
	public CafeNoticeVO noticeInquired(int noticeNumber);
	
	/**
	 * 게시글 수정을 처리하는 method
	 * @param notice 공지게시판 관련 작업 처리 시 필요한 VO
	 */
	public void noticeModify(CafeNoticeVO notice);
	
	/**
	 * 게시글 삭제를 처리하는 method
	 * @param noticeNumber 게시글 고유  식별 번호 [PK]
	 */
	public void noticeDelete(int noticeNumber);
	
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
	
	public List<CafeNoticeVO> noticeRegistDate();

}