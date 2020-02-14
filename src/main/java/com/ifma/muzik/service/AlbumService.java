package com.ifma.muzik.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifma.muzik.model.Album;
import com.ifma.muzik.model.Artista;
import com.ifma.muzik.model.Musica;
import com.ifma.muzik.repository.AlbumRepository;
import com.ifma.muzik.repository.ArtistaRepository;
import com.ifma.muzik.repository.MusicaRepository;


@Service
public class AlbumService {

	private final AlbumRepository albumRepository;
	private final ArtistaRepository artistaRepository;
	private final MusicaRepository musicaRepository;

	private final GenericoService<Album> genericoService;

	@Autowired
	public AlbumService(AlbumRepository albumRepository, ArtistaRepository artistaRepository,
		MusicaRepository musicaRepository) {
		this.albumRepository = albumRepository;
		this.artistaRepository = artistaRepository;
		this.musicaRepository = musicaRepository;
		this.genericoService = new GenericoService<Album>(albumRepository);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Album salva(Album album) {
		validaArtistas(album.getArtistas());
		validaMusicas((List<Musica>) album.getMusicas());
		return this.albumRepository.save(album);
	}

	private void validaArtistas(List<Artista> artistas) {
		if (artistas != null && !artistas.isEmpty()) {

			artistas.forEach(artista -> {

				Integer id = artista.getArtistaId();

				if (id == null) {
					throw new IllegalArgumentException("O id do artista não pode ser vazio");
				}

				Optional<Artista> optional = artistaRepository.findById(id);
				artista = optional.orElseThrow(() -> new IllegalArgumentException("Artista Inválido " + id));
			});
		}
	}

	private void validaMusicas(List<Musica> musicas) {
		if (musicas != null && !musicas.isEmpty()) {

			musicas.forEach(musica -> {

				Integer id = musica.getMusicaId();

				if (id == null) {
					throw new IllegalArgumentException("O id da musica não pode ser vazio");
				}

				Optional<Musica> optional = musicaRepository.findById(id);
				musica = optional.orElseThrow(() -> new IllegalArgumentException("Musica Inválida " + id));
			});
		}
	}

	@Transactional(readOnly = true)
	public List<Album> obterTodosAlbuns() {
		return genericoService.buscaTodasAsEntities();
	}

	public Album buscaPor(Integer id) {

		Optional<Album> optionalCategoria = albumRepository.findById(id);

		return optionalCategoria.orElseThrow(() -> new EmptyResultDataAccessException(1));
	}

	@Transactional
	public void excluir(Integer id) {
		albumRepository.deleteById(id);
	}

	@Transactional
	public Album atualiza(Integer id, Album album) {
		Album albumManager = this.buscaPor(id);

		if (albumManager == null) {

			throw new EmptyResultDataAccessException(1);
		}

		BeanUtils.copyProperties(album, albumManager, "id");

		this.salva(albumManager);

		return albumManager;
	}
}