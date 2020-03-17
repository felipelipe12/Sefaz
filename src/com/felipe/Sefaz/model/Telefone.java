package com.felipe.Sefaz.model;

public class Telefone {
	private int id_telefone;
	private int ddd;
	private int numero;
	private int id_usuario;
	
	public int getId_telefone() {
		return id_telefone;
	}
	
	public void setId_telefone(int id_telefone) {
		this.id_telefone = id_telefone;
	}
	
	public int getDdd() {
		return ddd;
	}
	
	public void setDdd(int ddd) {
		this.ddd = ddd;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public int getId_usuario() {
		return id_usuario;
	}
	
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
}
