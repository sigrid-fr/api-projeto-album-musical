package com.ifma.muzik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifma.muzik.model.Artista;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Integer> {

	Artista findByNome(String nome);

}