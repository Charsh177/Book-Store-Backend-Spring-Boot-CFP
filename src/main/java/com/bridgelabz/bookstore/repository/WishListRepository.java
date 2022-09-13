package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.entity.MyWishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface WishListRepository extends JpaRepository <MyWishList,Integer> {

    @Query(value="select * from wish_list where wishlist_id =:id",nativeQuery = true)
    public List<MyWishList> findByWishlistId(Integer id);

    @Query(value="select * from wish_list where book_id =:bookId",nativeQuery = true)
    public List<MyWishList> findByBookId(Integer bookId);

    @Query(value="select * from wish_list where user_id =:userId",nativeQuery = true)
    public List<MyWishList> findByUserId(Integer userId);
}