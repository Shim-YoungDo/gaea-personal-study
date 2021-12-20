package com.study.vo;

/**
 * 페이징 처리에서 페이지 번호를 표시하기 위한 정보를 담고있는 DTO클래스입니다.
 * 
 * 
 * @author airpo
 *
 */
public class PageMakeDTO {
	
	/**
	 * 시작페이지
	 */
	private int startPage;
	
	/**
	 * 끝페이지
	 */
	private int endPage;
	
	/**
	 * 이전페이지, 다음페이지 존재 유뮤
	 */
	private boolean prev, next;
	
	/**
	 * 전체 게시물 수
	 */
	private int boardTotal;

	/**
	 * 현재 페이지, 페이지 당 게시글 수 정보
	 */
	private Criteria cri;
	
	/**
	 * 전체(실제) 마지막 페이지
	 */
	private int realEnd;
	
	/**
	 * 생성자
	 * @param cri   현재 페이지와 페이지 당 게시글 수 정보를 담고있음
	 * @param boardTotal  전체 게시물 수
	 */
	public PageMakeDTO(Criteria cri, int boardTotal) {
		this.cri = cri;
		this.boardTotal = boardTotal;
		
		/**
		 * 마지막 페이지
		 * ex) 현재페이지 : 11
		 * ceil(11/10)*10 = 20
		 * 마지막 페이지 : 20 
		 */
		this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0))*10;
		
		/**
		 * 시작 페이지
		 * ex) 위 결과에서 20-9=11
		 * 시작페이지 : 11
		 */
		this.startPage = this.endPage-9;
		
		/**
		 * 전체 마지막 페이지
		 * ex) 전체 게시글 수 : 130개, 
		 * 한페이지 출력할 게시글 수(amount) : 10개
		 * 마지막 페이지 예상 기대값 : 13page 
		 * ceil((130*1.0)/10) = 13
		 * 실제 마지막 페이지 : 13 
		 */
		this.realEnd = (int)(Math.ceil(boardTotal*1.0/cri.getAmount()));
		
		/**
		 * 화면에 보일 마지막페이지 유효체크
		 * 마지막 페이지(endPage) : 20
		 * 실제 마지막 페이지(realEnd) : 13
		 * 13 < 20
		 * 화면에 출력될 실제 페이지 : 13
		 */
		if(realEnd<this.endPage) {
			this.endPage = realEnd;
		}
		
		/**
		 * 시작 페이지 값이 1보다 큰 경우 true
		 */
		this.prev = this.startPage>1;
		
		/**
		 * 실제 페이지 마지막 번호(realEnd)보다 
		 * 마지막 페이지(endPage) 값이 작은 경우 true
		 * ex) 화면에 보이는 마지막 페이지(endPage) : 20
		 * 실제 페이지(realEnd) : 27이면
		 * 다음 페이지 버튼 존재
		 */
		this.next = this.endPage<realEnd;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	

	public int getBoardTotal() {
		return boardTotal;
	}

	public void setBoardTotal(int boardTotal) {
		this.boardTotal = boardTotal;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public int getRealEnd() {
		return realEnd;
	}

	public void setRealEnd(int realEnd) {
		this.realEnd = realEnd;
	}
	
}
