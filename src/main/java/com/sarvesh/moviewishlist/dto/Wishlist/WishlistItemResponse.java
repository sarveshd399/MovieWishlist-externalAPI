package com.sarvesh.moviewishlist.dto.Wishlist;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class WishlistItemResponse {
    private Long id;
    private String title;
    private String imdbId;
    private String posterUrl;
    private LocalDateTime addedAt;
}
