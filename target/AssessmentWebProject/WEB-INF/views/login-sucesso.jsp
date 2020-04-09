<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sessão iniciada</title>
<style type="text/css">
@import url(https://fonts.googleapis.com/css?family=Roboto:300);

.login-page {
	width: 360px;
	padding: 8% 0 0;
	margin: auto;
}

.form {
	position: relative;
	z-index: 1;
	background: #FFFFFF;
	max-width: 360px;
	margin: 0 auto 100px;
	padding: 45px;
	text-align: center;
	box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0
		rgba(0, 0, 0, 0.24);
}

h2, ul{
	text-align:center;
}

li{}

.form button {
	font-family: "Roboto", sans-serif;
	text-transform: uppercase;
	outline: 0;
	background: #4CAF50;
	width: 100%;
	border: 0;
	padding: 15px;
	color: #FFFFFF;
	font-size: 14px;
	-webkit-transition: all 0.3 ease;
	transition: all 0.3 ease;
	cursor: pointer;
}

body {
	background: #76b852; /* fallback for old browsers */
	background: -webkit-linear-gradient(right, #76b852, #8DC26F);
	background: -moz-linear-gradient(right, #76b852, #8DC26F);
	background: -o-linear-gradient(right, #76b852, #8DC26F);
	background: linear-gradient(to left, #76b852, #8DC26F);
	font-family: "Roboto", sans-serif;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
}
#menu{width: 300px; margin: 0 auto;
</style>
</head>
<body>
	<jsp:useBean id="usuario" class="entidade.Usuario" scope="session"></jsp:useBean>
	<jsp:useBean id="endereco" class = "entidade.Endereco" scope = "session"></jsp:useBean>
	
	<h2><jsp:getProperty property="nome" name="usuario"/>, parabéns você iniciou a sua sessão!! seus dados são: </h2>
	<div id="menu">
		<ul>
			<li>Nome: <jsp:getProperty property="nome" name="usuario"/></li>
			<li>Email: <jsp:getProperty property="email" name="usuario"/></li>
			<li>Pais: <jsp:getProperty property="pais" name="endereco"/></li>
			<li>Cidade: <jsp:getProperty property="cidade" name="endereco"/></li>
			<li>Bairro: <jsp:getProperty property="bairro" name="endereco"/></li>
			<li>Endereco: <jsp:getProperty property="endereco" name="endereco"/></li>
			<li>Cep: <jsp:getProperty property="cep" name="endereco"/></li>
		</ul>
	</div>
	
		<div class="login-page">
			<div class="form">
				<form method="get" action="deletarConta">
					<button value="Deletar Usuario">Deletar Usuario</button>
				</form>
			</div>
		</div>
</body>
</html>