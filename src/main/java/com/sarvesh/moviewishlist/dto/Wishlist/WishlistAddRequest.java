package com.sarvesh.moviewishlist.dto.Wishlist;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class WishlistAddRequest {
    @NotBlank
    private String imdbId;

    @NotBlank
    private String title;

    private String posterUrl;
}
