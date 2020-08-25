package com.jgmonteiro.pokedex;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.publisher.Mono;

@SpringBootTest
public class TestMono {
	
	@Test
	public void testeMono1() {
		Mono<String> mono = Mono.empty();
	}
	
	@Test
	public void testeMono2() {
		//Espera uma string com o nome de um pokemon
		Mono<String> mono = Mono.just("Pokemon");
		//Printa na tela se o teste passou ou não
		mono.subscribe(System.out::println);
	}
	
	@Test
	public void testeMono3() {
		
		Mono<Integer> mono = Mono.just(10);
		mono.subscribe(System.out::println);
	}
	
	@Test
	public void testeMono4() {
		Mono<String> mono = Mono.error(new RuntimeException("Ocorreu uma exception"));
	}
}
