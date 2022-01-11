package com.study.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.service.MemberService;
import com.study.service.NoticeService;
import com.study.vo.CafeNoticeVO;
import com.study.vo.MemberVO;

/**
 * 스케쥴링을 실행할 구현체
 * 
 * @author ydshim
 *
 */
@Service("quartzJob")
public class QuartzJobExecutionImpl implements QuartzJobExecution {

	private static final Logger logger = LoggerFactory.getLogger(QuartzJobExecutionImpl.class);

	@Autowired
	private NoticeService service;
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * 등록한지 하루가 지난 게시글을 매일 2시에 삭제하는 job method
	 */
	@Override
	public void noticeScheduleDelete() {
	
		List<CafeNoticeVO> registDate = service.noticeRegistDate();
		
		for(int i=0; i<registDate.size(); i++) {
			int noticeNumber = registDate.get(i).getNoticeNumber();
			logger.info("delete noticeNumber:"+noticeNumber);
			service.noticeDelete(noticeNumber);
		}
		logger.info("notice job end");
	}
	
	/**
	 * 마지막 로그인시간이 하루가 지났거나 접속한 이력이 없는
	 * 계정을 휴면계정으로 전환하는 job method
	 */
	@Override
	public void MemberConvertDormancy() {
		List<MemberVO> loginDate = memberService.memberLoginDate();
		logger.info("loginDate VO :"+loginDate);
		for(int i=0; i<loginDate.size(); i++) {
			String memberId = loginDate.get(i).getMemberID();
			logger.info(i+":" +"memberId:"+memberId);
			memberService.memberConvertDormancy(memberId);
		}
		logger.info("member job end");
	}
	
}
