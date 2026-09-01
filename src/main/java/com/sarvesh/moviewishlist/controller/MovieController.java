package com.sarvesh.moviewishlist.controller;

import com.sarvesh.moviewishlist.dto.movie.MovieSearchResult;
import com.sarvesh.moviewishlist.dto.omdb.OmdbMovieDetails;
import com.sarvesh.moviewishlist.dto.omdb.OmdbSearchResponse;
import com.sarvesh.moviewishlist.service.MovieApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieApiService movieApiService;

    @GetMapping("/search")
    public ResponseEntity<List<MovieSearchResult>> search(@RequestParam String query){
        OmdbSearchResponse response = movieApiService.searchMovie(query);

        if(response.getResults() == null){
            return ResponseEntity.ok(Collections.emptyList());
        }

        List<MovieSearchResult> results = response.getResults().stream()
                .map(item ->new MovieSearchResult(item.getTitle(), item.getYear(), item.getImdbID(), item.getPoster())
                )
                .toList();

        return ResponseEntity.ok(results);
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<OmdbMovieDetails> searchMovie(
            @PathVariable String imdbId) {

        return ResponseEntity.ok(
                movieApiService.getMovieDetails(imdbId)
        );
    }
}
