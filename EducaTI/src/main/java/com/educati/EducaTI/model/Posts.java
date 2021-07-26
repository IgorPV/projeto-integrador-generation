package com.educati.EducaTI.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Posts")
public class Posts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPosts;
	
	@NotNull
	@Size(min=10 , max=5000)
	private String textoPosts;
	
	@NotNull
	@Size(min=1 , max=45)
	private String tituloPosts;
	
	@Size(max=1000)
	private String multimPosts;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPosts = new java.sql.Date(System.currentTimeMillis());
	
	@ManyToOne
	@JsonIgnoreProperties({"posts","usuariosInscritos"})
	private Temas temas;
	
	@ManyToOne
	@JsonIgnoreProperties({"postsUsuario", "senha","email","temasInscritos"})
	private Usuario usuarioCriador;

	public Long getIdPosts() {
		return idPosts;
	}

	public void setIdPosts(Long idPosts) {
		this.idPosts = idPosts;
	}

	public String getTextoPosts() {
		return textoPosts;
	}

	public void setTextoPosts(String textoPosts) {
		this.textoPosts = textoPosts;
	}

	public String getTituloPosts() {
		return tituloPosts;
	}

	public void setTituloPosts(String tituloPosts) {
		this.tituloPosts = tituloPosts;
	}

	public String getMultimPosts() {
		return multimPosts;
	}

	public void setMultimPosts(String multimPosts) {
		this.multimPosts = multimPosts;
	}

	public Date getDataPosts() {
		return dataPosts;
	}

	public void setDataPosts(Date dataPosts) {
		this.dataPosts = dataPosts;
	}

	public Temas getTemas() {
		return temas;
	}

	public void setTemas(Temas temas) {
		this.temas = temas;
	}

	public Usuario getUsuarioCriador() {
		return usuarioCriador;
	}

	public void setUsuarioCriador(Usuario usuarioCriador) {
		this.usuarioCriador = usuarioCriador;
	}
	
	
	

}
