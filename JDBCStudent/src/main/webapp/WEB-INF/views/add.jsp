<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>떡잎마을 유치원생 추가</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

    <h4>학생 추가</h4>
    <form action="/std/goToadd" method="post" id="addForm">
    	<div>
    		이름 : <input type="text" name="stdName">
    	</div>
    	<div>
    		나이 : <input type="number" name="stdAge">
    	</div>
    	<div>
    		성별 : <label><input type="radio" name="stdGender"  value = "M">남성</label>
    			   <label><input type="radio" name="stdGender"  value = "F">여성</label>
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


<script src="/resources/js/add.js"></script>
</body>
</html>