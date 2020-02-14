package com.ifma.muzik.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifma.muzik.model.Musica;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Integer> {

	Page<Musica> findByNome(String nome, Pageable paginacao);

}