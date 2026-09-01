package com.sarvesh.moviewishlist.repository;

import com.sarvesh.moviewishlist.entity.User;
import com.sarvesh.moviewishlist.entity.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepository extends JpaRepository<WishlistItem, Long> {
    List<WishlistItem> findByUser(User user);
    boolean existsByUserAndImdbId(User user, String imdbId);
}
