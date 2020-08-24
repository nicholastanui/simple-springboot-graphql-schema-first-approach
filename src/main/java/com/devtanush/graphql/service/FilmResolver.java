package com.devtanush.graphql.service;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.devtanush.graphql.model.Actor;
import com.devtanush.graphql.model.Film;
import com.devtanush.graphql.repository.FilmRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FilmResolver implements GraphQLResolver<Actor> {
    private FilmRepository filmRepository;

    public FilmResolver(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Transactional
    public Film getFilm(Actor actor) {
        return filmRepository.findById(actor.getFilmId()).get();
    }
}
