package com.benhaimoud.mutator;

import com.benhaimoud.exception.DogNotFoundException;
import com.benhaimoud.repository.DogRepository;
import com.benhaimoud.entity.Dog;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {
    private DogRepository repository;

    public Mutation(DogRepository repository) {
        this.repository = repository;
    }
    public boolean deleteDogBreed(String breed) {
        boolean deleted = false;
        Iterable<Dog> allDogs = repository.findAll();
        //loop throug all dogs to check their breed
        for (Dog d:allDogs) {
            if(d.getBreed().equals(breed));
            //delete if the breed is found
            repository.delete(d);
            deleted = true;
        }
        return deleted;
    }
    public Dog updateDogName(String newName, long id) {
        Optional<Dog> optionalDog = repository.findById(id);
        if(optionalDog.isPresent()) {
            Dog dog = optionalDog.get();
            dog.setName(newName);
            repository.save(dog);
            return dog;
        } else {
            throw new DogNotFoundException("Dog not found", id);
        }
    }

}
