package com.felipe.Sefaz.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.felipe.Sefaz.util.ConnectionDB;

public class UsuarioDAO {
	private Connection connection;
	private boolean status;
	private PreparedStatement statement;
	
	private Connection conexao() throws SQLException {
		return ConnectionDB.getConnection();
	}
	
	//M�todo para adicionar os usu�rios.
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
				throw new SQLException("Erro ao criar um novo usu�rio!");
			}

			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					id_gerado = generatedKeys.getLong(1);
				} else {
					throw new SQLException("N�o foi poss�vel criar um novo usu�rio, o ID n�o foi obtido.");
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
	
	//M�todo para atualizar os usu�rios.
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
	
	//M�todo para deletar os usu�rios.
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
			connection.close();
		}
		return status;
	}
	
	//M�todo para listar os usu�rios.
	public List<Usuario> listarUsuarios() throws SQLException {
		ResultSet resultSet = null;
		List<Usuario> arrayUsuarios = new ArrayList<>();
		
		String sql = null;
		status = false;
		connection = conexao();

		try {
			sql = "SELECT * FROM usuario ORDER BY nome ASC";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				Usuario usuario = new Usuario();
				usuario.setId_usuario(resultSet.getInt(1));
				usuario.setNome(resultSet.getString(2));
				usuario.setEmail(resultSet.getString(3));
				usuario.setSenha(resultSet.getString(4));
				
				arrayUsuarios.add(usuario);
			}
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return arrayUsuarios;
	}
	
	//M�todo para listar um usu�rio.
	public Usuario listarUsuario(int id_usuario) throws SQLException {
		ResultSet resultSet = null;
		Usuario usuario = new Usuario();

		String sql = null;
		status = false;
		connection = conexao();

		try {
			sql = "SELECT * FROM usuario WHERE id_usuario =?";
			statement=connection.prepareStatement(sql);
			statement.setInt(1, id_usuario);
			
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {				
				usuario.setId_usuario(resultSet.getInt(1));
				usuario.setNome(resultSet.getString(2));
				usuario.setEmail(resultSet.getString(3));
				usuario.setSenha(resultSet.getString(4));
			}
			statement.close();
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return usuario;
	}
	
	//M�todo para fazer login com um usu�rio.
	public Usuario logar(Usuario usu) throws SQLException {
		ResultSet resultSet = null;

		Usuario usuario = new Usuario();

		String sql = null;
		status = false;
		connection = conexao();

		try {
			sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
			statement=connection.prepareStatement(sql);
			statement.setString(1, usu.getEmail());
			statement.setString(2, usu.getSenha());
			resultSet = statement.executeQuery();

			if(resultSet.next()) {				
				usuario.setId_usuario(resultSet.getInt(1));
				usuario.setNome(resultSet.getString(2));
				usuario.setEmail(resultSet.getString(3));
				usuario.setSenha(resultSet.getString(4));
			}
			statement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return usuario;
	}
}
