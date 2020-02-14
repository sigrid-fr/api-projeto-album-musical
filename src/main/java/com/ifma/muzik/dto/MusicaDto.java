package com.ifma.muzik.dto;

import org.springframework.data.domain.Page;

import com.ifma.muzik.model.Musica;

public class MusicaDto {

	private Integer id;
	private String nome;
	private Double duracao;
	
	public MusicaDto(Musica musica) {
		this.id = musica.getMusicaId();
		this.nome = musica.getNome();
		this.duracao = musica.getDuracao();
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Double getDuracao() {
		return duracao;
	}

	public static Page<MusicaDto> converter(Page<Musica> musicas) {
		return musicas.map(MusicaDto::new);
	}

}
