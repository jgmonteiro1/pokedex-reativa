package com.jgmonteiro.pokedex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;

import com.jgmonteiro.pokedex.model.Pokemon;
import com.jgmonteiro.pokedex.repository.PokemonRepository;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class PokedexApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokedexApplication.class, args);

	}

	@Bean
	CommandLineRunner init(ReactiveMongoOperations operations, PokemonRepository repository) {
		return args -> {
			Flux<Pokemon> pokedexFlux = Flux
					.just(new Pokemon(null, "Bulbasaur", "Semente", "Super Crescimento", 6.9),
							new Pokemon(null, "Charmander", "Fogo", "Chama", 8.5),
							new Pokemon(null, "Squirtle", "Agua", "Torrent", 9.0))

					.flatMap(repository::save);

			pokedexFlux.thenMany(repository.findAll()).subscribe(System.out::println);
		};
	}

}
