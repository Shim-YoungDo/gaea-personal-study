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
		type:'get',
		url:'/notice/jsonlist',
		dataType:'json',
		success : function(data){
			console.log(data);
			console.log(data.list);
			console.log(${data.list});
			
			html = '<tr>';
		    for (var i = 0; i < data.list.length; i++) {
		        html += '<tr><td>' + data.list[i].noticeNumber + 
		        '</td><td>'+"<a class=\"move\" href="+ data.list[i].noticeNumber + ">" + data.list[i].noticeTitle+  
		        '</a></td><td>' + data.list[i].noticeWriter + '</td><<td>' + moment(data.list[i].noticeRegistrationDate).format('YYYY MM DD')
		                + '</td><td>'+ moment(data.list[i].noticeUpdateDate).format('YYYY MM DD') +'</td></tr>';
		    }
		    $("#noticeList").append(html);
		    html += '</tr>'; 
		    for(let j=data.pageMake.startPage; j<=data.pageMake.endPage; j++){
		    	$("#pageInfo").append("<li class=\"pageInfo_btn\">" +
		    			"<a href="+ j +">"+  j + "</a> </li> ");
		    	
		    	console.log("page:"+j);
		    	console.log("pageNum:"+data.pageMake.cri.pageNum);
		    }
		    let moveForm = $("#moveForm");
		    $(".move").on("click", function(e) {
				e.preventDefault();
				moveForm.append("<input type='hidden' name='noticeNumber' value='"
						+ $(this).attr("href") + "'>"); //<form>내부 noticeNumber값을 저장하는 <input>태그 생성
				moveForm.attr("action", "/notice/getPage");
				moveForm.attr("method", "post");
				moveForm.submit();
			});
		    
		    $(".pageInfo a").on("click", function(e){
				e.preventDefault();
				moveForm.find("<input type='hidden' name='pageNum' value='"
						+ $(this).attr("href") + "'>"); //<form>내부 pageNum과 관련된 <input>태그의 value 속성값에 클릭한<a>태그 페이지 번호 삽입
				moveForm.find("<input type='hidden' name='amount' value='"
						+ data.pageMake.cri.amount + "'>");
				moveForm.attr("action", "/notice/callList");
				moveForm.submit();
			});
		    
		    /*
		    totalData = data.pageMake.boardTotal;
		    dataPerPage = data.pageMake.cri.amount;
		    console.log(dataPerPage);
		    globalCurrentPage = data.pageMake.cri.pageNum;
		    
		    paging(totalData, dataPerPage, pageCount, globalCurrentPage);    
		    
		    function paging(totalData, dataPerPage, pageCount, currentPage) {
		    	  console.log("currentPage : " + currentPage);
		    	  totalPage = data.pageMake.realEnd; //총 페이지 수
		    	  
		    	  if(totalPage<pageCount){
		    	    pageCount=totalPage;
		    	  }
		    	  
		    	 
		    	  let last = data.pageMake.endPage; //화면에 보여질 마지막 페이지 번호
		    	  
		    	  if (last > totalPage) {
		    	    last = totalPage;
		    	  }

		    	  let first = data.pageMake.startPage; //화면에 보여질 첫번째 페이지 번호
		    	  let next = last + 1;
		    	  let prev = first - 1;

		    	  let pageHtml = "";

		    	  if (prev > 0) {
		    	    pageHtml += "<li><a href='#' id='prev'> 이전 </a></li>";
		    	  }

		    	 //페이징 번호 표시 
		    	  for (var i = first; i <= last; i++) {
		    	    if (currentPage == i) {
		    	      pageHtml +=
		    	        "<li class='on'><a href='#' id='" + i + "'>" + i + "</a></li>";
		    	    } else {
		    	      pageHtml += "<li><a href='#' id='" + i + "'>" + i + "</a></li>";
		    	    }
		    	  }

		    	  if (last < totalPage) {
		    	    pageHtml += "<li><a href='#' id='next'> 다음 </a></li>";
		    	  }

		    	  $("#pagingul").html(pageHtml);
		    	  let displayCount = "";
		    	  displayCount = "현재 1 - " + totalPage + " 페이지 / " + totalData + "건";
		    	  $("#displayCount").text(displayCount);


		    	  //페이징 번호 클릭 이벤트 
		    	  $("#pagingul li a").click(function () {
		    	    let $id = $(this).attr("id");
		    	    selectedPage = $(this).text();

		    	    if ($id == "next") selectedPage = next;
		    	    if ($id == "prev") selectedPage = prev;
		    	    
		    	    //전역변수에 선택한 페이지 번호를 담는다...
		    	    globalCurrentPage = selectedPage;
		    	    //페이징 표시 재호출
		    	    paging(totalData, dataPerPage, pageCount, selectedPage);
		    	  });
		    }*/
		                                               
		}
		
	});
	
	$(".pageInfo a").on("click", function(e){
		e.preventDefault();
		moveForm.find("input[name='pageNum']").val($(this).attr("href")); //<form>내부 pageNum과 관련된 <input>태그의 value 속성값에 클릭한<a>태그 페이지 번호 삽입
		moveForm.attr("action", "/notice/callList");
		moveForm.submit();
	});
});
</script>

</body>
</html>