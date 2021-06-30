package com.example.restaurant.wishlist.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WishList {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String category;

    private String address;

    private String roadAddress; //도로명 주소

    private String homePageLink;

    private String imageLink;

    private boolean isVisit;

    private int visitCount;

    private LocalDateTime lastVisitDate;

}
