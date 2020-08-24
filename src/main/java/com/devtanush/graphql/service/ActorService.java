package com.devtanush.graphql.service;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.devtanush.graphql.model.Actor;
import com.devtanush.graphql.model.AddressInput;
import com.devtanush.graphql.repository.ActorRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ActorService implements GraphQLQueryResolver, GraphQLMutationResolver {
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

    @Transactional
    public Actor updateAddress(Integer id, String address) {
        Actor actor = actorRepository.findById(id).get();
        actor.setAddress(address);
        actorRepository.save(actor);
        return actor;
    }

    @Transactional
    public Actor updateAddressByInputObject(AddressInput input) {
        Actor actor = actorRepository.findById(input.getActorId()).get();
        actor.setAddress(input.getAddress());
        actorRepository.save(actor);
        return actor;
    }
}
