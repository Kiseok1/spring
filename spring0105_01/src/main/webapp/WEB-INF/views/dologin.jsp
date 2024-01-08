<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<c:if test="${session_id!=null}">
			<script>
				alert("로그인되었습니다.");
				location.href="/";
			</script>		
		</c:if>
		<c:if test="${session_id==null}">
			<script>
				alert("아이디 또는 패스워드가 일치하지 않습니다.");
				location.href="login";
			</script>		
		</c:if>
		
		<script>
			if(${session_id}==null){
				alert("아이디 또는 패스워드가 일치하지 않습니다.");
				location.href="login";
			}
		</script>
	
	</body>
</html>