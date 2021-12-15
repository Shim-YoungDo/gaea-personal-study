package com.study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.mapper.BoardMapper;
import com.study.vo.BoardVO;
import com.study.vo.Criteria;

/*게시글 CRUD 서비스 구현체*/
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper mapper;
	
	@Override
	public void boardRegister(BoardVO board) {
		mapper.boardRegister(board);
	}
	
//	@Override
//	public List<BoardVO> getList(){
//		return mapper.getList();
//	}
	
	@Override
	public BoardVO boardInquire(int boardNumber) {
		return mapper.boardInquire(boardNumber);
	}
	
	@Override
	public void boardModify(BoardVO board) {
		mapper.boardModify(board);
	}
	
	@Override
	public void boardDelete(int boardNumber) {
		mapper.boardDelete(boardNumber);
	}
	
	@Override
	public List<BoardVO> getBoardPagingList(Criteria cri){
		return mapper.getBoardPagingList(cri);
	}
	
	@Override
	public int getBoardTotal(Criteria cri) {
		return mapper.getBoardTotal(cri);
	}
}
