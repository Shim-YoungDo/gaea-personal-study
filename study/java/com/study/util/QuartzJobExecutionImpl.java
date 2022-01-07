package com.study.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.service.NoticeService;
import com.study.vo.CafeNoticeVO;

/**
 * 스케쥴링을 실행할 구현체
 * 
 * @author airpo
 *
 */
@Service("noticeQuartzJob")
public class QuartzJobExecutionImpl implements QuartzJobExecution {

	private static final Logger logger = LoggerFactory.getLogger(QuartzJobExecutionImpl.class);

	@Autowired
	private NoticeService service;
	
	/**
	 * 등록한지 하루가 지난 게시글을 매일 2시에 삭제하는 job method
	 */
	@Override
	public void noticeScheduleDelete() {
	
		List<CafeNoticeVO> regDate = service.noticeRegistDate();
		
		for(int i=0; i<regDate.size(); i++) {
			int noticeNumber = regDate.get(i).getNoticeNumber();
			logger.info("delete noticeNumber:"+noticeNumber);
			service.noticeDelete(noticeNumber);
		}
		logger.info("job end");
	}

}
