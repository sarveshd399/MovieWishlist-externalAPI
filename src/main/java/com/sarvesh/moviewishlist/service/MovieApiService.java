package com.sarvesh.moviewishlist.service;

import com.sarvesh.moviewishlist.config.OmdbProperties;
import com.sarvesh.moviewishlist.dto.omdb.OmdbMovieDetails;
import com.sarvesh.moviewishlist.dto.omdb.OmdbSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class MovieApiService {
    private final RestClient omdbRestClient;
    private final OmdbProperties omdbProperties;

    public OmdbSearchResponse searchMovie(String query) {
        return omdbRestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("apikey", omdbProperties.getKey())
                        .queryParam("s", query)
                        .build())
                .retrieve()
                .body(OmdbSearchResponse.class);
    }

    public OmdbMovieDetails getMovieDetails(String imdbID) {
        return omdbRestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("apikey",omdbProperties.getKey())
                        .queryParam("i",imdbID)
                        .build())
                .retrieve()
                .body(OmdbMovieDetails.class);
    }
}
