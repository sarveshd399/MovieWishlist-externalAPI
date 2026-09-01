package com.sarvesh.moviewishlist.controller;

import com.sarvesh.moviewishlist.dto.Wishlist.WishlistAddRequest;
import com.sarvesh.moviewishlist.dto.Wishlist.WishlistItemResponse;
import com.sarvesh.moviewishlist.entity.WishlistItem;
import com.sarvesh.moviewishlist.repository.WishlistRepository;
import com.sarvesh.moviewishlist.service.WishlistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
public class WishlistController {
    private final WishlistRepository wishlistRepository;
    private final WishlistService wishlistService;

    @PostMapping
    public ResponseEntity<WishlistItemResponse> add(@Valid @RequestBody WishlistAddRequest request, Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(wishlistService.addToWishlist(username, request));
    }

    @GetMapping
    public ResponseEntity<List<WishlistItemResponse>> getAll(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(wishlistService.getWishlist(username));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> remove(@PathVariable Long id, Authentication authentication) throws Throwable {
        wishlistService.removeFromWishlist(authentication.getName(), id);
        return ResponseEntity.noContent().build();
    }
}
