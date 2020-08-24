package com.devtanush.graphql.service;

import com.devtanush.graphql.model.Actor;
import com.devtanush.graphql.model.Film;
import com.devtanush.graphql.repository.ActorRepository;
import com.devtanush.graphql.repository.FilmRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class DataLoaderService {
    private ActorRepository actorRepository;
    private FilmRepository filmRepository;

    public DataLoaderService(ActorRepository actorRepository, FilmRepository filmRepository) {
        this.actorRepository = actorRepository;
        this.filmRepository = filmRepository;
    }

    @PostConstruct
    public void loadData(){
        String [] actors = {"Chuck Norris", "Van Dame", "Bill Spencer", "Bill Cosby"};
        Map<String, String> films = new HashMap<String, String>() {
            {
                put("Chuck Norris", "Vietnam");
                put("Van Dame", "Kick Boxer");
                put("Bill Spencer", "Run Town");
                put("Bill Cosby", "The Bill Cosby Show");
            }
        };

        for (String actorName: actors) {
            String [] names = actorName.split(" ");

            Date dateOfBirth = DataLoaderService.between(new Date(1960, 01, 01), new Date(1980, 01, 01));
            Date dateOfLaunch = DataLoaderService.between(new Date(1990, 01, 01), new Date(2000, 01, 01));
            Film film = new Film(films.get(actorName), dateOfLaunch);
            filmRepository.save(film);
            Actor actor = new Actor(names[0], names[1], dateOfBirth, "Nairobi Kenya", film.getFilmId());
            actorRepository.save(actor);
        }
    }

    public static Date between(Date startInclusive, Date endExclusive) {
        long startMillis = startInclusive.getTime();
        long endMillis = endExclusive.getTime();
        long randomMillisSinceEpoch = ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis);

        return new Date(randomMillisSinceEpoch);
    }
}
