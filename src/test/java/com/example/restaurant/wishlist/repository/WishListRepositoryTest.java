package com.example.restaurant.wishlist.repository;

import com.example.restaurant.wishlist.domain.WishList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WishListRepositoryTest {
    @Autowired
    private WishListRepository wishListRepository;

    private WishList givenWishList(String title){
        var wishList=new WishList();
        wishList.setTitle(title);
        wishList.setCategory("category");
        wishList.setAddress("address");
        wishList.setRoadAddress("roadAddress");
        wishList.setHomePageLink("");
        wishList.setVisit(false);
        wishList.setVisitCount(0);
        wishList.setLastVisitDate(null);
        return wishListRepository.save(wishList);

    }
    @Test
    public void saveTest(){
        var expected=givenWishList("맛집1");
        Assertions.assertNotNull(expected);
        Assertions.assertEquals(1,expected.getId());
    }

    @Test
    public void findByIdTest(){
        givenWishList("맛집1");
        var expected=wishListRepository.findById(1L);

        Assertions.assertEquals(true,expected.isPresent());
        Assertions.assertEquals(1,expected.get().getId());
    }

    @Test
    public  void deleteTest(){
        givenWishList("맛집1");
        wishListRepository.deleteById(1L);
        int count=wishListRepository.findAll().size();

        Assertions.assertEquals(0,count);

    }

    @Test
    public void listAllTest(){
        givenWishList("맛집1");
        givenWishList("맛집2");
        givenWishList("맛집3");
        int count=wishListRepository.findAll().size();

        Assertions.assertEquals(3,count);
    }

    @Test
    public void UpdateTest(){
        var expected=givenWishList("맛집1");
        expected.setTitle("update test");
        var save=wishListRepository.save(expected);

        Assertions.assertEquals("update test",save.getTitle());
        Assertions.assertEquals(1,wishListRepository.findAll().size());
    }
}