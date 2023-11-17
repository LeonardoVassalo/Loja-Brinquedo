package model;
import controller.ServletBrinquedo;


public class Usuario {
	
	private int idUsuario;
	private String nome;
	private String senha;

	
	public Usuario() {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.senha = senha;
							
	}
	public Usuario(int idUsuario, String nome, String senha) {
		super();
	
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.senha = senha;
		 
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	

}
