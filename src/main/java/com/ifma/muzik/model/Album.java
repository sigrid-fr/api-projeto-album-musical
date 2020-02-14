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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


	@Entity
	@Table(name = "album")
	public class Album{

		//private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "album_id")
		private Integer albumId;

		@NotNull
		@Column(name = "nome")
		private String nome;

		@Column(name = "ano")
		private Integer ano;

		@JsonIgnore
		@ManyToMany
	    @JoinTable(name = "artista_album",
	    joinColumns = @JoinColumn(name = "artista_id"),
	    inverseJoinColumns = @JoinColumn(name = "album_id"))
	    private List<Artista> artistas = new ArrayList<>();


	    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
	    private List<Musica> musicas = new ArrayList<>();

		public Integer getAlbumId() {
			return albumId;
		}

		public void setAlbumId(Integer albumId) {
			this.albumId = albumId;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public Integer getAno() {
			return ano;
		}

		public void setAno(Integer ano) {
			this.ano = ano;
		}

		public List<Artista> getArtistas() {
	        return artistas;
	    }

	    public void setArtistas(List<Artista> artistas) {
	        this.artistas = artistas;
	    }

		public List<Musica> getMusicas() {
			return musicas;
		}

		public void setMusicas(List<Musica> musicas) {
			this.musicas = musicas;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((albumId == null) ? 0 : albumId.hashCode());
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
			Album other = (Album) obj;
			if (albumId == null) {
				if (other.albumId != null)
					return false;
			} else if (!albumId.equals(other.albumId))
				return false;
			return true;
		}
	}