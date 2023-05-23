package com.example.neveragain

import androidx.room.*

@Entity(tableName = "user")
data class User(
    @PrimaryKey(true) val id: Long=0,
    @ColumnInfo("name") val name: String
)

@Entity(tableName = "custom_list",
    primaryKeys = ["user_id","restaurant_id","is_white"],
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"]
        ),
        ForeignKey(
            entity = Restaurant::class,
            parentColumns = ["id"],
            childColumns = ["restaurant_id"]
        )
    ])
data class CustomList(
    @ColumnInfo("user_id") val user_id: Long,
    @ColumnInfo("restaurant_id") val restaurant_id: Long,
    @ColumnInfo("is_white") val isWhite: Boolean,
)

@Entity(tableName = "comment",
    primaryKeys = ["user_id","restaurant_id"],
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"]
        ),
        ForeignKey(
            entity = Restaurant::class,
            parentColumns = ["id"],
            childColumns = ["restaurant_id"]
        )
    ])
data class Comment(
    @ColumnInfo("user_id") val user_id: Long,
    @ColumnInfo("restaurant_id") val restaurant_id: Long,
    @ColumnInfo("text") val text: String,
)

@Entity(tableName = "review",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"]
        ),
        ForeignKey(
            entity = Restaurant::class,
            parentColumns = ["id"],
            childColumns = ["restaurant_id"]
        ),
        ForeignKey(
            entity = Order::class,
            parentColumns = ["id"],
            childColumns = ["order_id"]
        )
    ])
data class Review(
    @PrimaryKey(true) val order_id: Long,
    @ColumnInfo("grade") val grade: Int,
    @ColumnInfo("text") val text: String,
    @ColumnInfo("user_id") val user_id: Long,
    @ColumnInfo("restaurant_id") val restaurant_id: Long
)

@Entity(tableName = "order",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"]
        ),
        ForeignKey(
            entity = Restaurant::class,
            parentColumns = ["id"],
            childColumns = ["restaurant_id"]
        )
    ])
data class Order(
    @PrimaryKey(true) val id: Long=0,
    @ColumnInfo("user_id") val user_id: Long,
    @ColumnInfo("restaurant_id") val restaurant_id: Long,
    @ColumnInfo("service_type") val serviceType: Int,
)

@Entity(tableName = "ordered_menu",
    foreignKeys = [
        ForeignKey(
            entity = Order::class,
            parentColumns = ["id"],
            childColumns = ["order_id"]
        )
    ])
data class OrderedMenu(
    @PrimaryKey(true) val id: Long=0,
    @ColumnInfo("order_id") val order_id: Long,
    @Embedded
    @ColumnInfo("menu") val menu: Menu,
    @ColumnInfo("count") val count: Int,
)

@Entity(tableName = "ordered_option",
    foreignKeys = [
        ForeignKey(
            entity = OrderedMenu::class,
            parentColumns = ["id"],
            childColumns = ["ordered_menu_id"]
        )
    ])
data class OrderedOption(
    @PrimaryKey(true) val id: Long=0,
    @ColumnInfo("ordered_menu_id") val ordered_menu_id: Long,
    @Embedded
    @ColumnInfo("option") val menu: Option,
)