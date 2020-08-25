package com.jgmonteiro.pokedex.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.jgmonteiro.pokedex.model.Pokemon;
@Repository
public interface PokemonRepository extends ReactiveMongoRepository<Pokemon, String> {

}
