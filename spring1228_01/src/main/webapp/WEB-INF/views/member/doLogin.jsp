<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<script>
			if("${session_id}"==""){
				alert("아이디 또는 패스워드가 일치하지 않습니다.");
				location.href="login";
			} else {
				alert("로그인 되었습니다.");
				location.href="/";
			}
			
		</script>
	
	</body>
</html>