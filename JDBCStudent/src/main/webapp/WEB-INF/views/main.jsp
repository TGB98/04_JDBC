<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>떡잎마을 유치원</title>
	<link rel="stylesheet" href="/resources/css/main.css">
</head>
<body>
	<h1>떡잎마을 유치원</h1>

	 <table id="stdList" border="1">
    <thead>
      <tr>
        <th>학생 번호</th> 
        <th>학생 이름</th> 
      </tr>
    </thead>
    
    <tbody>
     <c:forEach items = "${stdList}" varStatus ="vs" var = "std">
      <tr>
      	<th>${std.stdNo}</th>
      	<td>
	    	<h3>등록되어 있는 학생 : 
			 <a href="/std/detail?stdNo=${std.stdNo}">${std.stdName}</a>
			</h3>
		</td>
	  </tr>
	 </c:forEach>
    </tbody>
    
    <h4>학생 추가</h4>
    <form action="/std/add" method="post" id="addForm">
    	<div>
    		이름 : <input type="text" name="stdName">
    	</div>
    	<div>
    		나이 : <input type="number" name="stdAge">
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
    	<button>추가하기</button>
    </form>

	<c:if test = "${not empty sessionScope.message}">
		<script>
			alert("${message}");
		</script>
		
		<c:remove var = "message" scope = "session" />
	</c:if>

	<script src="/resources/js/main.js"></script>
</body>
</html>