package com.example.neveragain

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [
    Restaurant::class, ImageRestaurant::class, InfoService::class, DeliveryTipPrice::class, DeliveryTipLocation::class,
    CategoryMenu::class, Menu::class, ImageMenu::class, PriceComplex::class,
    CategoryOption::class, Option::class,
    User::class,
    CustomList::class, Comment::class,
    Review::class, Order::class,
    OrderedMenu::class, OrderedOption::class], version=1)
abstract class RestaurantDatabase: RoomDatabase() {
    abstract fun DAO(): DAO

    companion object{
        @Volatile private var INSTANCE: RestaurantDatabase? = null

        fun getInstance(context: Context): RestaurantDatabase {
            val tempInstance: RestaurantDatabase? = INSTANCE

            if(tempInstance != null){
                return tempInstance
            }

            val instance = buildDatabase(context)

            INSTANCE = instance
            return instance
        }

        private fun buildDatabase(context: Context): RestaurantDatabase{
            return Room.databaseBuilder(context, RestaurantDatabase::class.java, "restaurant-db").build()
        }
    }

}