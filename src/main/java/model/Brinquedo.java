package model;

import controller.ServletBrinquedo;

public class Brinquedo {
	private int idBrinquedo;
	private String nome;
	private String descricao;
	private String categoria;
	private String marca;
	private Double valor;
	private String detalhes;
	public String imagem;
 	   
	
	
	
	public Brinquedo(int idBrinquedo, String nome, String descricao, String categoria, String marca, Double valor,
			String detalhes, String imagem) {
		super();
	
		this.idBrinquedo = idBrinquedo;
		this.nome = nome;
		this.descricao = descricao;
		this.categoria = categoria;
		this.marca = marca;
		this.valor = valor;
		this.detalhes = detalhes;
		this.imagem= imagem;
		
		 
	}
	
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	
	public Brinquedo() {
	}
	
	public int getIdBrinquedo() {
		return idBrinquedo;
	}
	public void setIdBrinquedo(int idBrinquedo) {
		this.idBrinquedo = idBrinquedo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getDetalhes() {
		return detalhes;
	}
	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}
	
	

}

