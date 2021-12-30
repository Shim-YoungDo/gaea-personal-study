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
000000
<script>
$(document).ready(function(){
	$.ajax({
		type:'get',
		url:'/notice/jsonlist',
		dataType:'json',
		success : function(data){
			console.log(data);
			console.log(data.list);
			console.log(data.pageMake);
			 html = '<tr>';
		        for (var i = 0; i < data.list.length; i++) {
		            html += '<tr><td>' + data.list[i].noticeNumber + '</td><td>' + data.list[i].noticeTitle
		                    + '</td><td>' + data.list[i].noticeWriter + '</td><<td>' + moment(data.list[i].noticeRegistrationDate).format('YYYY MM DD')
		                    + '</td><td>'+ moment(data.list[i].noticeUpdateDate).format('YYYY MM DD') +'</td></tr>';
		        }
		        $("#noticeList").append(html);
		        html += '</tr>'; 
		}
	});
});
</script>

</body>
</html>