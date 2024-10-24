<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${std.stdName}의 정보 수정 페이지</title>
</head>
<body>

	<h4>정보 수정</h4>

	<form action="/std/update" method="post" id="updateForm">
		<div>
			이름 : <input type = "text" name = "stdName" value = "${std.stdName}">
		</div>
		<div>
			나이 : <input type = "number" name = "stdAge" value = "${std.stdAge}">
		</div>
		<div>
			성별 : <label><input type="radio" name="stdGender" value = "M">남성</label>
    			   <label><input type="radio" name="stdGender" value = "F">여성</label>
		</div>
		<div>
    		성적 : <select name="stdScore">
	 				<option value="A">A</option>
	 				<option value="B">B</option>
	 				<option value="C">C</option>
	 				<option value="D">D</option>
	 				<option value="F">F</option>
 				  </select>
		</div>
	
		<input type = "hidden" name="stdNo" value="${param.stdNo}">
	
		<button>학생 정보 수정하기</button>
	
	</form>
	
	<c:if test = "${not empty sessionScope.message}">
	  	<script>
	  		alert("${message}");
	  	</script>
  	
  		<c:remove var = "message" scope = "session" />
	</c:if>
	
</body>
</html>