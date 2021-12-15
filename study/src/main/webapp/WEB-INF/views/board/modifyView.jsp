<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/board/modifyView.css">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
</head>
<body>
	<form id="modifyForm" action="/board/modify" method="post">
	<div class="input_wrap">
		<label>게시판 번호</label>
		<input name="boardNumber" readonly="readonly" value='<c:out value="${pageInfo.boardNumber}"/>' >
	</div>
	<div class="input_wrap">
		<label>게시판 제목</label>
		<input name="boardTitle" value='<c:out value="${pageInfo.boardTitle}"/>' >
	</div>
	<div class="input_wrap">
		<label>게시판 내용</label>
		<textarea rows="3" name="boardContent"><c:out value="${pageInfo.boardContent}"/></textarea>
	</div>
	<div class="input_wrap">
		<label>게시판 작성자</label>
		<input name="boardWriter" readonly="readonly" value='<c:out value="${pageInfo.boardWriter}"/>' >
	</div>
	<div class="input_wrap">
		<label>게시판 등록일</label>
		<input name="boardRegistrationDate" readonly="readonly" value='<fmt:formatDate pattern="yyyy/MM/dd" value="${pageInfo.boardRegistrationDate}"/>' >
	</div>
	<div class="input_wrap">
		<label>게시판 수정일</label>
		<input name="boardUpdateDate" readonly="readonly" value='<fmt:formatDate pattern="yyyy/MM/dd" value="${pageInfo.boardUpdateDate}"/>' >
	</div>		
	<div class="btn_wrap">
		<a class="btn" id="list_btn">목록</a>
		<c:if test="${member == pageInfo.boardWriter}"> 
			<a class="btn" id="modify_btn">수정 완료</a>
			<a class="btn" id="delete_btn">삭제</a>
			<a class="btn" id="cancel_btn">수정 취소</a>
		</c:if>
	</div>
	</form>
	<form id="infoForm" action="/board/modifyView" method="get">
		<input type="hidden" id="boardNumber" name="boardNumber" value='<c:out value="${pageInfo.boardNumber}"/>'>
		<input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum}"/>'>
		<input type="hidden" name="amount" value='<c:out value="${cri.amount}"/>'>
		<input type="hidden" name="type" value="${cri.type}">
		<input type="hidden" name="keyword" value="${cri.keyword }"> 
	</form>
<script>
	let form = $("#infoForm"); //페이지 이동 form(리스트페이지, 조회페이지 이동)
	let mForm = $("#modifyForm"); //페이지 데이터 수정form
	
	/*목록 페이지 이동 버튼*/
	$("#list_btn").on("click", function(e){
		form.find("#boardNumber").remove();
		form.attr("action", "/board/list");
		form.submit();
	});
	
	/*수정하기 버튼*/
	$("#modify_btn").on("click", function(e){
		mForm.submit();
	});
	
	/* 취소 버튼 */
    $("#cancel_btn").on("click", function(e){
        form.attr("action", "/board/getPage");
        form.submit();
    });   
	
	/*삭제 버튼*/
	$("#delete_btn").on("click", function(e){
		form.attr("action", "/board/delete");
		form.attr("method", "post");
		form.submit();
	});
</script>	
</body>
</html>