<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Pitang - Cadastro</title>
        <link rel="icon" href="./resources/img/favicon.png" />
		<link rel="stylesheet" type="text/css" href="./resources/css/style.css">
		<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Ubuntu'>
    </head>
<body>
    <div class="center">
        <form action="UsuarioController" method="post">
            <div class="alignVertical space title">
                <p>Preencha os campos abaixo</p>
            </div>
            <div class="alignVertical">
                <input type="text" name="nome" placeholder="Nome">
            </div>
            <div class="alignVertical">
                <input type="text" name="email" placeholder="Email">
            </div>
            <div class="alignVertical">
                <input type="password" name="senha" placeholder="Senha">
            </div>
            	<input type="text" name="ddd" placeholder="DDD" size="3" maxlength="3">
            	<input type="text" name="telefone" placeholder="Telefone" size="7" maxlength="8">
            <div class="alignVertical space">
                <input type="submit" value="Enviar">
            </div>
        </form>
    </div>
</body>
</html>