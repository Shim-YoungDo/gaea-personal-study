package com.study.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.vo.BoardVO;
import com.study.vo.Criteria;

/*게시글 CRUD 매퍼*/
@Mapper
public interface BoardMapper {
	
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
