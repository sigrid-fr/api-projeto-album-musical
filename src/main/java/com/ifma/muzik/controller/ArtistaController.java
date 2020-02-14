
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.ifma.muzik.model.Artista;
import com.ifma.muzik.service.ArtistaService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/artistas")
public class ArtistaController {

	private final ArtistaService artistaService;

	@Autowired
	public ArtistaController(ArtistaService artistaService) {
		this.artistaService = artistaService;
	}

	@GetMapping("/artistas")
	public List<Artista> getAllArtistas() {
		return artistaService.obterTodosArtistas();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Artista> getArtistaById(@PathVariable(value = "id") Integer artistaId){
		Artista artista = artistaService.buscaPor(artistaId);
		return ResponseEntity.ok().body(artista);
	}
	

	@GetMapping
	public ResponseEntity<?> listaArtistas() {

		List<Artista> artistas = this.artistaService.obterTodosArtistas();

		if (artistas.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(artistas);
		}
	}
	

	@PostMapping
	public ResponseEntity<?> cria(@Validated @RequestBody Artista artista) {

		Artista artistaSalvo = artistaService.salva(artista);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(artistaSalvo.getArtistaId()).toUri();

		return ResponseEntity.created(uri).body(artistaSalvo);

	}

	
	@PutMapping("/{id}")
	public ResponseEntity<Artista> atualizaArtista(@PathVariable(value = "id") Integer artistaId,
			@Valid @RequestBody Artista artistaDetails) {
		
		Artista artista = artistaService.buscaPor(artistaId);
		artista.setNome(artistaDetails.getNome());
		artista.setNacionalidade(artistaDetails.getNacionalidade());
		final Artista updatedArtista = artistaService.salva(artista);
		return ResponseEntity.ok(updatedArtista);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Map<String, Boolean> deleteArtista(@PathVariable(value = "id") Integer artistaId){
		Artista artista = artistaService.buscaPor(artistaId);
		artistaService.excluir(artistaId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	
}