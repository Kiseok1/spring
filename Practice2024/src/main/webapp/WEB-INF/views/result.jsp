<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>결과페이지</title>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	</head>
	<body>
		<c:choose>
			<c:when test="${result == 'login-success'}">
				<script>
					alert("로그인 되었습니다.");
					location.href="/";
				</script>
			</c:when>
			<c:when test="${result == 'login-error'}">
				<script>
					alert("아이디 또는 패스워드가 일치하지 않습니다.");
					location.href="login";
				</script>
			</c:when>
			<c:when test="${result == 'logout-success'}">
				<script>
					alert("로그아웃 되었습니다.");
					location.href="/";
				</script>
			</c:when>
			<c:when test="${result == 'delete-success'}">
				<script>
					alert("회원정보를 삭제하였습니다.");
					location.href="/";
				</script>
			</c:when>
		
			<c:otherwise>
			
			</c:otherwise>
		</c:choose>
	
	</body>
</html>