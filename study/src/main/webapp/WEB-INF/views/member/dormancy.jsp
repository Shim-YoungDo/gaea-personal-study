<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
</head>
<body>
	<form id="dormancy_form" method="POST">
		<div class="form-group has-feedback">
			<label class="control-label" for="memberId">아이디</label> <input
				class="form-control" type="text" id="id_input" name="memberID"
				value="${member}" readonly="readonly"/>
		</div>

		<div class="form-group has-feedback">
			<label class="control-label" for="memberPw">패스워드</label> <input
				class="form-control" type="text" id="pw_input" name="memberPW"/>
		</div>

		<div class="form-group has-feedback">
			<label class="control-label" for="memberName">성명</label> <input
				class="form-control" type="text" id="name_input" name="memberName"/>
		</div>
		<div class="form-group has-feedback">
			<button class="dormancy_off_button" type="submit" id="submit">휴면해제</button>
		</div>
	</form>
	<div>
		<c:if test="${result == 0}">
			<div class="login_warn">사용자 정보를 잘못 입력하셨습니다.</div>
		</c:if>
	</div>
	
<script>
$(".dormancy_off_button").click(function(){
	//alert("로그인 버튼 정상 작동");
	
	/*로그인 메서드 서버 요청*/
	$("#dormancy_form").attr("action", "/member/dormancy");
	$("#dormancy_form").submit();
});
</script>

</body>
</html>