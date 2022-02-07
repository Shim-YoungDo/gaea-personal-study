<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.js">
</script>
</head>
<body>
<form name="form" id="form" method="post">
<input type="text" name="currentPage" value="1"/>
<input type="text" name="countPerPage" value="10"/>
<input type="hidden" name="resultType" value="json"/>
<input type="hidden" name="confmKey" value="devU01TX0FVVEgyMDIyMDEyODA5NDA1OTExMjE5MTU="/>
<input type="text" name="keyword" value=""onKeydown="enterSearch();"/>
<input type="button" onClick="getAddr();"value="주소검색하기"/>
<div id="list">
</div>
<div class="paginate" id="pageApi"></div>
</form>
</body>
<script>
function goPage(pageNum){
	document.form.currentPage.value=pageNum;
	getAddr();
}

function getAddr(){
	if(!checkSearchedWord(document.form.keyword)){
		return;
	}
	
	$.ajax({
		url:"https://www.juso.go.kr/addrlink/addrLinkApiJsonp.do",
		type:"post",
		data:$("#form").serialize(),
		dataType:"jsonp",
		crossDomain:true,
		success:function(data){
			$("#list").html("");
			var errCode=data.results.common.errorCode;
			var errDesc=data.results.common.errorMessage;
			if(errCode!="0"){
				alert(errCode+"="+errDesc);
			}
			else{
				if(data!=null){
					makeListJson(data);
					makePage(data);
				}
			}
		}
		,error:function(xhr,status,error){
			alert("에러!");
		}
	});
}

function makeListJson(data){
	
	var htmlStr = "";
	 htmlStr += "<table>";
	 
	 $(data.results.juso).each(function(){
		 htmlStr+="<tr>",
		 htmlStr+="<td>"+this.roadAddr+"</td>",
		 htmlStr+="<td>"+this.roadAddrPart1+"</td>",
		 htmlStr+="<td>"+this.roadAddrPart2+"</td>"
		 htmlStr+="<td>"+this.jibunAddr+"</td>"
		 htmlStr+="<td>"+this.zipNo+"</td>"
	 });
	 htmlStr += "</table>";
	 $("#list").html(htmlStr);
}
function checkSearchedWord(obj){
	if(obj.value.length>0){
		var expText = /[%=><]/;
		if(expText.test(obj.value)==true){
			alert("특수문자 입력 불가!");
			obj.value=obj.value.split(expText).join("");
			return false;
		}
		
		var sqlArray = new Array(
				"OR", "SELECT", "INSERT", "DELETE", "UPDATE", "CREATE",
				"DROP", "EXEC", "UNION", "FETCH", "DECLARE", "TRUNCATE");
		var regex;
		for(var i=0;i<sqlArray.length; i++){
			regex = new RegExp(sqlArray[i], "gi");
			
			if(regex.test(obj.value)){
				alert("\""+sqlArray[i]+"\"와 값은 특정문자로 검색할 수 없습니다.");
				obj.value=obj.value.replace(regex,"");
				return false;
			}
		}
	}
	return true;
}

function makePage(data){
	var total = data.results.common.totalCount;
	var pageNum = data.results.common.currentPage;
	var pagingStr="";
	if(total<1){
	}
	else{
		var pageCount=document.form.countPerPage.value;
		var endPage=Math.ceil((pageNum)/pageCount)*pageCount;
		var startPage=endPage-9;
		var realEnd=Math.ceil(total*1.0/pageCount);
		if(realEnd<endPage){
			endPage=realEnd;
		}
		
		var nextPage=endPage+1;
		var prePage=startPage-1;
		if(startPage>pageCount){
			pagingStr+="<a href='javascript:goPage("+prePage+");'>◁</a>";
		}
		for(i=startPage; i<=endPage; i++){
			if(pageNum==i){
				pagingStr +="<a style='font-weight:bold;color:blue;font-size:15px;'href='javascript:goPage("+i+");'>"+i+"</a>";
			}
			else{
				pagingStr+="<a href='javascript:goPage("+i+");'>"+ i + "</a>";
			}
		}
		if(endPage<total){
			pagingStr+="<a href='javascript:goPage("+nextPage+");'>▷</a>";
		}
		$("#pageApi").html(pagingStr);
	}
}

function enterSearch(){
	var evt_code = (window.netscape)?ev.which:event.keyCode;
	if(evt_code == 13){
		event.keyCode =0;
		getAddr();
	}
}
</script>
</html>