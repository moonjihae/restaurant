package com.example.restaurant.wishlist.service;

import com.example.restaurant.naver.NaverClient;
import com.example.restaurant.naver.dto.SearchImageReq;
import com.example.restaurant.naver.dto.SearchLocalReq;
import com.example.restaurant.wishlist.domain.WishList;
import com.example.restaurant.wishlist.dto.WishListDto;
import com.example.restaurant.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListService {
    private final NaverClient naverClient;

    private final WishListRepository wishListRepository;

    public WishListDto search(String query){
        //지역검색
        var searchLocalReq=new SearchLocalReq();
        searchLocalReq.setQuery(query);

        var searchLocalRes =naverClient.searchLocal(searchLocalReq);

        if (searchLocalRes.getTotal()>0){
            var localitem=searchLocalRes.getItems().stream().findFirst().get();
            //이미지 검색
            var imageQuery=localitem.getTitle().replaceAll("<[^>]*>","");
            var searchImageReq=new SearchImageReq();
            searchImageReq.setQuery(imageQuery);

            var searchImageRes=naverClient.searchImage(searchImageReq);

            if(searchImageRes.getTotal()>0){
                var imageItem=searchImageRes.getItems().stream().findFirst().get();
                //결과 리턴
                var result=new WishListDto();
                result.setTitle(imageItem.getTitle());
                result.setCategory(localitem.getCategory());
                result.setAddress(localitem.getAddress());
                result.setRoadAddress(localitem.getRoadAddress());
                result.setHomePageLink(localitem.getLink());
                result.setImageLink(imageItem.getLink());

                return result;
            }
        }
        return new WishListDto();
    }

    public WishListDto add(WishListDto wishListDto){
        //db에 저장
        var entity=dtoToEntity(wishListDto);
        var saveEntity=wishListRepository.save(entity);
        return entityTODto(saveEntity);

    }

    private WishList dtoToEntity(WishListDto wishListDto){
        var entity=new WishList();
        entity.setId(wishListDto.getId());
        entity.setTitle(wishListDto.getTitle());
        entity.setCategory(wishListDto.getCategory());
        entity.setAddress(wishListDto.getAddress());
        entity.setRoadAddress(wishListDto.getRoadAddress());
        entity.setHomePageLink(wishListDto.getHomePageLink());
        entity.setVisitCount(wishListDto.getVisitCount());
        entity.setImageLink(wishListDto.getImageLink());
        entity.setVisit(wishListDto.isVisit());
        entity.setLastVisitDate(wishListDto.getLastVisitDate());
        return entity;
    }

    private WishListDto entityTODto(WishList wishList){
        var dto=new WishListDto();
        dto.setId(wishList.getId());
        dto.setTitle(wishList.getTitle());
        dto.setCategory(wishList.getCategory());
        dto.setAddress(wishList.getAddress());
        dto.setRoadAddress(wishList.getRoadAddress());
        dto.setHomePageLink(wishList.getHomePageLink());
        dto.setVisitCount(wishList.getVisitCount());
        dto.setImageLink(wishList.getImageLink());
        dto.setVisit(wishList.isVisit());
        dto.setLastVisitDate(wishList.getLastVisitDate());
        return dto;
    }

    public List<WishListDto> findAll(){

        return wishListRepository.findAll()
                .stream()
                .map(it->entityTODto(it))
                .collect(Collectors.toList());
    }
}


