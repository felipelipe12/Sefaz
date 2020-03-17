package com.felipe.Sefaz.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.felipe.Sefaz.model.Telefone;
import com.felipe.Sefaz.model.TelefoneDAO;
import com.felipe.Sefaz.model.Usuario;
import com.felipe.Sefaz.model.UsuarioDAO;

@WebServlet("/UsuarioController")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UsuarioController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcao = request.getParameter("opcao");
		
		if (opcao.equals("guardar")) {
			UsuarioDAO usuariodao = new UsuarioDAO();
			TelefoneDAO telefonedao = new TelefoneDAO();
			
			Usuario usuario = new Usuario();
			
			usuario.setNome(request.getParameter("nome"));
			usuario.setEmail(request.getParameter("email"));
			usuario.setSenha(request.getParameter("senha"));

/*			try {
				int id_usuario = (int) usuariodao.adicionarUsuario(usuario);

					Telefone telefone = new Telefone();

					telefone.setDdd(Integer.parseInt(request.getParameter("ddd")));
					telefone.setNumero(Integer.parseInt(request.getParameter("telefone")));
					telefone.setId_usuario(id_usuario);
	
					telefonedao.cadastrarTelefone(telefone);
				
				System.out.println("Cadastro realizado com sucesso!");
				HttpSession session=request.getSession();  
				session.setAttribute("msgAviso", "Cadastro realizado com sucesso!");
				session.setAttribute("msgAvisoCor", "green");
				if(session.getAttribute("usuario") == null || session.getAttribute("usuario") == ""){
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");					
					requestDispatcher.forward(request, response);
				} else {
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/principal.jsp");					
					requestDispatcher.forward(request, response);					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}*/
		}
		
		response.getWriter().append(opcao).append(request.getContextPath());
	}

}
