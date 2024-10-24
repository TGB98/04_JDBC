<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang = "ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${std.stdName} 상세 조회</title>
<link rel="stylesheet" href="/resources/css/detail.css">
</head>
<body>
	<h1>${std.stdName} 상세 조회</h1>
	<div class="content">
이름 : ${std.stdName}
나이 : ${std.stdAge}
성별 : ${std.stdGender}
성적 : ${std.stdScore}
	</div>
	
	<div class="btn-container">
		<div>
			<button id="goToList">목록으로 가기</button>
		</div>
		
		<div>
			<button id="updateBtn">학생 정보 수정하기</button>
			<button id="deleteBtn">학생 정보 삭제하기</button>
		</div>
	</div>

	<c:if test = "${not empty sessionScope.message}">
		<script>
			alert("${message}");
		</script>
	
		<c:remove var = "message" scope = "session" />
	</c:if>

	<script src = "/resources/js/detail.js"></script>
</body>
</html>