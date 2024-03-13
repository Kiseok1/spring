<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원리스트</title>
	</head>
	<!-- ♣♣♣ CSS ♣♣♣ -->
	<link href="../css/index.css" rel="stylesheet">
	
	<!-- ♣♣♣ font ♣♣♣ -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<!-- JQuery 최신 -->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	
	<!--◁◀◁◀ Header ▶▷▶▷ -->
	<%@ include file="/WEB-INF/views/include/sidebar.jsp"%>
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<!--◁◀◁◀ Header ▶▷▶▷ -->
	
	<script>
		$(function(){
			$("#listInsertBtn").click(function(){
				alert("test");
			});//#listInsertBtn(등록버튼)
			
			$("#listDelBtn").click(function(){
				if(confirm("삭제하시겠습니까?")){
					var selected = [];
			        // 체크된 체크박스의 값을 배열에 추가한다
			        $('input[type="checkbox"]:checked').each(function() {
			            selected.push($(this).val());
			        });
			        console.log(selected);
					$("#Frm").submit();
				}//if(삭제확인)
			});//#listInsertBtn(등록버튼)
			
			var Select_condition=0;
			$("#SelectAll").click(function(){
				alert("전체선택 버튼 test");
			
			/* if(Select_condition==0){
				$("#checkBox").prop("checked", true);
				Select_condition=1
			} else if(Select_condition==1) {
				$("#checkBox").prop("checked", false);
				Select_condition=0;
			} */
			
			if(Select_condition==0){
				$(".checkBox").prop("checked",true);
				Select_condition=1;
			} else if (Select_condition ==1){
				$(".checkBox").prop("checked",false);
				Select_condition=0;
			}
			
			});//#SelectAll(전체선택)
			
			$("#searchBtn").click(function(){
				alert("검색어 test");
				searchFrm.submit();
			});//#searchBtn(검색어)
			
		});//제이쿼리 최신
	</script>
	
	<body>
	
	<!--▼▼▼ 회원정보 리스트 전체테두리 ▼▼▼ -->
	<div class="table-container">
	<h1 style="font-size: 70px; font-weight: 700;">회원정보 리스트</h1>
	
	<!--검색어 찾기 -->
	<form action="/" method="get" name="searchFrm">
	<div id="search-container">
	
	<div id="seletBox" style="width: 275px; padding-top: 50px;">
		<input type="button" id="SelectAll" value="전체선택" style="font-weight: 700; margin-right: 10px;">
		<input type="radio" value="Male" name="gender"  id="Male" style="display: inline-block; vertical-align: middle;"><label for="Male">남자</label>
		<input type="radio" value="Female" name="gender" id="Female" style="display: inline-block; vertical-align: middle;"><label for="Female" >여자</label>
		<input type="radio" value="all" name="gender" id="all" style="display: inline-block; vertical-align: middle;"><label for="all">전체</label>
	</div>
			<div id="searchSection">
				<select id="ViewCondition" name="ppr">
					<c:if test="${map.ppr==5}">
						<option value="5" selected>5 개</option>
					</c:if>
					<c:if test="${map.ppr!=5}">
						<option value="5">5 개</option>
					</c:if>
					<c:if test="${map.ppr==10}">
						<option value="10" selected>10 개</option>
					</c:if>
					<c:if test="${map.ppr!=10}">
						<option value="10">10 개</option>
					</c:if>
					<c:if test="${map.ppr==20}">
						<option value="20" selected>20 개</option>
					</c:if>
					<c:if test="${map.ppr!=20}">
						<option value="20">20 개</option>
					</c:if>
				</select>
				<select id="searchCategory">
					<option value="all" name="category">전체</option>
					<option value="ymno" name="category">번호</option>
					<option value="btitle" name="category">제목</option>
				</select>
				<input type="text" id="searchWord" name="searchWord" placeholder=" ※검색어를 입력하세요.">
				<input type="button" id="searchBtn" value="검 색">
			</div>
		</form>
	</div>
	<!--검색어 찾기 끝 -->
	
	<hr style="border: 2px solid #14213d; margin-bottom: 20px;">
	  <table style="border-top: 3px solid #14213d;">
	    <thead>
	    <colgroup>
		    <col width="4%">
		    <col width="6%">
		    <col width="6%">
		    <col width="6%">
		    <col width="4%">
		    <col width="10%">
		    <col width="23%">
		    <col width="12%">
		    <col width="10%">
		    <col width="6%">
		    <col width="12%">
	    </colgroup>
	      <tr>
	        <th>번 호</th>
	        <th>아이디</th>
	        <th>비밀번호</th>
	        <th>성 명</th>
	        <th>성 별</th>
	        <th>연락처</th>
	        <th>주&nbsp;&nbsp;소</th>
	        <th>E-mail</th>
	        <th>주민번호</th>
	        <th>접속횟수</th>
	        <th>최근접속</th>
	      </tr>
	    </thead>
	    <form id="Frm" action="memDel" method="post">
	    <tbody style="border-bottom: 2px solid #14213d;">
	      <c:forEach var="dto" items="${map.list}">
	      	<tr>
		        <td class="Bno"><input type="checkbox" class="checkBox" value="${dto.ymno}" name="ymnos">${dto.ymno}</td>
		        <td class="ID">${dto.id}</td>
		        <td class="Bdate">${dto.pw}</td>
		        <td class="Btitle">${dto.name}</td>
		        <td class="Bdate">${dto.gender}</td>
		        <td class="Bgroup">${dto.phone}</td>
		        <td class="Bfile">${dto.address}</td>
		        <td class="Bhit">${dto.email}</td>
		        <td class="Bhit">${dto.pnumber}</td>
		        <td class="Bdate">${dto.login_num}</td>
		        <td class="Bdate">${dto.recent_time}</td>
	      	</tr>
	      </c:forEach>
	    </tbody>
	    </form>
	  </table>
	  
	  <!--페이지 넘버링 -->
	  <ul id="PageNum" style="display: flex; list-style: none;">
	  	<li class="num" onclick="location.href='/?page=1&gender=${map.gender}'"><i class="fa fa-backward" aria-hidden="true"></i></li>
	  	<c:if test="${map.page!=1}">
	  		<li class="num" onclick="location.href='/?page=${map.page-1}&gender=${map.gender}&ppr=${map.ppr}'"><i class="fa fa-chevron-circle-left" aria-hidden="true"></i></li>
	  	</c:if>
	  	<c:if test="${map.page==1}">
	  		<li class="num"><i class="fa fa-chevron-circle-left" aria-hidden="true"></i></li>
	  	</c:if>
	  	<c:forEach var="i" begin="${map.firstBox}" end="${map.endBox}" step="1">
	  		<c:if test="${map.page==i}">
		  		<li class="num" style="background: #14213d; color: yellow;">${i}</li>
	  		</c:if>
	  		<c:if test="${map.page!=i}">
		  		<li class="num" onclick="location.href='/?page=${i}&gender=${map.gender}&ppr=${map.ppr}'">${i}</li>
	  		</c:if>
	  	</c:forEach>
	  	<c:if test="${map.page!=map.maxBox}">
		  	<li class="num" onclick="location.href='/?page=${map.page+1}&gender=${map.gender}&ppr=${map.ppr}'"><i class="fa fa-chevron-circle-right" aria-hidden="true"></i></li>
	  	</c:if>
	  	<c:if test="${map.page==map.maxBox}">
		  	<li class="num"><i class="fa fa-chevron-circle-right" aria-hidden="true"></i></li>
	  	</c:if>
	  	<li class="num" onclick="location.href='/?page=${map.maxBox}&gender=${map.gender}&ppr=${map.ppr}'"><i class="fa fa-forward" aria-hidden="true"></i></li>
	  </ul>
	  <!--페이지 넘버링 끝 -->
	<!--▣ 버튼 ▣ -->
	<div class="button-container">
	  <input type="button" id="listInsertBtn" value="등록">
	  <input type="button" id="listDelBtn" value="삭제">
	</div>
	<!--▣ 버튼 ▣-->
	</div>
	<!-- ▲▲▲ 회원정보 리스트 전체 테두리 끝 ▲▲▲-->
	
	
	</body>
	
	<!--◁◀◁◀ Footer ▶▷▶▷ -->
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
	<!--◁◀◁◀ Footer ▶▷▶▷ -->
</html>