package com.devtanush.graphql.service;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.devtanush.graphql.model.Actor;
import com.devtanush.graphql.repository.ActorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService implements GraphQLQueryResolver {
    // all public methods under classes implementing GraphQLQueryResolver will be registered as Query
    // and will try to map the name of the method with the ones under the schema file.
    private ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    public Actor getActorbyId(Integer id) {
        return actorRepository.findById(id).get();
    }
}
