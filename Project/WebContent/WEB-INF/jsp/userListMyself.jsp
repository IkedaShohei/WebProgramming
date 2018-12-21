<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザ一覧</title>
<link  rel="stylesheet" href="MockTask.css">
</head>
<body>
	<div class="header">
		<ul>
			<li class="logout"><a href="LogoutServletMyself" class="under">ログアウト</a></li>
			<li class="userName">{userInfo.name} さん</li>
		</ul>
	</div>
	<div class="center">
		<h1>ユーザ一覧</h1>
	</div>
		<br>
	<div class="right">
		<div class= "under">
			<p><a href="newSignUpMyself.jsp">新規登録</a></p>
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
		<tr><!-- 0001 -->
			<td>id0001</td>
			<td>田中太郎</td>
			<td>1989年04月26日</td>
			<td>
				<input type="submit" value="詳細">
				<input type="submit" value="更新">
				<input type="submit" value="削除">
			</td>
		</tr>
		<tr><!-- 0002 -->
			<td>id0002</td>
			<td>佐藤二朗</td>
			<td>2001年11月12日</td>
			<td>
				<input type="submit" value="詳細">
				<input type="submit" value="更新">
				<input type="submit" value="削除">
			</td>
		</tr>
		<tr><!-- 0003 -->
			<td>id0003</td>
			<td>佐川真司</td>
			<td>2000年01年01日</td>
			<td>
				<input type="submit" value="詳細">
				<input type="submit" value="更新">
				<input type="submit" value="削除">
			</td>
		</tr>
		</table>
</body>
</html>