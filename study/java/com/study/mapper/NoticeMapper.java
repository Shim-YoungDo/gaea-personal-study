package com.study.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.vo.CafeNoticeVO;
import com.study.vo.Criteria;

/**
 * 게시글 조회, 등록, 수정, 삭제 등의 처리요청을 쿼리문으로 넘겨주는
 * Mapper 인터페이스입니다.
 * 
 * 
 * @author airpo
 * @version 1.2
 *
 */
@Mapper
public interface NoticeMapper {
	
	/**
	 * 게시글 등록을 수행하는 mapper method
	 * @param notice 공지게시판 관련 작업 처리 시 필요한 VO
	 */
	public void noticeRegister(CafeNoticeVO notice);
	
	/**
	 * 게시글 조회를 수행하는 mapper method
	 * @param noticeNumber 게시글 고유  식별 번호 [PK]
	 */
	public CafeNoticeVO noticeInquire(int noticeNumber);
	
	/**
	 * 게시글 수정을 수행하는 mapper method
	 * @param notice 공지게시판 관련 작업 처리 시 필요한 VO
	 */
	public void noticeModify(CafeNoticeVO notice);
	
	/**
	 * 게시글 삭제를 수행하는 mapper method
	 * @param noticeNumber 게시글 고유  식별 번호 [PK]
	 */
	public void noticeDelete(int noticeNumber);
	
	/**
	 * 페이징 적용된 게시글 목록을 수행하는 mapper method
	 * @param cri 현재 페이지와 페이지 당 게시글 수 정보를 담고있음
	 */
	public List<CafeNoticeVO> getNoticePagingList(Criteria cri);
	
	/**
	 * 게시물 총 개수를 가져오는 mapper method
	 * @param cri 현재 페이지와 페이지 당 게시글 수 정보를 담고있음
	 */
	public int getNoticeTotal(Criteria cri);
}