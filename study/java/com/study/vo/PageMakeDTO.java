package com.study.vo;

/*페이지 번호 생성 위한 DTO*/
public class PageMakeDTO {
	
	//시작페이지
	private int startPage;
	
	//끝페이지
	private int endPage;
	
	//이전페이지, 다음페이지 존재 유뮤
	private boolean prev, next;
	
	//전체 게시물 수
	private int boardTotal;

	//현재 페이지, 페이지 당 게시글 수->pageNum
	private Criteria cri;
	
	//전체(실제) 마지막 페이지
	private int realEnd;
	
	//생성자
	public PageMakeDTO(Criteria cri, int boardTotal) {
		this.cri = cri;
		this.boardTotal = boardTotal;
		
		//마지막 페이지
		this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0))*10;
		
		//시작 페이지
		this.startPage = this.endPage-9;
		
		//전체 마지막 페이지
		this.realEnd = (int)(Math.ceil(boardTotal*1.0/cri.getAmount()));
		
		//화면에 보일 마지막페이지 유효체크
		if(realEnd<this.endPage) {
			this.endPage = realEnd;
		}
		
		//시작 페이지 값이 1보다 큰 경우 true
		this.prev = this.startPage>1;
		
		//전체 페이지 마지막 번호(realEnd)보다 마지막 페이지(endPage) 값이 작은 경우 true
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
