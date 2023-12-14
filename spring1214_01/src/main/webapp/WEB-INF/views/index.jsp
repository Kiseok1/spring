<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>index.jsp 페이지입니다.</h1>
		<ul>
			<a href="/"></a><li>메인화면</li>
			<a href="/member/mInsert"><li>회원가입</li>
			<a href="/member/login"><li>로그인</li>
			<a href="/board/bList"><li>공지사항리스트(list)</li>
			<a href="/board/bView"><li>공지사항보기(view)</li>
			<a href="/board/bInsert"><li>공지사항글쓰기(insert)</li>
		</ul>
	
	</body>
</html>