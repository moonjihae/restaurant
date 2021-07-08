package com.example.restaurant.wishlist.service;

import com.example.restaurant.naver.dto.SearchLocalReq;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class WishListServiceTest {

    @Autowired
    private WishListService wishListService;

    @Test
    public void searchTest(){

        var search=new SearchLocalReq();
        search.setQuery("토스트");
        var result=wishListService.search("토스트");
        System.out.println(result);
        Assertions.assertNotNull(result.getCategory());
    }

    @Test
    public void searchImageTest(){

    }

}