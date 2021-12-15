package com.study.service;

import java.util.List;

import com.study.vo.BoardVO;
import com.study.vo.Criteria;

/*게시글 CRUD 서비스 인터페이스*/
public interface BoardService {
	
	//게시글 등록
	public void boardRegister(BoardVO board);
	
	//게시글 목록
//	public List<BoardVO> getList();
	
	//게시글 조회
	public BoardVO boardInquire(int boardNumber);
	
	//게시글 수정
	public void boardModify(BoardVO board);
	
	//게시글 삭제
	public void boardDelete(int boardNumber);
	
	//게시글 목록(페이징 적용)
	public List<BoardVO> getBoardPagingList(Criteria cri);
	
	//게시물 총 개수
	public int getBoardTotal(Criteria cri);

}
