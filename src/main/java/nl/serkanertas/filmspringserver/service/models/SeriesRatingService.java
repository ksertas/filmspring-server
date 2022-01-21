package nl.serkanertas.filmspringserver.service.models;

import nl.serkanertas.filmspringserver.dto.request.RatingDto;
import nl.serkanertas.filmspringserver.model.*;
import nl.serkanertas.filmspringserver.repository.SeriesRatingRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class SeriesRatingService {
    private final UserService userService;
    private final SeriesService seriesService;
    private final SeriesRatingRepository seriesRatingRepository;

    public SeriesRatingService(@Lazy UserService userService,
                               @Lazy SeriesService seriesService,
                               @Lazy SeriesRatingRepository seriesRatingRepository) {
        this.userService = userService;
        this.seriesService = seriesService;
        this.seriesRatingRepository = seriesRatingRepository;
    }

    public void addSeriesRating(String user_id, long series_id, RatingDto ratingDto) {
        int rating = ratingDto.getRating();
        User user = userService.getUserEntity(user_id);
        Series series = seriesService.getSeriesEntity(series_id);
        SeriesRating seriesRating = new SeriesRating();
        seriesRating.setUsername(user);
        seriesRating.setSeries(series);
        seriesRating.setRating(rating);
        seriesRatingRepository.save(seriesRating);
    }

    public int getSeriesRating(String user_id, long series_id) {
        SeriesRating seriesRating = getSeriesRatingEntity(user_id, series_id);
        if (seriesRating != null) {
            return seriesRating.getRating();
        }
        return -1;
    }

    public SeriesRating getSeriesRatingEntity(String user_id, long series_id) {
        User user = userService.getUserEntity(user_id);
        Series series = seriesService.getSeriesEntity(series_id);
        return seriesRatingRepository.findRatingBySeriesAndUsername(series, user);
    }

    public void deleteSeriesRating(String user_id, long series_id) {
        SeriesRating seriesRating = getSeriesRatingEntity(user_id, series_id);
        seriesRatingRepository.delete(seriesRating);
    }

    public int updateSeriesRating(String user_id, long series_id, RatingDto ratingDto) {
        SeriesRating seriesRating = getSeriesRatingEntity(user_id, series_id);
        seriesRating.setRating(ratingDto.getRating());
        seriesRatingRepository.save(seriesRating);
        return seriesRating.getRating();
    }
}
