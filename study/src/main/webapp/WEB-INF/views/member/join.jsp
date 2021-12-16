<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/member/join.css">
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
</head>
<body>
<div class="wrapper">
	<form id="join_form" method="post">
	<div class="wrap">
		<div class="subject">
			<span>회원가입</span>
		</div>
		<div class="id_wrap">
			<div class="id_name">아이디</div>
			<div class="id_input_box">
				<input class="id_input" name="memberID">
			</div>
			<span class="id_input_available_id">사용 가능한 아이디입니다.</span>
			<span class="id_input_unavailable_id">존재하는 아이디입니다.</span>
			<span class="final_id_ck">아이디를 입력해주세요.</span>
		</div>
		<div class="pw_wrap">
			<div class="pw_name">비밀번호</div>
			<div class="pw_input_box">
				<input class="pw_input" name="memberPW">
			</div>
			<span class="final_pw_ck">비밀번호를 입력해주세요.</span>
		</div>
		<div class="pwcheck_wrap">
			<div class="pwcheck_name">비밀번호 확인</div>
			<div class="pwcheck_input_box">
				<input class="pwcheck_input">
			</div>
			<span class="final_pwck_ck">비밀번호 확인을 입력해주세요.</span>
			<!--  <span class="pwCheck_input_correct">비밀번호가 일치합니다.</span>-->
            <span class="pwCheck_input_incorrect">비밀번호가 일치하지 않습니다.</span>
		</div>
		<div class="member_wrap">
			<div class="member_name">이름</div>
			<div class="member_input_box">
				<input class="member_input" name="memberName">
			</div>
			<span class="final_name_ck">이름을 입력해주세요.</span>
		</div>
		<div class="mail_wrap">
			<div class="mail_name">이메일</div>
			<div class="mail_input_box">
				<input class="mail_input" name="memberMail">
			</div>
			<span class="final_mail_ck">이메일을 입력해주세요.</span>
		</div>
		<!--  
		<div class="mail_check_wrap">
			<div class="mail_check_input_box" id="mail_check_input_box_false">
				<input class="mail_check_input" disabled="disabled">
			</div>
			<div class="mail_check_button">
				<span>인증번호 전송</span>
			</div>
			<div class="clearfix">
				<span id="mail_check_input_box_warnning"></span>
			</div>
		</div>
		-->
		<div class="join_button_wrap">
			<input type="button" class="join_button" value="가입하기">
		</div>
	</div>
	</form>
</div>

<script>

var code = ""; //이메일 전송 인증번호 저장을 위한 변수


var idCheck = false;			// 아이디
var idckCheck = false;			// 아이디 중복 검사
var pwCheck = false;			// 비번
var pwckCheck = false;			// 비번 확인
var pwckcorCheck = false;		// 비번 확인 일치 확인
var nameCheck = false;			// 이름
var mailCheck = false;			// 이메일
var mailnumCheck = false;		// 이메일 인증번호 확인


$(document).ready(function(){
	$(".join_button").click(function(){

		var id = $('.id_input').val(); 				// id 입력란
		var pw = $('.pw_input').val();				// 비밀번호 입력란
		var pwck = $('.pwcheck_input').val();			// 비밀번호 확인 입력란
		var name = $('.member_input').val();			// 이름 입력란
		var mail = $('.mail_input').val();			// 이메일 입력란
  	
		/* 아이디 유효성검사 */
		if(id == ""){
			$('.final_id_ck').css('display','block');
			idCheck = false;
		}else{
			$('.final_id_ck').css('display', 'none');
			idCheck = true;
		}
		
		/* 비밀번호 유효성 검사 */
		if(pw == ""){
			$('.final_pw_ck').css('display','block');
			pwCheck = false;
		}else{
			$('.final_pw_ck').css('display', 'none');
			pwCheck = true;
		}
		
		/* 비밀번호 확인 유효성 검사 */
		if(pwck == ""){
			$('.final_pwck_ck').css('display','block');
			pwckCheck = false;
		}else{
			$('.final_pwck_ck').css('display', 'none');
			pwckCheck = true;
		}
		
		/* 이름 유효성 검사 */
		if(name == ""){
			$('.final_name_ck').css('display','block');
			nameCheck = false;
		}else{
			$('.final_name_ck').css('display', 'none');
			nameCheck = true;
		}		
		
		/* 이메일 유효성 검사 */
		if(mail == ""){
			$('.final_mail_ck').css('display','block');
			mailCheck = false;
		}else{
			$('.final_mail_ck').css('display', 'none');
			mailCheck = true;
		}		
		
		/* 최종 유효성 검사 */
		if(idCheck&&idckCheck&&pwCheck&&pwckCheck&&pwckcorCheck&&nameCheck&&mailCheck){
			$("#join_form").attr("action", "/member/join");
			$("#join_form").submit();			
			
		}		
		
		return false;
	});
	
	/*
	$(".join_button").click(function(){
		//$("#join_form").attr("action", "/member/join");
		//$("#join_form").submit();
		
		var memberID = $('.id_input').val();                //ID 입력란
		var memberPW = $('.pw_input').val();				// 비밀번호 입력란
		var memberName = $('.member_input').val();			// 이름 입력란
		var memberMail = $('.mail_input').val();			// 이메일 입력란
		var memberPWCheck = $('.pwcheck_input').val();       //비밀번호 확인 입력란
		var mailCheck = $('.mail_check_input').val();       //메일 인증번호 
		
		$.ajax({
			type:"post",
			url:"/member/join",
			data:{"memberID" : memberID,
				"memberPW" : memberPW,
				"memberName" : memberName,
				"memberMail" : memberMail,
				"memberPWCheck" : memberPWCheck,
				"mailCheck" : mailCheck}
		});
	});
	*/
});

/*아이디 중복검사*/
 $(".id_input").on("porpertychange change keyup paste input", function(){
	
	 var memberID = $('.id_input').val(); //id input에 입력되는 값
	 var data = {memberID : memberID}  //controll에 넘길 데이터 이름
	 
	 $.ajax({
		type:"post",
		url:"/member/memberIdCheck",
		data:data,
		success : function(result){
			//console.log("성공여부:"+result);
			if(result != 'fail'){
				$(".id_input_available_id").css("display", "inline-block");
				$(".id_input_unavailable_id").css("display", "none");
				idckCheck = true;
			}
			else{
				$(".id_input_unavailable_id").css("display", "inline-block");
				$(".id_input_available_id").css("display", "none");
				idckCheck=false;
			}
		}
	 });
 });
 
 $(".mail_check_button").click(function(){
	 var email = $(".mail_input").val(); //입력한 이메일
	 var checkBox = $(".mail_check_input"); //인증번호 입력란
	 var boxWrap = $(".mail_check_iput_box"); //인증번호 입력란 박스
	 
	 $.ajax({
		 
			type : "POST",
			url : "mailCheck?email=" + email,
			success : function(data) {
				//console.log("data : "+data);
				checkBox.attr("disabled", false);
				boxWrap.attr("id", "mail_check_input_box_true");
				
				code = data;
			}
		});
	});
 
 /*인증번호 비교*/
 $(".mail_check_input").blur(function(){
	 
	 var inputCode = $(".mail_check_input").val();
	 var checkResult = $("#mail_check_input_box_warnning");
	 
	 if(inputCode == code){
		 checkResult.html("인증번호가 일치합니다.");
		 checkResult.attr("class", "correct");
		 mailnumCheck = true;
	 }
	 else{
		 checkResult.html("인증번호가 일치하지 않습니다.");
		 checkResult.attr("class", "incorrect");
		 mailnumCheck = false;
	 }
 });
 
 $(".pwcheck_input").on("porpertychange change keyup paste input", function(){
	
	 var pw = $('.pw_input').val();
	 var pwck = $('.pwcheck_input').val();
	 $('.final_pwck_ck').css('display', 'none');
	 
	 if(pw == pwck){
	        $('.pwCheck_input_correct').css('display','block');
	        $('.pwCheck_input_incorrect').css('display','none');
	        pwckcorCheck = true;
	    }
	 else{
	        $('.pwCheck_input_correct').css('display','none');
	        $('.pwCheck_input_incorrect').css('display','block');
	        pwckcorCheck = false;
	 }
 });
</script>

</body>
</html>