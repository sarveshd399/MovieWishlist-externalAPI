package com.sarvesh.moviewishlist.service;

import com.sarvesh.moviewishlist.dto.Wishlist.WishlistAddRequest;
import com.sarvesh.moviewishlist.dto.Wishlist.WishlistItemResponse;
import com.sarvesh.moviewishlist.entity.User;
import com.sarvesh.moviewishlist.entity.WishlistItem;
import com.sarvesh.moviewishlist.repository.UserRepository;
import com.sarvesh.moviewishlist.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;

    public WishlistItemResponse addToWishlist(String username, WishlistAddRequest request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        boolean alreadyExists = wishlistRepository.existsByUserAndImdbId(user, request.getImdbId());
        if (alreadyExists) {
            throw new IllegalArgumentException("Movie already in wishlist");
        }

        WishlistItem item = new WishlistItem();
        item.setImdbId(request.getImdbId());
        item.setTitle(request.getTitle());
        item.setPosterUrl(request.getPosterUrl());
        item.setUser(user);
        item.setAddedAt(LocalDateTime.now());

        wishlistRepository.save(item);

        return new WishlistItemResponse(item.getId(), item.getImdbId(), item.getTitle(), item.getPosterUrl(), item.getAddedAt());
    }

    public List<WishlistItemResponse> getWishlist(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return wishlistRepository.findByUser(user).stream()
                .map(item -> new WishlistItemResponse(item.getId(), item.getImdbId(), item.getTitle(), item.getPosterUrl(), item.getAddedAt()))
                .toList();
    }

    public void removeFromWishlist(String username, Long itemId) throws Throwable {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        WishlistItem item = wishlistRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));

        if (!item.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("Not your wishlist item");
        }

        wishlistRepository.delete(item);
    }
}