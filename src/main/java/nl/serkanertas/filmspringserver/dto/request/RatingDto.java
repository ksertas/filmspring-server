package nl.serkanertas.filmspringserver.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class RatingDto {
    @Min(0)
    @Max(5)
    private int rating;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
