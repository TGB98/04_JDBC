<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${std.stdName}의 정보 수정 페이지</title>
<link rel="stylesheet" href="/resources/css/update.css">
</head>
<body>

	<h4>정보 수정</h4>

	<form action="/std/update" method="post" id="updateForm">
	
		<table id = updateT>
			<thead class = updateThead>
				<tr>
					<th>이름
					<th><input type = "text" name = "stdName" value = "${std.stdName}">
				</tr>
				<tr>
					<th>나이
					<th><input type = "number" name = "stdAge" value = "${std.stdAge}">
				</tr>
				<tr>
					<th>성별
					<th><label><input type="radio" name="stdGender" value = "M">남성</label>
    			   		<label><input type="radio" name="stdGender" value = "F">여성</label>
				</tr>
				<tr>
					<th>성적
					<th><select name="stdScore">
			 				<option value="A">A</option>
			 				<option value="B">B</option>
			 				<option value="C">C</option>
			 				<option value="D">D</option>
			 				<option value="F">F</option>
 				  		</select>
				</tr>
				
			</thead>
		</table>
	
		<input type = "hidden" name="stdNo" value="${param.stdNo}">
	
		<button id = "stdInfoUpdate">정보 수정 완료 버튼</button>
	
	</form>
	
	<script>
		// js코드 작성
		document.addEventListener('DOMContentLoaded', () => {
			const radio = document.querySelectorAll("[name=stdGender]");
			const select = document.querySelector("[name=stdScore]").options;
			
			if("${std.stdGender}" == "남") { 
				radio[0].setAttribute("checked", true);
			}
			
			if("${std.stdGender}" == "여") {
				
				radio[1].setAttribute("checked", true);
			}
			
			for(let i = 0; i < select.length; i++) { // 성적 selected
				if(select[i].value == "${std.stdScore}") select[i].selected = true;
			}
			
		});
	</script>

	<c:if test = "${not empty sessionScope.message}">
	  	<script>
	  		alert("${message}");
	  	</script>
  	
  		<c:remove var = "message" scope = "session" />
	</c:if>
	
	<script src = "/resources/js/update.js"></script>
</body>
</html>