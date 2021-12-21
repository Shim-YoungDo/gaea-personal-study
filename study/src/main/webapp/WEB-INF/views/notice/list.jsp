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
</head>
<body>
	<div class="table_wrap">
		<a href="javascript:void(0)" onClick="javascript:registeView()" class="enroll_btn">글쓰기</a>
		<a href="/notice/list" class="list_btn">목록</a>
		<div class="login_area">
		<!-- 로그인 x -->
		<c:if test="${member==null}">
		<div class="login_button"><a href="javascript:void(0)" onClick="javascript:loginView()">로그인</a></div>
		<span><a href="javascript:void(0)" onClick="javascript:joinView()">회원가입</a></span>
		</c:if>
		<!-- 로그인 o -->
		<c:if test="${member != null}">
		<div class="login_success_area">
			<span>아이디:${member}</span>
			<a href="javascript:void(0)" onClick="javascript:logout()">로그아웃</a>
		</div>
		</c:if>
		
		<table>
			<thead>
				<tr>
					<th class="bno_width">번호</th>
					<th class="title_width">제목</th>
					<th class="writer_width">작성자</th>
					<th class="regdate_width">작성일</th>
					<th class="updatedate_width">수정일</th>
				</tr>
			</thead>
			<c:forEach items="${list}" var="list">
				<tr>
					<td><c:out value="${list.noticeNumber}" /></td>
					<td>
						<a class="move" href='<c:out value="${list.noticeNumber}"/>'> 
							<c:out value="${list.noticeTitle}" />
						</a>
					</td>
					<td>
						<c:out value="${list.noticeWriter}" />
					</td>
					<td>
						<fmt:formatDate pattern="yyyy/MM/dd"
							value="${list.noticeRegistrationDate}" />
					</td>
					<td>
						<fmt:formatDate pattern="yyyy/MM/dd"
							value="${list.noticeUpdateDate}" />
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<div class="search_wrap">
			<div class="search_area">
				<select name="type">
                <option value="" <c:out value="${pageMake.cri.type == null?'selected':'' }"/>>--</option>
                <option value="T" <c:out value="${pageMake.cri.type eq 'T'?'selected':'' }"/>>제목</option>
                <option value="C" <c:out value="${pageMake.cri.type eq 'C'?'selected':'' }"/>>내용</option>
                <option value="W" <c:out value="${pageMake.cri.type eq 'W'?'selected':'' }"/>>작성자</option>
                <option value="TC" <c:out value="${pageMake.cri.type eq 'TC'?'selected':'' }"/>>제목 + 내용</option>
                <option value="TW" <c:out value="${pageMake.cri.type eq 'TW'?'selected':'' }"/>>제목 + 작성자</option>
                <option value="TCW" <c:out value="${pageMake.cri.type eq 'TCW'?'selected':'' }"/>>제목 + 내용 + 작성자</option>
            </select> 
				<input type="text" name="keyword" value="${pageMake.cri.keyword}">
				<button>Search</button>
			</div>
		</div>
		
		<div class="amount_wrap">
			<div class="amount_area">
				
					<select name="amount" onchange="selectBoxChange(this.value);">
						<option value="" disabled >=== 선택 ===</option>
						<option value=5 <c:out value="${pageMake.cri.amount == 5?'selected':''}"/>>5개</option>
    					<option value=10 <c:out value="${pageMake.cri.amount == 10?'selected':''}"/>>10개</option>
    					<option value=20 <c:out value="${pageMake.cri.amount == 20?'selected':''}"/>>20개</option>
    					<option value=25 <c:out value="${pageMake.cri.amount == 25?'selected':''}"/>>25개</option>
    					
					</select>
				
			</div>
		</div>
		
		 
		<div class="page_select_wrap">
			<div class="page_select_area">
				<input type = "text" pattern="\d" name="pageSelect"value='<c:out value="${pageMake.cri.pageNum}"/>'>
				<input name = "totalPage" readonly="readonly" value = '<c:out value="${pageMake.realEnd}"/>'>
				<button>페이지 이동</button>
			</div>
		</div>
		
		<div class="pageInfo_wrap">
			<div class="pageInfo_area">
				<ul id="pageInfo" class="pageInfo">
				
					<!-- 이전페이지 버튼 -->
					<c:if test="${pageMake.prev}">
						<li class="pageInfo_btn previous"><a href="${pageMake.startPage-1}">이전</a></li>
					</c:if>
				
					<!-- 각 번호 페이지 버튼 -->
					<c:forEach var="num" begin="${pageMake.startPage}" end="${pageMake.endPage}">
						<li class="pageInfo_btn ${pageMake.cri.pageNum == num ? "active":"" }"><a href="${num}">${num}</a></li>
					</c:forEach>
					
					<!--  
					<form id="pageForm" method="post">
					<select name="amount" id="amount" onchange="selectBoxChange(this.value);">
						<option value="none">=== 선택 ===</option>
						<option value="5">5개</option>
    					<option value="10">10개</option>
    					<option value="20">20개</option>
					</select>
					</form>
					-->
					
					<!-- 다음페이지 버튼 -->
					<c:if test="${pageMake.next}">
						<li class="pageInfo_btn next"><a href="${pageMake.endPage+1}">다음</a></li>
					</c:if>
				</ul>
			</div>
		</div>
		
		<form id="moveForm" method="get">
			<input type="hidden" name="pageNum" value="${pageMake.cri.pageNum}">
			<input type="hidden" name="amount" value="${pageMake.cri.amount}">
			<input type="hidden" name="keyword" value="${pageMake.cri.keyword}">
			<input type="hidden" name="type" value="${pageMake.cri.type}">
			
		</form>
	</div>
</div>

	<script>
		let moveForm = $("#moveForm");
		let pageForm = $("#pageForm");
		
		function joinView(){
		    let f = document.createElement('form');
		    f.setAttribute('method', 'post');
		    f.setAttribute('action', '/member/joinView');
		    document.body.appendChild(f);
		    f.submit();
		}
		
		function loginView(){
		    let f = document.createElement('form');
		    f.setAttribute('method', 'post');
		    f.setAttribute('action', '/member/loginView');
		    document.body.appendChild(f);
		    f.submit();
		}
		
		function logout(){
		    let f = document.createElement('form');
		    f.setAttribute('method', 'post');
		    f.setAttribute('action', '/member/logout');
		    document.body.appendChild(f);
		    f.submit();
		}
		
		function registeView(){
		    let f = document.createElement('form');
		    f.setAttribute('method', 'post');
		    f.setAttribute('action', '/notice/registeView');
		    document.body.appendChild(f);
		    f.submit();
		}
		
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
			moveForm.find("input[name='pageNum']").val($(this).attr("href")); //<form>내부 pageNum과 관련된 <input>태그의 value 속성값에 클릭한<a>태그 페이지 번호 삽입
			moveForm.attr("action", "/notice/list");
			moveForm.submit();
		});
		
		/*
		var selectBoxChange = function(value){
			console.log("결과:" +value);
			$.ajax({
				type:"post",
				url:"/notice/pageAmount",
				data: "pageAmount=" +value,
				success : function(data){
					alert("성공", +data);
					console.log(data);
				}
			});
		}*/
		
		/*
		$("#pageForm").change(function(){
			var pageAmount = value;
			
			$.ajax({
				type:"post",
				url:"/notice/pageAmount",
				data: "pageAmount=" +pageAmount,
				success : function(data){
					alert("성공", +data);
					console.log(data);
				}
			});
			
		});*/
		
		
		//검색기능
		$(".search_area button").on("click", function(e){
			e.preventDefault();
			
			let type = $(".search_area select").val();
			let keyword = $(".search_area input[name='keyword']").val();
			
			if(!type){
				alert("검색 종류를 선택하세요");
				return false;
			}
			if(!keyword){
				alert("키워드를 입력하세요");
				return false;
			}
			
			//let val = $("input[name='keyword']").val();
			moveForm.find("input[name='type']").val(type);
			moveForm.find("input[name='keyword']").val(keyword);
			moveForm.find("input[name='pageNum']").val(1);
			moveForm.submit();
		});
		
		/*
		$("#pageForm").change(function(){
			var pageAmount = value;
			let amount = $(".amount_area select").val();
			
			pageForm.find("input[name]='amount'").val(amount);
			pageForm.submit();
		});*/
		
		//화면에 출력될 총 게시글 수 선택
		var selectBoxChange = function(value){
			
			let amount = $(".amount_area select").val();
			
			//pageForm.find("input[name]='amount'").val(amount);
			//pageForm.submit();
			moveForm.find("input[name='amount']").val(amount);
			moveForm.find("input[name='pageNum']").val(1);
			moveForm.submit();
			
			console.log("결과:" +value);
			//alert("성공", +data);
		}
		
		//선택 페이지 이동
		$(".page_select_area button").on("click",function(){
			
			let pageNum = $(".page_select_area [name='pageSelect']").val();
			
			moveForm.find("input[name='pageNum']").val(pageNum);
			moveForm.attr("action", "/notice/list");
			moveForm.submit();
		});
		
		
		
		
		/*
		$(".page_count_area button").on("click"function(){
			let pageCount = $(".page_count_area [name='pageCount']").val();
			moveForm.find("input[name='pageCount']").val(pageCount);
			moveForm.attr("action", "/notice/list");
			moveForm.submit();
		});
		*/
	</script>

</body>
</html>