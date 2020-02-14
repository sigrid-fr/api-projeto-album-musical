package com.ifma.muzik.repository.filter;

public class MusicaFiltro {

	private String nome;
	private Double duracaoDe;
	private Double duracaoAte;
	private Integer albumId;
	private Integer artistaId;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getDuracaoDe() {
		return duracaoDe;
	}

	public void setDuracaoDe(Double duracaoDe) {
		this.duracaoDe = duracaoDe;
	}

	public Double getDuracaoAte() {
		return duracaoAte;
	}

	public void setDuracaoAte(Double duracaoAte) {
		this.duracaoAte = duracaoAte;
	}

	public Integer getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Integer albumId) {
		this.albumId = albumId;
	}

	public Integer getArtistaId() {
		return artistaId;
	}

	public void setArtistaId(Integer artistaId) {
		this.artistaId = artistaId;
	}
}
