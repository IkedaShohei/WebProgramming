<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザ一覧</title>
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
		<h1>ユーザ一覧</h1>
	</div>
		<br>
	<div class="right">
		<div class= "under">
			<p><a href="NewSighUpServletMyself">新規登録</a></p>
		</div>
	</div>
		<br>
		<br>
		<br>
	<div class="center">
		<a>ログインID</a><input type="text" name="loginId">
		<br>
		<a>ユーザ名</a><input type="text" name="passWord">
		<br>
		生年月日<input type="date" name="passWord">〜<input type="date" name="passWord">
		<br>
	</div>
	<div class="right">
	<input type="submit" value="検索">
	</div>

	<p class="border"> </p>

	<br>
	<br>
	<br>

		<table>
		<tr><!-- 項目 -->
			<th>ログインID</th>
			<th>ユーザー名</th>
			<th>生年月日</th>
			<th>　　　　</th>
		</tr>
		<c:forEach var="userMyself" items="${userMyselfList}" >

				<tr>
					<td>${userMyself.loginId}</td>
					<td>${userMyself.name}</td>
					<td>${userMyself.birthDate}</td>
					<td>
	<!-- 	もし管理者じゃなかったら -->
					 <a class="btn btn-primary" href="UserDetailServletMyself?id=${userMyself.id}">詳細</a>
	                 <a class="btn btn-success" href="UserUpdateServletMyself?id=${userMyself.id}">更新</a>
	                 <a class="btn btn-danger" href ="UserDeleteServletMyself?id=${userMyself.id}">削除</a>

					</td>
				</tr>

		</c:forEach>
		</table>
</body>
</html>