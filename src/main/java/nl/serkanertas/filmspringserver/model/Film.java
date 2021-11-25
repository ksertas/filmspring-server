package nl.serkanertas.filmspringserver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long film_id;

    private int runtime;

    @ManyToMany
    @JoinTable(
            name = "actor_film",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actorsInFilm = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_film",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> usersWatchedFilm = new ArrayList<>();;

    private int rating;
}
