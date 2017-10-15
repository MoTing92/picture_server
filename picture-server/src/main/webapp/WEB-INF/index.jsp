<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图片上传</title>
</head>
<body>
	<form action="fileUpload" method="post" enctype="multipart/form-data">
    	<input type="file" name="uploadFile" /><br>
    	<input type="submit" value="上传" />
	</form>
	<h1>欢迎${user.username }光临!请选择你的操作:</h1><br>
    <ul>
        <li><a href="/user/query">查询</a></li>
        <li><a href="/user/add">添加</a></li>
    </ul>
        <a href="/user/logOut">点我注销</a>
</body>
</html>