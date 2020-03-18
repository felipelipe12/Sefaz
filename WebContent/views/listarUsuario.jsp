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
	                    <th>Código</th>
	                    <th>Nome</th>
	                    <th>Email</th>
	                    <th colspan="2">Ações</th>
	                </tr>
	
	
	                <% ArrayList<Usuario> list = (ArrayList<Usuario>) request.getAttribute("arrayUsuarios");
	
					for(Usuario usuario : list) {
					%>
	
	                <tr>
	   					<%	    out.println("<td> " + usuario.getId_usuario() + "</td>");
							    out.println("<td> " + usuario.getNome() + "</td>");
							    out.println("<td> " + usuario.getEmail() + "</td>");
	                	%>
	                    <td>
	                        <a style="color: #01DF01;" href="UsuarioController?opcao=editar&id_usuario">Atualizar</a>
	                    </td>
	                    <td>
	                        <a style="color: #FF0000;" href="UsuarioController?opcao=deletar&id_usuario=<%= usuario.getId_usuario() %>">Deletar</a>
	                    </td>
	                </tr>
	                <% } %>
	            </table>
	        </div>
		</body>
	</html>