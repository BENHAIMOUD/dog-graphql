package com.benhaimoud.resolver;

import com.benhaimoud.exception.DogNotFoundException;
import com.benhaimoud.repository.DogRepository;
import com.benhaimoud.entity.Dog;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {
    @Autowired
    private DogRepository repository;

    public Iterable<Dog> findAllDogs() {
        return repository.findAll();
    }

    public Dog findDogById(long id) {
        Optional<Dog> optionalDog = repository.findById(id);
        if(optionalDog.isPresent()){
            return optionalDog.get();
        } else {
            throw new DogNotFoundException("Dog Not Found", id);
        }
        }

}
