<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/notice/registerView.css">
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
</head>
<body>

<form id="enroll_form" action="/notice/register" method="post">
<c:if test="${member != null}">
	<c:if test="${memberHumanResult != 0}">
		<div class="input_wrap">
			<label>Title</label>
			<input class="noticeTitle" name="noticeTitle">
			<span class="title_check">제목을 입력해주세요.</span>
		</div>
		<div class="input_wrap">
			<label>Content</label>
			<textarea rows="3" class="noticeContent" name="noticeContent"></textarea>
			<span class="content_check">내용을 입력해주세요.</span>
		</div>
		<div class="input_wrap">
			<label>Writer</label>
			<input class="writer_input" name="noticeWriter" readonly="readonly" value="${member}">
		</div>
		<button class="enroll_button">등록</button>
	</c:if>
	<!-- <button class="cancel_button">취소</button>-->
	<c:if test="${memberHumanResult == 0 }">
		<p>휴면 상태를 해제해주세요.</p>
	</c:if>
	</c:if>
</form>
<c:if test="${member == null}">
	<p>로그인 후에 작성해주세요.</p>
</c:if>

<script>

var titleCheck = false;
var contentCheck = false;

$(document).ready(function(){
	$(".enroll_button").click(function(){
		var title = $('.noticeTitle').val();
		var content = $('.noticeContent').val();
		
		if(title == ""){
			//$('.final_id_ck').css('display','block');
			titleCheck = false;
		}else{
			//$('.final_id_ck').css('display', 'none');
			titleCheck = true;
		}
		
		if(content == ""){
			//$('.final_id_ck').css('display','block');
			contentCheck = false;
		}else{
			//$('.final_id_ck').css('display', 'none');
			contentCheck = true;
		}
		
		if(titleCheck&&contentCheck){
			$("#enroll_form").submit();		
			
		}
		else{
			alert("잘못된 형식입니다.");
		}
		
		return false;
	});
});
</script>

</body>
</html>