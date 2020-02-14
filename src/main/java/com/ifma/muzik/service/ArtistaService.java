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
public class ArtistaService {

	private final ArtistaRepository artistaRepository;
	private final AlbumRepository albumRepository;
	private final MusicaRepository musicaRepository;

	private final GenericoService<Artista> genericoService;

	@Autowired
	public ArtistaService(ArtistaRepository artistaRepository, AlbumRepository albumRepository,
		MusicaRepository musicaRepository) {
		this.albumRepository = albumRepository;
		this.artistaRepository = artistaRepository;
		this.musicaRepository = musicaRepository;
		this.genericoService = new GenericoService<Artista>(artistaRepository);
	}

	@Transactional
	public Artista salva(Artista artista) {
		validaAlbuns(artista.getAlbuns());
		validaMusicas(artista.getMusicas());
		return this.artistaRepository.save(artista);
	}

	private void validaAlbuns(List<Album> list) {
		if (list != null && !list.isEmpty()) {

			list.forEach(album -> {

				Integer id = album.getAlbumId();

				if (id == null) {
					throw new IllegalArgumentException("O id do album não pode ser vazio");
				}

				Optional<Album> optional = albumRepository.findById(id);
				album = optional.orElseThrow(() -> new IllegalArgumentException("Album Inválido " + id));
			});
		}
	}

	private void validaMusicas(List<Musica> list) {
		if (list != null && !list.isEmpty()) {

			list.forEach(musica -> {

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
	public List<Artista> obterTodosArtistas() {
		return genericoService.buscaTodasAsEntities();
	}

	Optional<Artista> buscaPor(String nome) {
		return Optional.ofNullable(artistaRepository.findByNome(nome));
	}

	public Artista buscaPor(Integer id) {
		Optional<Artista> optionalArtista = artistaRepository.findById(id);
		return optionalArtista.orElseThrow(() -> new EmptyResultDataAccessException(1));
	}
	
	@Transactional
	public void excluir(Integer id) {
		artistaRepository.deleteById(id);
	}

	@Transactional
	public Artista atualiza(Integer id, Artista artista) {
		Artista artistaManager = this.buscaPor(id);

		if (artistaManager == null) {

			throw new EmptyResultDataAccessException(1);
		}

		BeanUtils.copyProperties(artista, artistaManager, "id");

		this.salva(artistaManager);

		return artistaManager;
	}
}