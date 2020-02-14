package com.ifma.muzik.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifma.muzik.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {

	Album findByNome(String nome);
	
    Optional< List<Album> > findByNomeContaining(String nome );

}
