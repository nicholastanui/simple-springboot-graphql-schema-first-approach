package com.devtanush.graphql.service;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.devtanush.graphql.model.Actor;
import com.devtanush.graphql.model.AddressInput;
import com.devtanush.graphql.repository.ActorRepository;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ActorService implements GraphQLQueryResolver, GraphQLMutationResolver, GraphQLSubscriptionResolver {
    // all public methods under classes implementing GraphQLQueryResolver will be registered as Query
    // and will try to map the name of the method with the ones under the schema file.
    private ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    private ConcurrentHashMap<Integer, FluxSink<Actor>> subscribers = new ConcurrentHashMap<>();

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
        if (subscribers.get(id) != null) {
            subscribers.get(id).next(actor);
        }
        return actor;
    }

    @Transactional
    public Actor updateAddressByInputObject(AddressInput input) {
        Actor actor = actorRepository.findById(input.getActorId()).get();
        actor.setAddress(input.getAddress());
        actorRepository.save(actor);
        if (subscribers.get(input.getActorId()) != null) {
            subscribers.get(input.getActorId()).next(actor);
        }
        return actor;
    }

    public Publisher<Actor> onActorUpdate(Integer actorId) {
        return Flux.create(subscriber -> subscribers.put(actorId, subscriber.onDispose(()-> subscribers.remove(actorId, subscriber))), FluxSink.OverflowStrategy.LATEST);
    }
}
