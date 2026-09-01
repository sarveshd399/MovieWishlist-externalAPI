package com.sarvesh.moviewishlist.dto.omdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OmdbSearchResponse {

    @JsonProperty("Search")
    private List<OmdbSearchItem> results;

    @JsonProperty("totalResults")
    private String totalResults;

    private String Response;
}
