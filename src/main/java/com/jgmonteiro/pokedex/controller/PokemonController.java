package com.jgmonteiro.pokedex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jgmonteiro.pokedex.model.Pokemon;
import com.jgmonteiro.pokedex.repository.PokemonRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {
	
	@Autowired
	private PokemonRepository repository;
	
	/*
	public PokemonController(PokemonRepository repository){
		this.repository = repository;
	}*/
	
	@GetMapping
	public Flux<Pokemon> getAllPokemons(){
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<Pokemon>> findById(@PathVariable String id){
		return repository.findById(id).map(pokemon -> ResponseEntity.ok().body(pokemon)).defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Pokemon> savePokemon(@RequestBody Pokemon obj){
		return repository.save(obj);
	}
	
	@PutMapping("{id}")
	public Mono<ResponseEntity<Pokemon>> updatePokemon(@PathVariable(value = "id") String id, @RequestBody Pokemon obj){
		return repository.findById(id).flatMap(updatePokemon -> {
			updatePokemon.setNome(obj.getNome());
			updatePokemon.setCategoria(obj.getCategoria());
			updatePokemon.setHabilidade(obj.getHabilidade());
			updatePokemon.setPeso(obj.getPeso());
			return repository.save(updatePokemon);
		}).map(updatePokemon ->ResponseEntity.ok(updatePokemon)).defaultIfEmpty(ResponseEntity.notFound().build());		
	}
	
	@DeleteMapping("{id}")
	public Mono<ResponseEntity<Void>> deletePokemon(@PathVariable(value = "id") String id){
		return repository.findById(id).flatMap(pokemonExist -> repository.delete(pokemonExist).then(Mono.just(ResponseEntity.ok().<Void>build()))).defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping
	public Mono<Void> deleteAll(){
		return repository.deleteAll();
	}
	
	
	
}
