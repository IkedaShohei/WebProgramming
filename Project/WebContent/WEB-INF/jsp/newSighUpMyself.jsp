<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザ新規登録</title>
<link  rel="stylesheet" href="MockTask.css">
</head>
<body>
	<div class="header">
		<ul>
			<li class="logout"><a  href="LogoutServletMyself" class="under">ログアウト</a></li>
			<li class="userName">${userInfoMyself.name} さん</li>
		</ul>
	</div>
	
	<!-- もし登録が失敗した時 ログインjspからの引用-->
	<c:if test="${errMsg != null}" >
			  ${errMsg}
	</c:if>
	
	<div class="center">
		<h1>ユーザ新規登録</h1>
	</div>
	<br>
	<form action="NewSighUpServletMyself" method="post">
		<div class="center">
			<a>ログインID</a><input type="text" name="loginId">
			<br>
			<br>
			<a>パスワード</a><input type="text" name="password">
			<br>
			<br>
			<a>パスワード（確認）</a><input type="text" name="passwordConfirmation">
			<br>
			<br>
			<a>ユーザ名</a><input type="text" name="userName">
			<br>
			<br>
			<a>生年月日</a><input type="text" name="birthDay">
			<br>
			<br>
			<br>
		<input type="submit" value="登録">
		</div>
	</form>
	<br>
	<br>
	<br>
	<div class="left">
		<div class= "under">
			<p><a href="NewSignUp.html">戻る</a></p>
		</div>
	</div>



</body>
</html>