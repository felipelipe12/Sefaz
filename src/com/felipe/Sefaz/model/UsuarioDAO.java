package com.felipe.Sefaz.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.felipe.Sefaz.util.ConnectionDB;

public class UsuarioDAO {
	private Connection connection;
	private boolean status;
	private PreparedStatement statement;
	
	private Connection conexao() throws SQLException {
		return ConnectionDB.getConnection();
	}
	
	//Método para adicionar os usuários.
	public long adicionarUsuario(Usuario usuario) throws SQLException {
		String sql = null;
		long id_gerado = 0;
		status = false;
		connection = conexao();
		
		try {
			connection.setAutoCommit(false);
			
			sql = "INSERT INTO usuario(id_usuario, nome, email, senha) VALUES (?, ?, ?, ?)";
			
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, null);
			statement.setString(2, usuario.getNome());
			statement.setString(3, usuario.getEmail());
			statement.setString(4, usuario.getSenha());
			
			status =  statement.executeUpdate() > 0;
			
			if (status == false) {
				throw new SQLException("Erro ao criar um novo usuário!");
			}

			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					id_gerado = generatedKeys.getLong(1);
				} else {
					throw new SQLException("Não foi possível criar um novo usuário, o ID não foi obtido.");
				}
			}
			connection.commit();
			statement.close();
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return id_gerado;
	}
	
	//Método para atualizar os usuários.
	public boolean atualizarUsuario(Usuario usuario) throws SQLException {
		String sql = null;
		status = false;
		connection = conexao();

		try {
			connection.setAutoCommit(false);
			sql = "UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE id_usuario = ?";
			
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, usuario.getNome());
			statement.setString(2, usuario.getEmail());
			statement.setString(3, usuario.getSenha());
			statement.setInt(4, usuario.getId_usuario());
			
			status = statement.executeUpdate() > 0 ;
			connection.commit();
			statement.close();
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		} finally {
			System.out.println("fechou");
			connection.close();
		}
		return status;
	}
	
	//Método para deletar os usuários.
	public boolean deletarUsuario(int id_usuario) throws SQLException {
		String sql = null;
		status = false;
		connection = conexao();

		try {
			connection.setAutoCommit(false);
			sql = "DELETE FROM usuario WHERE id_usuario = ?";
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1, id_usuario);
			
			status = statement.executeUpdate() > 0 ;
			connection.commit();
			statement.close();	
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		} finally {
			System.out.println("fechou");
			connection.close();
		}
		return status;
	}
	
	//Método para listar os usuários.
	
}
