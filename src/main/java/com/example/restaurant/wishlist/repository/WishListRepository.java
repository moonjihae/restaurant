package com.example.restaurant.wishlist.repository;

import com.example.restaurant.wishlist.domain.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishList,Long> {
}
