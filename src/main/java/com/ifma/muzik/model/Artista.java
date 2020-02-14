package com.ifma.muzik.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "artista")
public class Artista {

	//private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "artista_id")
	private Integer artistaId;

	@Column(name = "nome")
	private String nome;

	@Column(name = "nacionalidade")
	private String nacionalidade;

	@JsonIgnore
    @ManyToMany
    @JoinTable(name = "artista_album",
            joinColumns = @JoinColumn(name = "artista_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id"))
    private List<Album> albuns = new ArrayList<>();

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "artista_musica", joinColumns = { @JoinColumn(name = "artista_id") }, inverseJoinColumns = {
			@JoinColumn(name = "musica_id") })
	List<Musica> musicas = new ArrayList<>();


	public Integer getArtistaId() {
		return artistaId;
	}

	public void setArtistaId(Integer artistaId) {
		this.artistaId = artistaId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public List<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}

	public List<Album> getAlbuns() {
		return albuns;
	}

	public void setAlbums(List<Album> albuns) {
		this.albuns = albuns;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artistaId == null) ? 0 : artistaId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artista other = (Artista) obj;
		if (artistaId == null) {
			if (other.artistaId != null)
				return false;
		} else if (!artistaId.equals(other.artistaId))
			return false;
		return true;
	}
}