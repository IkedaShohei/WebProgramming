<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザ情報詳細参照</title>
<link  rel="stylesheet" href="css/MockTask.css">
</head>
<body>
	<div class="header">
		<ul>
			<li class="logout"><a href="LogoutServletMyself" class="under">ログアウト</a></li>
			<li class="userName">${userInfoMyself.name} さん</li>
		</ul>
	</div>
	<div class="center">
			<h1>ユーザ情報詳細参照</h1>
		<br>
		<div class="information">
			<div><span>ログインID</span><span>${userMyself.loginId}</span></div>
			<br>
			<br>
			<div><span>ユーザ名</span><span>${userMyself.name}</span></div>
			<br>
			<br>
			<div><span>生年月日</span><span>${userMyself.birthDate}</span></div>
			<br>
			<br>
			<div><span>登録日時</span><span>${userMyself.createDate}</span></div>
			<br>
			<br>
			<div><span>更新日時</span><span>${userMyself.updateDate}</span></div>
		</div>
	</div>
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