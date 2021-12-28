<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<head>
<title>회원가입</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/custom.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="main.jsp">메인</a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="main.jsp">메인</a></li>
				<li><a href="bbs.jsp">게시판</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-hashpopup="true"
					aria-expanded="false">접속하기<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="login.jsp">로그인</a></li>
						<li class="active"><a href="join.jsp">회원가입</a></li>
					</ul></li>
			</ul>
		</div>
	</nav>
	<div class="container">

		<div class="col-lg-2"></div>
		<div class="col-lg-8">
			<div class="jumbotron" style="padding-top: 20px;">
				<form method="post" action="joinAction.jsp">
					<h3 style="text-align: center;">회원가입하기</h3>
					<div class="form-group">
						<label for="id">ID</label> <input type="text" class="form-control"
							placeholder="ID" name="userID" maxlength="20" autofocus>
					</div>
					<div class="form-group">
						<label for="password">비밀번호</label> <input type="password"
							class="form-control" placeholder="password" name="userPassword"
							maxlength="20">
					</div>
					<div class="form-group">
						<label for="name">이름</label> <input type="text"
							class="form-control" placeholder="name" name="userName"
							maxlength="20">
					</div>
					<div class="form-group">
						<label for="email">이메일</label> <input type="email"
							class="form-control" placeholder="email" name="userEmail"
							maxlength="20">
					</div>
					<div class="form-group" style="text-align: center;">
						<div class="btn-group" data-toggle="buttons">
							<label class="btn btn-primary active" style="background-color:#3392BB"> <input
								type="radio" name="userGender" autocomplete="off" value="male"
								checked>남자
							</label> <label class="btn btn-primary" style="background-color:#A71950"> <input type="radio"
								name="userGender" autocomplete="off" value="female">여자
							</label>
						</div>
					</div>
					<input type="submit" class="btn btn-primary form-control"
						value="등록" style="background-color:gray">
				</form>
			</div>
		</div>
		<div class="col-lg-2"></div>
	</div>
</body>

</html>