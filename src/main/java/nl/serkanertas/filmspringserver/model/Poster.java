package nl.serkanertas.filmspringserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Poster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long poster_id;

    private String fileName;

    private String type;

    @Lob
    private byte[] data;

    @JsonIgnore
    @OneToOne(mappedBy = "posterFilm")
    private Film film;

    @JsonIgnore
    @OneToOne(mappedBy = "posterSeries")
    private Series series;

    public Poster() {
    }

    public Poster(String fileName, String type, byte[] data) {
        this.fileName = fileName;
        this.type = type;
        this.data = data;
    }

    public long getPoster_id() {
        return poster_id;
    }

    public void setPoster_id(long poster_id) {
        this.poster_id = poster_id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }
}
