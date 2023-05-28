package com.example.neveragain

import androidx.room.*

data class Information(
    @ColumnInfo("business_name") val businessName: String,
    @ColumnInfo("address") val address: String,
    @ColumnInfo("operating_hour") val operatingHour: String,
    @ColumnInfo("day_off") val dayOff: String
)

data class Location(
    @ColumnInfo("latitude") val latitude: Double,
    @ColumnInfo("longitude") val longitude: Double
)

@Entity(tableName = "restaurant")
data class Restaurant(
    @PrimaryKey(true) val id: Long = 0,
    @ColumnInfo("name") val name: String,
    @Embedded
    @ColumnInfo("information") val information: Information,
    @ColumnInfo("is_onlyone") val isOnlyone: Boolean,
    @ColumnInfo("is_delivery") val isDelivery: Boolean,
    @ColumnInfo("is_takeout") val isTakeout: Boolean,
    @Embedded
    @ColumnInfo("location") val location: Location
)

@Entity(tableName = "image_restaurant",
    primaryKeys = ["restaurant_id", "priority"],
    foreignKeys = [
        ForeignKey(
            entity = Restaurant::class,
            parentColumns = ["id"],
            childColumns = ["restaurant_id"]
        )
    ])
data class ImageRestaurant(
    @ColumnInfo("restaurant_id") val restaurant_id: Long,
    @ColumnInfo("priority") val priority: Int,
    @ColumnInfo("src") val src: String
)

@Entity(tableName = "info_service",
    primaryKeys = ["restaurant_id", "type"],
    foreignKeys = [
        ForeignKey(
            entity = Restaurant::class,
            parentColumns = ["id"],
            childColumns = ["restaurant_id"]
        )
    ])
data class InfoService(
    @ColumnInfo("restaurant_id") val restaurant_id: Long,
    @ColumnInfo("type") val type: Int,
    @ColumnInfo("min_order_price") val minOrderPrice: Int,
)

@Entity(tableName = "delivery_tip_price",
    primaryKeys = ["restaurant_id", "type"],
    foreignKeys = [
        ForeignKey(
            entity = InfoService::class,
            parentColumns = ["restaurant_id","type"],
            childColumns = ["restaurant_id","type"]
        )
    ])
data class DeliveryTipPrice(
    @ColumnInfo("restaurant_id") val restaurant_id: Long,
    @ColumnInfo("type") val type: Int,
    @ColumnInfo("min_price_range") val minPriceRange: Int,
    @ColumnInfo("max_price_range") val maxPriceRange: Int,
    @ColumnInfo("tip") val tip: Int
)

@Entity(tableName = "delivery_tip_location",
    primaryKeys = ["restaurant_id", "type"],
    foreignKeys = [
        ForeignKey(
            entity = InfoService::class,
            parentColumns = ["restaurant_id","type"],
            childColumns = ["restaurant_id","type"]
        )
    ])
data class DeliveryTipLocation(
    @ColumnInfo("restaurant_id") val restaurant_id: Long,
    @ColumnInfo("type") val type: Int,
    @ColumnInfo("location") val location: Int,
    @ColumnInfo("tip") val tip: Int
)

@Entity(tableName = "category_menu",
    primaryKeys = ["restaurant_id", "name"],
    foreignKeys = [
        ForeignKey(
            entity = Restaurant::class,
            parentColumns = ["id"],
            childColumns = ["restaurant_id"]
        )
    ])
data class CategoryMenu(
    @ColumnInfo("restaurant_id") val menu_id: Long,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("priority") val priority: Int,
)

@Entity(tableName = "menu",
    foreignKeys = [
        ForeignKey(
            entity = Restaurant::class,
            parentColumns = ["id"],
            childColumns = ["restaurant_id"]
        ),
        ForeignKey(
            entity = CategoryMenu::class,
            parentColumns = ["name"],
            childColumns = ["category"]
        )
    ])
data class Menu(
    @PrimaryKey(true) val id: Long = 0,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("description") val description: String?,
    @ColumnInfo("is_price_simple") val isPriceSimple: Boolean,
    @ColumnInfo("price_simple") val priceSimple: Int?,
    @ColumnInfo("priority") val priority: Int,
    @ColumnInfo("restaurant_id") val restaurant_id: Long,
    @ColumnInfo("category") val category: String?,
)

@Entity(tableName = "image_menu",
    primaryKeys = ["menu_id", "priority"],
    foreignKeys = [
        ForeignKey(
            entity = Menu::class,
            parentColumns = ["id"],
            childColumns = ["menu_id"]
        )
    ])
data class ImageMenu(
    @ColumnInfo("menu_id") val menu_id: Long,
    @ColumnInfo("priority") val priority: Int,
    @ColumnInfo("src") val src: String
)

@Entity(tableName = "price_complex",
    primaryKeys = ["menu_id", "name"],
    foreignKeys = [
        ForeignKey(
            entity = Menu::class,
            parentColumns = ["id"],
            childColumns = ["menu_id"]
        )
    ])
data class PriceComplex(
    @ColumnInfo("menu_id") val menu_id: Long,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("price") val price: Int,
    @ColumnInfo("priority") val priority: Int,
)

@Entity(tableName = "category_option",
    primaryKeys = ["menu_id", "name"],
    foreignKeys = [
        ForeignKey(
            entity = Menu::class,
            parentColumns = ["id"],
            childColumns = ["menu_id"]
        )
    ])
data class CategoryOption(
    @ColumnInfo("menu_id") val menu_id: Long,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("is_required") val isRequired: Boolean,
    @ColumnInfo("priority") val priority: Int,
)

@Entity(tableName = "option",
    foreignKeys = [
        ForeignKey(
            entity = CategoryOption::class,
            parentColumns = ["menu_id","name"],
            childColumns = ["menu_id","category"]
        )
    ])
data class Option(
    @PrimaryKey(true) val id: Long = 0,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("price") val price: Int,
    @ColumnInfo("priority") val priority: Int,
    @ColumnInfo("menu_id") val menu_id: Long,
    @ColumnInfo("category") val category: String?,
)
