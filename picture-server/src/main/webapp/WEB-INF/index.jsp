<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
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
        <li><a href="/user/update">修改</a></li>
        <li><a href="/user/delete">删除</a></li>
        <li><span>以下是拥有权限才显示的内容</span></li>
        <shiro:hasPermission name="USER_QUERY">
        	<li><a href="/user/query">查询</a></li>
        </shiro:hasPermission>
        <shiro:hasPermission name="USER_ADD">
        	<li><a href="/user/add">添加</a></li>
        </shiro:hasPermission>
        <shiro:hasPermission name="USER_UPDATE">
        	<li><a href="/user/update">修改</a></li>
        </shiro:hasPermission>
        <shiro:hasPermission name="USER_DELETE">
        	<li><a href="/user/delete">删除</a></li>
        </shiro:hasPermission>
    </ul>
        <a href="/user/logOut">点我注销</a>
</body>
</html>