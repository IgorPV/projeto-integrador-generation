package com.educati.EducaTI.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "Temas", uniqueConstraints = @UniqueConstraint(columnNames={"id","titulo"}) )
public class Temas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 5, max = 100)
	private String titulo;
	
	@NotNull
	@Size(min = 10,max = 10000)
	private String texto;
	
	private String nivel;
	
	@OneToMany(mappedBy = "temas", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("temas")
	private List<Posts> posts;
	
	@ManyToMany(mappedBy = "temasInscritos", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"senha","email","temasInscritos","postsUsuario"})
	private List<Usuario> usuariosInscritos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public List<Posts> getPosts() {
		return posts;
	}

	public void setPosts(List<Posts> posts) {
		this.posts = posts;
	}

	public List<Usuario> getUsuariosInscritos() {
		return usuariosInscritos;
	}

	public void setUsuariosInscritos(List<Usuario> usuariosInscritos) {
		this.usuariosInscritos = usuariosInscritos;
	}

	
}
