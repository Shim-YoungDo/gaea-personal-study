package com.study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.mapper.NoticeMapper;
import com.study.vo.CafeNoticeVO;
import com.study.vo.Criteria;

/**
 * 공지게시판의 게시물 등록, 조회, 수정, 삭제 등과 관련된 비즈니스 로직을 처리하는
 * 구현체입니다.
 * @author airpo
 * @since 1.0
 */
@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper mapper;
	
	/**
	 * 게시글 등록을 처리하는 구현체 method
	 * @param notice 공지게시판 관련 작업 처리 시 필요한 VO
	 */
	@Override
	public void noticeRegister(CafeNoticeVO notice) {
		mapper.noticeRegister(notice);
	}
	
//	@Override
//	public List<BoardVO> getList(){
//		return mapper.getList();
//	}
	
	/**
	 * 게시글 조회를 처리하는 구현체 method
	 * @param noticeNumber  게시글 고유  식별 번호 [PK]
	 * @return 게시글 고유 식별 번호를 담아 조회처리하는 쿼리와 매핑
	 */
	@Override
	public CafeNoticeVO noticeInquired(int noticeNumber) {
		return mapper.noticeInquire(noticeNumber);
	}
	
	/**
	 * 게시글 수정을 처리하는 구현체 method
	 * @param notice 공지게시판 관련 작업 처리 시 필요한 VO
	 */
	@Override
	public void noticeModify(CafeNoticeVO notice) {
		mapper.noticeModify(notice);
	}
	
	/**
	 * 게시글 삭제를 처리하는 구현체 method
	 * @param noticeNumber 게시글 고유  식별 번호 [PK]
	 */
	@Override
	public void noticeDelete(int noticeNumber) {
		mapper.noticeDelete(noticeNumber);
	}
	
	/**
	 * 페이징 적용된 게시글 목록을 가져오는 구현체 method
	 * @param cri 현재 페이지와 페이지 당 게시글 수 정보를 담고있음
	 * @return 현재 페이지와 페이지 당 게시글 수 정보를 담아 게시글 리스트를 가져오는 쿼리와 매핑
	 */
	@Override
	public List<CafeNoticeVO> getNoticePagingList(Criteria cri){
		return mapper.getNoticePagingList(cri);
	}
	
	/**
	 * 게시글 총 개수를 가져오는 구현체 method
	 * @param cri 현재 페이지와 페이지 당 게시글 수 정보를 담고있음
	 * @return 현재 페이지와 페이지 당 게시글 수 정보를 담아 게시글 총 개수를 가져오는 쿼리와 매핑
	 */
	@Override
	public int getNoticeTotal(Criteria cri) {
		return mapper.getNoticeTotal(cri);
	}
}