<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザ情報更新</title>
<link  rel="stylesheet" href="css/MockTask.css">
</head>
<body>
	<div class="header">
		<ul>
			<li class="logout"><a href="LogoutServletMyself" class="under">ログアウト</a></li>
			<li class="userName">${userInfoMyself.name} さん</li>
		</ul>
	</div>

<!-- 	失敗時の表示-->
	<div class="errRed">
		<c:if test="${errMsg != null}" >
				  ${errMsg}
		</c:if>
	</div>

	<div class="center">
			<h1>ユーザ情報更新</h1>
		<br>
		<div class="information">


			<div><span>ログインID</span><span>${userMyself.loginId}</span></div>
			<br>
			<br>
			<form action="UserUpdateServletMyself" method="post">
				<input type="hidden" value="${userMyself.id}" name="id">
				<div><span>パスワード</span><span><input type="text" name="passWord"></span></div>
				<br>
				<br>
				<div><span>パスワード（確認）</span><span><input type="text" name="passwordConfirmation"></span></div>
				<br>
				<br>
				<div><span>ユーザ名</span><span><input type="text" name="userName" value="${userMyself.name}"></span></div>
				<br>
				<br>
				<div><span>生年月日</span><span><input type="text" name="birthday" value="${userMyself.birthDate}"></span></div>
				<br>
				<br>
				<div class="center"><input type="submit" value="更新"></div>
			</form>
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