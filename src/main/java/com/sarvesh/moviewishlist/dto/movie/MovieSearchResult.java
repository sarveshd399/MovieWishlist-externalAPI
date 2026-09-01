package com.sarvesh.moviewishlist.dto.movie;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieSearchResult {
    private String title;
    private String year;
    private String imdbId;
    private String poster;
}
