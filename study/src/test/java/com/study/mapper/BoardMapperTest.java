package com.study.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.study.service.NoticeService;
import com.study.vo.CafeNoticeVO;
import com.study.vo.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardMapperTest {
 
     private static final Logger log = LoggerFactory.getLogger(BoardMapperTest.class);
     
     @Autowired
     private NoticeMapper mapper;
     
     @Autowired
     private NoticeService service;
 
     /*
     //게시글 등록 테스트
     @Test
     public void testEnroll() {
         
         CafeNoticeVO vo = new CafeNoticeVO();
         
         vo.setNoticeTitle("efef");
         vo.setNoticeContent("ㅇㄹㅇ");
         vo.setNoticeWriter("ㄱㄱㄱ");
         
         mapper.noticeRegister(vo);
         
     }*/
     
//     //게시글 조회
//     @Test
//     public void testGetPage() {
//    	 int bno=19;
//    	 log.info("" +service.noticeInquired(bno));
//     }
     
     
     /*
     //게시글 수정 테스트
     @Test
     public void testModify() {
    	 CafeNoticeVO notice = new CafeNoticeVO();
    	 notice.setNoticeNumber(19);
    	 notice.setNoticeTitle("수정 제목123");
    	 notice.setNoticeContent("수정 내용123");
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
     
     
//     //페이징 테스트
//     @Test
//     public void testPaging() {
//    	 Criteria cri = new Criteria();
//    	 cri.setType("TWC");
//		 String[] typeArr = cri.getTypeArr();
//		 
//		 for(int i = 0; i < typeArr.length;i++) {
//			 System.out.println("typeArr : " + typeArr[i]);
//		 }
//		 List list = mapper.getNoticePagingList(cri);
//		 
//		 list.forEach(board -> log.info("" + board));
//     }
     
     
//     
     
     
 
}