<!DOCTYPE html>
<html>
	<head>
		<title>Pitang - Acesso</title>
		<link rel="icon" href="resources/img/favicon.png" />
		<link rel="stylesheet" type="text/css" href="resources/css/style.css">
		<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Ubuntu'>
	</head>
	<body>
		<div class="center">
			<form action="UsuarioController" method="post">
				<input type="hidden" name="opcao" value="login">
				
				<div class="alignVertical">
					<img class="logo space" src="resources/img/logo.png">
				</div>
				<div class="alignVertical">
					<input type="text" name="nome" placeholder="Email">
				</div>
				<div class="alignVertical">
					<input type="password" name="senha" placeholder="Senha">
				</div>
				<div class="alignVertical">	
					<input type="submit" value="Enviar">
				</div>
				<div class="alignVertical space">
	                <label>Não possui cadastro?
	                    <a class="linkLogin" href="IncluirUsuarioController">Clique aqui!</a>
	                </label>
	                <label>
	                    <a class="linkLogin" href="UsuarioController?opcao=listar">Listar Usuários</a>
	                </label>
            	</div>
			</form>
		</div>
	</body>
</html>