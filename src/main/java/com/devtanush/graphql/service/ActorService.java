package com.devtanush.graphql.service;

import com.devtanush.graphql.model.Actor;
import com.devtanush.graphql.repository.ActorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {
    private ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }
}
