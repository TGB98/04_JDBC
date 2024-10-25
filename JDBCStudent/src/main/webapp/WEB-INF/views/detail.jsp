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
	<table id = "stdList">
		<thead class = "thead">
			<tr>
				<th>이름</th>
				<th>나이</th>
				<th>성별</th>
				<th>성적</th>
			</tr>
		</thead>
		
		<tbody class = "tbody">
			<tr>
				<th>${std.stdName}</th>
				<th>${std.stdAge}</th>
				<th>${std.stdGender}</th>
				<th>${std.stdScore}</th>
			</tr>
		</tbody>
	</table>

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