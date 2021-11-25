package nl.serkanertas.filmspringserver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "series")
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long series_id;

    private int seasons;
    private int runtime;

    @ManyToMany
    @JoinTable(
            name = "actor_series",
            joinColumns = @JoinColumn(name = "series_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actorsInSeries = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_series",
            joinColumns = @JoinColumn(name = "series_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> usersWatchedSeries = new ArrayList<>();;

    private int rating;
}
