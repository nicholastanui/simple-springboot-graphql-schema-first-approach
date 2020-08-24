package com.devtanush.graphql.repository;

import com.devtanush.graphql.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface FilmRepository extends JpaRepository<Film, Integer> {
}
