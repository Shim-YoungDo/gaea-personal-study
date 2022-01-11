<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/notice/list.css">
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>


</head>
<body>
	<div>
	<table id = "noticeList" border = "1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>등록날짜</th>
			<th>수정날짜</th>
		</tr>
	</table>
	</div>
	
	 
	<div class="pageInfo_area">
		<ul id="pageInfo" class="pageInfo">
		
		</ul>
		<form id="moveForm" method="get">
		</form>
		
	</div>
<script>
$(document).ready(function(){
	
	$.ajax({
		data:{pageNum:'${pageNum}',amount:'${amount}'},
		type:'post',
		url:'/notice/jsonlist',
		dataType:'json',
		async:false,
		success : function(data){
			console.log(data);
			console.log(data.list);
			
			html = '<tr>';
		    for (var i = 0; i < data.list.length; i++) {
		        html += '<tr><td>' + data.list[i].noticeNumber + 
		        '</td><td>'+'<a class="move" href="javascript:void(0)" data-list="'+ data.list[i].noticeNumber + '">' + data.list[i].noticeTitle+  
		        '</a></td><td>' + data.list[i].noticeWriter + '</td><<td>' + moment(data.list[i].noticeRegistrationDate).format('YYYY MM DD')
		                + '</td><td>'+ moment(data.list[i].noticeUpdateDate).format('YYYY MM DD') +'</td></tr>';
		        
		        console.log("noticeNumber:"+data.list[i].noticeNumber);
		    }
		    $("#noticeList").append(html);
		    html += '</tr>'; 
		    for(let j=data.pageMake.startPage; j<=data.pageMake.endPage; j++){
		    	$("#pageInfo").append('<li class="pageInfo_btn">' +
		    			'<a href="javascript:void(0)" data-page="'+j+'">' +  j + '</a> </li>');
		    	
		    	console.log("page:"+j);
		    	console.log("pageNum:"+data.pageMake.cri.pageNum);
		    }
		    
//		    let moveForm = $("#moveForm");
	
// 		    $(".move").on("click", function(e) {
// 				e.preventDefault();
// 				moveForm.append("<input type='hidden' name='noticeNumber' value='"
// 						+ $(this).attr("href") + "'>"); //<form>내부 noticeNumber값을 저장하는 <input>태그 생성
// 				moveForm.attr("action", "/notice/getPage");
// 				moveForm.attr("method", "post");
// 				moveForm.submit();
// 			});                                            
		}
		
		
	});
	
	
	let moveForm = $("#moveForm");
	   $(".move").on("click", function(e) {
			e.preventDefault();
			moveForm.append("<input type='hidden' name='noticeNumber' value='"
					+ $(this).data("list")+ "'>"); //<form>내부 noticeNumber값을 저장하는 <input>태그 생성
			moveForm.attr("action", "/notice/getPage");
			moveForm.attr("method", "post");
			moveForm.submit();
		});
	   
	   $(".pageInfo a").on("click", function(e){
	    	e.preventDefault();
	    	moveForm.append("<input type='hidden' name='pageNum' value='"
	    			+ $(this).data("page")+ "'>");
			moveForm.append("<input type='hidden' name='amount' value='10'>");
			moveForm.find("input[name='pageNum']").val($(this).data("page"));
			moveForm.attr("action", "/notice/totalList");
			moveForm.submit();
		});
	    
});


</script>

</body>
</html>