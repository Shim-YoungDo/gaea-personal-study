package com.study.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.study.service.BoardService;
import com.study.vo.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardMapperTest {
 
     private static final Logger log = LoggerFactory.getLogger(BoardMapperTest.class);
     
     @Autowired
     private BoardMapper mapper;
     
     @Autowired
     private BoardService service;
 
     /*
     //게시글 등록 테스트
     @Test
     public void testEnroll() {
         
         BoardVO vo = new BoardVO();
         
         vo.setTitle("test");
         vo.setContent("test");
         vo.setWriter("tester");
         
         mapper.enroll(vo);
         
     }
     */
     
     /*
     //게시글 목록 테스트
     @Test
     public void testGetList() {
    	 List list = mapper.getList();
    	 
    	 for(int i=0; i<list.size(); i++) {
    		 log.info(""+list.get(i));
    	 }
     }
     */
     
     /*
     //게시글 조회
     @Test
     public void testGetPage() {
    	 int bno=2;
    	 log.info("" +service.getPage(bno));
     }
     */
     
     /*
     //게시글 수정 테스트
     @Test
     public void testModify() {
    	 BoardVO board = new BoardVO();
    	 board.setBno(6);
    	 board.setTitle("수정 제목123");
    	 board.setContent("수정 내용123");
    	 
    	 int result = service.modify(board);
    	 log.info("결과:" +result);
     }
     */
     
     /*
     //게시글 삭제 테스트
     @Test
     public void testDelete() {
    	 int result = service.delete(5);
    	 
    	 log.info("result: " +result);
    	 
     }
     */
     
     //페이징 테스트
     @Test
     public void testPaging() {
    	 Criteria cri = new Criteria();
    	 List list = service.getBoardPagingList(cri);
    	 list.forEach(board->log.info("" +board));
     }
     
     
     
 
}
