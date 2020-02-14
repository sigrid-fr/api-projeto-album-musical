package com.ifma.muzik.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ifma.muzik.dto.MusicaDto;
import com.ifma.muzik.model.Musica;
import com.ifma.muzik.repository.MusicaRepository;
import com.ifma.muzik.service.MusicaService;

@RestController
@RequestMapping("/api/musicas")
public class MusicaController {

	private final MusicaService musicaService;

	@Autowired
	public MusicaController(MusicaService musicaService) {
		this.musicaService = musicaService;
	}
	
	@Autowired
	private MusicaRepository musicaRepository;
	
	@GetMapping("/musicas")
	public List<Musica> getAllMusicas() {
		return musicaService.obterTodasMusicas();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Musica> getMusicaById(@PathVariable(value = "id") Integer musicaId){
		Musica musica = musicaService.buscaPor(musicaId);
		return ResponseEntity.ok().body(musica);
	}
	
	@GetMapping
	public Page<MusicaDto> lista(@RequestParam(required = false) String nome, 
			@PageableDefault(sort = "musicaId", direction = Direction.ASC, page = 0) Pageable paginacao) {
		
		if (nome == null) {
			Page<Musica> musicas = musicaRepository.findAll(paginacao);
			return MusicaDto.converter(musicas);
		} else {
			Page<Musica> musicas = musicaRepository.findByNome(nome, paginacao);
			return MusicaDto.converter(musicas);
		}
	}
	
	//@GetMapping
	/*public ResponseEntity<?> listaDeMusicas() {

		List<Musica> musicas = this.musicaService.obterTodasMusicas();

		if (musicas.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(musicas);
		}
	}*/

	@PostMapping
	public ResponseEntity<?> cria(@Validated @RequestBody Musica musica) {

		Musica musicaSalvo = musicaService.salva(musica);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(musicaSalvo.getMusicaId()).toUri();

		return ResponseEntity.created(uri).body(musicaSalvo);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Musica> atualizaMusica(@PathVariable(value = "id") Integer musicaId,
			@Valid @RequestBody Musica musicaDetails) {
		
		Musica musica = musicaService.buscaPor(musicaId);
		musica.setNome(musicaDetails.getNome());
		musica.setDuracao(musicaDetails.getDuracao());
		final Musica updatedMusica = musicaService.salva(musica);
		return ResponseEntity.ok(updatedMusica);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Map<String, Boolean> deleteMusica(@PathVariable(value = "id") Integer musicaId){
		Musica musica = musicaService.buscaPor(musicaId);
		musicaService.excluir(musicaId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}

