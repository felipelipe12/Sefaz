<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" %>
<%@page import="com.felipe.Sefaz.model.Usuario" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Pitang - Ações do Usuário</title>
		<link rel="icon" href="resources/img/favicon.png" />
		<link rel="stylesheet" type="text/css" href="resources/css/style.css">
		<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Ubuntu'>
	</head>
	<body>
        <div>
            <ul class="ulhome">
                <li class="lilogo">
                    <a href="#">
                        <img class="logobar" src="resources/img/logo.png">
                    </a>
                </li>
                <li class="lihome">
                    <a href="#">Home</a>
                </li>
                <li class="lihome">
                    <a href="#">Ações do Usuário</a>
                </li>
                <li class="lihome">
                    <a href="#">Sobre</a>
                </li>
                <li style="float:right;" class="lihome active">
                    <a href="#">Logout</a>
                </li>
            </ul>
        </div>
        <div class="center table">
            <table>
                <tr>
                
                <% ArrayList<Usuario> list = (ArrayList<Usuario>) request.getAttribute("arrayUsuarios");

						for(Usuario usuario : list) {
						    out.println(usuario.getId_usuario());
						    out.println(usuario.getNome());
						    out.println(usuario.getEmail());
						} %>
						
                    <th>Código</th>
                    <th>Nome</th>
                    <th>Email</th>
                    <th colspan="2">Ações</th>
                </tr>

                <tr>
                    <td>${arrayUsuarios.nome}</td>
                    <td>${arrayUsuarios.email}</td>
                    <td>${arrayUsuarios.senha}</td>
                    <td>
                        <a style="color: #01DF01;" href="">Atualizar</a>
                    </td>
                    <td>
                        <a style="color: #FF0000;" href="">Deletar</a>
                    </td>
                </tr>
            </table>
        </div>
	</body>
</html>