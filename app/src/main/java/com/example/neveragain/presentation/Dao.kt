package com.example.neveragain

import androidx.room.*

@Dao
interface DAO{
    @Query("SELECT * FROM category_menu WHERE restaurant_id = :restaurant_id ORDER BY priority")
    suspend fun getCategoryMenuList(restaurant_id: Long): List<CategoryMenu>

    @Query("SELECT * FROM menu WHERE restaurant_id = :restaurant_id AND category = :categoryName ORDER BY priority")
    suspend fun getMenuList(restaurant_id: Long, categoryName: String): List<Menu>
    @Query("SELECT * FROM menu WHERE restaurant_id = :category.restaurant_id AND category = :category.name ORDER BY priority")
    suspend fun getMenuList(category: CategoryMenu): List<Menu>

    @Query("SELECT * FROM price_complex WHERE menu_id = :menu_id ORDER BY priority")
    suspend fun getPriceList(menu_id: Long): List<PriceComplex>

    @Query("SELECT * FROM category_option WHERE menu_id = :menu_id AND is_required = :is_required ORDER BY priority")
    suspend fun getCategoryOptionList(menu_id: Long, is_required: Boolean): List<CategoryOption>

    @Query("SELECT * FROM option WHERE menu_id = :menu_id AND category = :categoryName ORDER BY priority")
    suspend fun getOptionList(menu_id: Long, categoryName: String): List<Option>
    @Query("SELECT * FROM option WHERE menu_id = :category.menu_id AND category = :category.name ORDER BY priority")
    suspend fun getOptionList(category: CategoryOption): List<Option>

    @Query("SELECT * FROM restaurant WHERE restaurant_id IN ( SELECT restaurant_id FROM custom_list WHERE user_id = :user_id AND is_white = :is_white )")
    suspend fun getUserCustomList(user_id: Long, is_white: Boolean): List<Restaurant>

    @Query("SELECT * FROM comment WHERE user_id = :user_id AND restaurant_id = :restaurant_id")
    suspend fun getComment(user_id: Long, restaurant_id: Long): Comment
    @Query("SELECT * FROM comment WHERE user_id = :user_id")
    suspend fun getCommentList(user_id: Long): List<Comment>

    @Query("SELECT * FROM review WHERE user_id = :user_id")
    suspend fun getUserReviewList(user_id: Long): List<Review>
    @Query("SELECT * FROM review WHERE restaurant_id = :restaurant_id")
    suspend fun getRestaurantReviewList(restaurant_id: Long): List<Review>

    @Query("SELECT * FROM order WHERE user_id = :user_id")
    suspend fun getUserOrderList(user_id: Long): List<Review>
    @Query("SELECT * FROM order WHERE restaurant_id = :restaurant_id")
    suspend fun getRestaurantOrderList(restaurant_id: Long): List<Review>

}
