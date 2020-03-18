package com.felipe.Sefaz.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        String opcao = request.getParameter("opcao");

        if (opcao.equals("listar")) {
            listar(request, response);
        } else if (opcao.equals("deletar")) {
            UsuarioDAO usuariodao = new UsuarioDAO();
            TelefoneDAO telefonedao = new TelefoneDAO();

            int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
            try {
                usuariodao.deletarUsuario(id_usuario);
                System.out.println("Exclusão do usuário " + request.getParameter("id_usuario") + " realizado com sucesso!");
                telefonedao.deletarTelefone(id_usuario);
                System.out.println("Exclusão de todos os números do usuário " + request.getParameter("id_usuario") + " realizado com sucesso!");

                listar(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        UsuarioDAO usuariodao = new UsuarioDAO();
        List<Usuario> arrayUsuarios = new ArrayList<>();
        TelefoneDAO telefonedao = new TelefoneDAO();
        List<ArrayList<Telefone>> arrayTelefone = new ArrayList<>();

        try {
            arrayUsuarios = usuariodao.listarUsuarios();

            for (Usuario usuario : arrayUsuarios) {
                arrayTelefone.add((ArrayList<Telefone>) telefonedao.listarTelefone(usuario.getId_usuario()));
            }

            request.setAttribute("arrayUsuarios", arrayUsuarios);
            request.setAttribute("arrayTelefone", arrayTelefone);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/listarUsuario.jsp");
            requestDispatcher.forward(request, response);
        } catch (SQLException e) {

            e.printStackTrace();
        }
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

			try {
				int id_usuario = (int) usuariodao.adicionarUsuario(usuario);

				Telefone telefone = new Telefone();

				telefone.setDdd(Integer.parseInt(request.getParameter("ddd")));
				telefone.setNumero(Integer.parseInt(request.getParameter("numero")));
				telefone.setId_usuario(id_usuario);
	
				telefonedao.adicionarTelefone(telefone);
				
			HttpSession session=request.getSession();  
				session.setAttribute("msgAviso", "Cadastro realizado com sucesso!");
				session.setAttribute("msgAvisoCor", "green");
				if(session.getAttribute("usuario") == null || session.getAttribute("usuario") == ""){
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");					
					requestDispatcher.forward(request, response);
				} else {
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/listarUsuario.jsp");					
					requestDispatcher.forward(request, response);					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} else if (opcao.equals("editar")) {
			UsuarioDAO usuariodao = new UsuarioDAO();
			Usuario usuario = new Usuario();
			TelefoneDAO telefonedao = new TelefoneDAO();
			
			usuario.setId_usuario(Integer.parseInt(request.getParameter("id_usuario")));
			usuario.setNome(request.getParameter("nome"));
			usuario.setEmail(request.getParameter("email"));
			usuario.setSenha(request.getParameter("senha"));
			
			try {
				usuariodao.atualizarUsuario(usuario);
				System.out.println("Edição do usuario " + request.getParameter("id_usuario") + " realizado com sucesso!");
				
				telefonedao.deletarTelefone(Integer.parseInt(request.getParameter("id_usuario")));
				System.out.println("Exclusão de todos os números do usuário " + request.getParameter("id_usuario") + " realizado com sucesso!");
				
				Telefone telefone = new Telefone();

				telefone.setNumero(Integer.parseInt(request.getParameter("numero")));
				telefone.setDdd(Integer.parseInt(request.getParameter("ddd")));
				telefone.setId_usuario(Integer.parseInt(request.getParameter("id_usuario")));
	
				telefonedao.adicionarTelefone(telefone);
				System.out.println("Criação do 1º numero do usuario id " + request.getParameter("id_usuario") + " realizado com sucesso!");

				HttpSession session=request.getSession();  
				session.setAttribute("msgAviso", "Edição realizada com sucesso!");
				session.setAttribute("msgAvisoCor", "green");

				RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/editarUsuario.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}