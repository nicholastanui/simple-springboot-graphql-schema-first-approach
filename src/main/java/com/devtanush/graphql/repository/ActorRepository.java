package com.devtanush.graphql.repository;

import com.devtanush.graphql.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ActorRepository extends JpaRepository<Actor, Integer> {
}
