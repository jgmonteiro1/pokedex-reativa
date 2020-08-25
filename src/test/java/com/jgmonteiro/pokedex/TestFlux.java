package com.jgmonteiro.pokedex;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.publisher.Flux;

@SpringBootTest
public class TestFlux {
	
	@Test
	public void testeFlux1() {
		Flux.empty();
	}
	
	@Test
	public void testeFlux2() {
		Flux<String> flux = Flux.just("Pokedex");
		flux.subscribe(System.out::println);
	}
	
	@Test
	public void testeFlux3() {
		Flux<String> flux = Flux.just("Bulbasaur", "Charmander", "Squirtle");
		flux.subscribe(System.out::println);
	}

}
