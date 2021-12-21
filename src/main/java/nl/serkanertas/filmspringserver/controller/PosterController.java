package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.service.PosterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/posters")
public class PosterController {

    private final PosterService posterService;

    public PosterController(PosterService posterService) {
        this.posterService = posterService;
    }

    @GetMapping("/films/{film_id}")
    public ResponseEntity<Object> getPosterFilm(@PathVariable long film_id) {
        return ResponseEntity.ok(posterService.getPosterFilm(film_id));
    }

    @PutMapping("/films/{film_id}")
    public ResponseEntity<Object> uploadPosterFilm(@RequestParam("file") MultipartFile file,
                                                   @PathVariable long film_id) {
        try {
            posterService.storePosterFilm(film_id, file);
            return ResponseEntity.status(HttpStatus.OK).body("Success");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }
    }

    @DeleteMapping("/films/{film_id}")
    public ResponseEntity<Object> deletePosterUser(@PathVariable long film_id) {
        posterService.deletePosterFilm(film_id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/series/{series_id}")
    public ResponseEntity<Object> getPosterSeries(@PathVariable long series_id) {
        return ResponseEntity.ok(posterService.getPosterSeries(series_id));
    }

    @PutMapping("/series/{series_id}")
    public ResponseEntity<Object> uploadPosterSeries(@RequestParam("file") MultipartFile file,
                                                    @PathVariable long series_id) {
        try {
            posterService.storePosterSeries(series_id, file);
            return ResponseEntity.status(HttpStatus.OK).body("Success");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }
    }

    @DeleteMapping("/series/{series_id}")
    public ResponseEntity<Object> deletePosterSeries(@PathVariable long  series_id) {
        posterService.deletePosterSeries(series_id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
