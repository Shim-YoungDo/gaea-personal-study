<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/notice/getPage.css">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
</head>
<body>
	<div class="input_wrap">
		<label>게시글 번호</label>
		<input name="noticeNumber" readonly="readonly" value='<c:out value="${pageInfo.noticeNumber}"/>' >
	</div>
	<div class="input_wrap">
		<label>제목</label>
		<input name="noticeTitle" readonly="readonly" value='<c:out value="${pageInfo.noticeTitle}"/>' >
	</div>
	<div class="input_wrap">
		<label>내용</label>
		<textarea rows="3" name="noticeContent" readonly="readonly"><c:out value="${pageInfo.noticeContent}"/></textarea>
	</div>
	<div class="input_wrap">
		<label>작성자</label>
		<input name="noticeWriter" readonly="readonly" value='<c:out value="${pageInfo.noticeWriter}"/>' >
	</div>
	<div class="input_wrap">
		<label>등록일</label>
		<input name="noticeRegistrationDate" readonly="readonly" value='<fmt:formatDate pattern="yyyy/MM/dd" value="${pageInfo.noticeRegistrationDate}"/>' >
	</div>
	<div class="input_wrap">
		<label>수정일</label>
		<input name="noticeUpdateDate" readonly="readonly" value='<fmt:formatDate pattern="yyyy/MM/dd" value="${pageInfo.noticeUpdateDate}"/>' >
	</div>	
	
	<div class="button_wrap">
		<a class="button" id="list_button">목록 페이지</a>
		<c:if test="${member == pageInfo.noticeWriter}"> 
		<a class="button" id="modify_button">수정 하기</a>
		</c:if>
	</div>
	
	<form id="infoForm" action="/notice/modifyView" method="get">
		<input type="hidden" id="noticeNumber" name="noticeNumber" value='<c:out value="${pageInfo.noticeNumber}"/>'>
		<input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum}"/>'>
		<input type="hidden" name="amount" value='<c:out value="${cri.amount}"/>'>
		<input type="hidden" name="type" value="${cri.type}">
		<input type="hidden" name="keyword" value="${cri.keyword }"> 
	</form>
	
<script>
	let form = $("#infoForm");
	
	
	
	$("#list_button").on("click", function(e){
		form.find("#noticeNumber").remove();
		form.attr("action", "/notice/list");
		form.submit();
	});
	
	$("#modify_button").on("click", function(e){
		
		form.attr("action", "/notice/modifyView");
		form.attr("method", "post");
		form.submit();
	});	
</script>	
	

</body>
</html>