package com.ifma.muzik.repository.filter.musica;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ifma.muzik.model.Musica;
import com.ifma.muzik.repository.filter.MusicaFiltro;

public interface MusicaRepositoryQuery {

    List<Musica> filtrar(MusicaFiltro filtro);

    Page<Musica> filtrar(MusicaFiltro filtro, Pageable pageable);
}
