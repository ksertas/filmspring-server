package nl.serkanertas.filmspringserver.model;

import javax.persistence.*;

@Entity
@Table(name = "series_ratings")
public class SeriesRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rating_id;

    @ManyToOne
    @JoinColumn(name = "username")
    private User username;

    @Column(name = "rating")
    private int rating;

    @ManyToOne
    @JoinColumn(name = "series")
    private Series series;

    public long getRating_id() {
        return rating_id;
    }

    public void setRating_id(long rating_id) {
        this.rating_id = rating_id;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }
}
