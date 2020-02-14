package com.ifma.muzik.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.ifma.muzik.model.Album;
import com.ifma.muzik.model.Artista;
import com.ifma.muzik.service.AlbumService;

@RestController
@RequestMapping("/api/albuns")
public class AlbumController {

	private final AlbumService albumService;

	@Autowired
	public AlbumController(AlbumService albumService) {
		this.albumService = albumService;
	}
	
	@GetMapping("/albuns")
	public List<Album> getAllAlbuns() {
		return albumService.obterTodosAlbuns();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Album> getArtistaById(@PathVariable(value = "id") Integer albumId){
		Album album = albumService.buscaPor(albumId);
		return ResponseEntity.ok().body(album);
	}

	@PostMapping
	public ResponseEntity<?> cria(@Validated @RequestBody Album album) {

		Album albumSalvo = albumService.salva(album);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(albumSalvo.getAlbumId()).toUri();

		return ResponseEntity.created(uri).body(albumSalvo);

	}

	@GetMapping
	public ResponseEntity<?> listaAlbuns() {

		List<Album> albuns = this.albumService.obterTodosAlbuns();

		if (albuns.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(albuns);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Album> atualizaAlbum(@PathVariable(value = "id") Integer albumId,
			@Valid @RequestBody Album albumDetails) {
		
		Album album = albumService.buscaPor(albumId);
		album.setNome(albumDetails.getNome());
		album.setAno(albumDetails.getAno());
		final Album updatedAlbum = albumService.salva(album);
		return ResponseEntity.ok(updatedAlbum);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Map<String, Boolean> deleteAlbum(@PathVariable(value = "id") Integer albumId){
		Album album = albumService.buscaPor(albumId);
		albumService.excluir(albumId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
