<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ログイン画面</title>
	<link  rel="stylesheet" href="MockTask.css">
</head>



<!-- もしDaoのメソッドを実行して入力されたデータが見つからなかった場合のメッセージ -->
<c:if test="${errMsg != null}" >
		  ${errMsg}
</c:if>

<body>
	<h1 class="Login">ログイン画面</h1>
		<br>
		<br>
		<br>

<!-- こっからフォーム -->
	<form action="LoginServletMyself" method="post">
		<div class="center">
			ログインID<input type="text" name="loginId">
			<br>
			パスワード<input type="text" name="password">
			<br>
			<br>
			<br>
		<input type="submit" value="ログイン">
		</div>
	</form>
</body>
</html>