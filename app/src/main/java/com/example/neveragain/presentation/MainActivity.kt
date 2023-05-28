package com.example.neveragain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var db:RestaurantDatabase
    var restaurants = ArrayList<Restaurant>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        test()
    }

    fun test() {
        CoroutineScope(Dispatchers.IO).launch {
            getAllRestaurant()
        }
    }

    suspend fun getAllRestaurant() {
        db = RestaurantDatabase.getInstance(this)

        db.clearAllTables()

//        db.restaurantDao().insertRestaurants(
//            Restaurant(1,"매장1", Information("매장1","주소","운영시간","휴무일")),
//            Restaurant(2,"매장2", Information("매장2","주소","운영시간","휴무일"))
//        )
//        db.categoryMenuDao().insertCategoryMenus(
//            CategoryMenu(1,"카테고리1",1),
//            CategoryMenu(2,"카테고리2",1),
//            CategoryMenu(3,"카테고리3",1),
//            CategoryMenu(1,"카테고리1",2),
//            CategoryMenu(2,"카테고리2",2),
//        )
//
//        db.menuDao().insertMenus(
//            Menu(1,"메뉴1","설명",1,true,1000),
//            Menu(2,"메뉴2","설명",2,true,2000),
//            Menu(3,"메뉴3","설명",3,true,3000),
//            Menu(1,"메뉴1","설명",1,true,4000),
//            Menu(2,"메뉴2","설명",2,true,5000),
//        )
//
//        val allMenus = db.menuDao().getAllMenus()
//
//        val allItems = db.restaurantMenuDao().getAllRestaurantMenu()
//
//        allItems?.forEach{
//            val menu = it.menuList
////            menu?.forEach {
////                Log.i("test",it.toString())
////            }
//        }
    }
}