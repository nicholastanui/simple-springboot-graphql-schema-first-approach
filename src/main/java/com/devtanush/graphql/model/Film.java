package com.devtanush.graphql.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "FILM")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FILM_ID")
    private Integer filmId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "LAUNCH_YEAR")
    private Date launchYear;

    public Film() {
    }

    public Film(String name, Date launchYear) {
        this.name = name;
        this.launchYear = launchYear;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLaunchYear() {
        return launchYear;
    }

    public void setLaunchYear(Date launchYear) {
        this.launchYear = launchYear;
    }
}
