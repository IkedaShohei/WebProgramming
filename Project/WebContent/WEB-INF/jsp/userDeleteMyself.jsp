<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザ削除確認</title>
<link  rel="stylesheet" href="css/MockTask.css">
</head>
<body>
	<div class="header">
		<ul>
			<li class="logout"><a class="under" href="LogoutServletMyself">ログアウト</a></li>
			<li class="userName">${userInfoMyself.name} さん</li>
		</ul>
	</div>
	<div class="center">
			<h1>ユーザ削除確認</h1>
	<br>
	<br>
	<div><P>ログインID：${userMyself.loginId}</P></div>
	<div>を本当に削除してよろしいでしょうか。</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<form action="UserDeleteCancelServlet" method="get">
		<span class="bottun"><input type="submit" value="キャンセル"></span>
	</form>
	<form action="UserDeleteServletMyself" method="post">
		<input type="hidden" value="${userMyself.id}" name="id">
		<span class="bottun"><input type="submit" value="OK"></span>
	</form>
	</div>

</body>
</html>