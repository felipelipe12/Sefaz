package com.felipe.Sefaz.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.felipe.Sefaz.util.ConnectionDB;

public class TelefoneDAO {
	private Connection connection;
	private PreparedStatement statement;
	private boolean status;
	
	private Connection conexao() throws SQLException {
		return ConnectionDB.getConnection();
	}
	
	//Método para adicionar os telefones.
	public boolean adicionarTelefone(Telefone telefone) throws SQLException {
		String sql = null;
		status = false;
		connection = conexao();
		
		try {
			connection.setAutoCommit(false);
			sql = "INSERT INTO telefone(id_telefone, ddd, numero, id_usuario) VALUES(?, ?, ?, ?)";
			
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1, telefone.getId_telefone());
			statement.setInt(2, telefone.getDdd());
			statement.setInt(3, telefone.getNumero());
			statement.setInt(4, telefone.getId_usuario());
			
			status =  statement.executeUpdate() > 0;
			
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
	
	//Método para listar os telefones.
	public List<Telefone> listarTelefone(int id_usuario) throws SQLException {
		ResultSet resultSet = null;
		List<Telefone> arrayTelefone = new ArrayList<>();
		
		String sql = null;
		status = false;
		connection = conexao();

		try {
			sql = "SELECT id_telefone, ddd, numero, id_usuario FROM telefone WHERE id_usuario=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id_usuario);

			resultSet = statement.executeQuery();

			while(resultSet.next()) {
				Telefone telefone = new Telefone();
				telefone.setId_telefone(resultSet.getInt(1));
				telefone.setDdd(resultSet.getInt(2));
				telefone.setNumero(resultSet.getInt(3));
				telefone.setId_usuario(resultSet.getInt(3));
				arrayTelefone.add(telefone);
			}

			statement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return arrayTelefone;
	}
	
	//Método para delerar os telefones.
	public boolean deletarTelefone(int id_usuario) throws SQLException {
		String sql = null;
		status = false;
		connection = conexao();

		try {
			connection.setAutoCommit(false);
			sql = "DELETE FROM telefone WHERE id_usuario = ?";
			
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id_usuario);

			status = statement.executeUpdate() > 0 ;
			connection.commit();
			statement.close();
		}
		catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return status;
	}
}
