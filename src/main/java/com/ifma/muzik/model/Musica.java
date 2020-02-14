package com.ifma.muzik.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "musica")
public class Musica {

	//private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "musica_id")
	private Integer musicaId;

	@Column(name = "nome")
	private String nome;

	@Column(name = "duracao")
	private Double duracao;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "album_id")
	private Album album;

	@JsonIgnore
	@ManyToMany(mappedBy = "musicas")
	private List<Artista> artistas = new ArrayList<>();

	public Integer getMusicaId() {
		return musicaId;
	}

	public void setMusicaId(Integer musicaId) {
		this.musicaId = musicaId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getDuracao() {
		return duracao;
	}

	public void setDuracao(Double duracao) {
		this.duracao = duracao;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public List<Artista> getArtistas() {
		return artistas;
	}

	public void setArtistas(List<Artista> artistas) {
		this.artistas = artistas;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((musicaId == null) ? 0 : musicaId.hashCode());
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
		Musica other = (Musica) obj;
		if (musicaId == null) {
			if (other.musicaId != null)
				return false;
		} else if (!musicaId.equals(other.musicaId))
			return false;
		return true;
	}
}