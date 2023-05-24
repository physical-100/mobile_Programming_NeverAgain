package com.example.neveragain.data

/**
 * id는 ordered와 공유
 */
data class Review(val id: Long,
                  val store_id: Long,
                  val menu_id: ArrayList<Int>,
                  val isComment: Boolean,
                  val food_satisfaction : Short,
                  val delivery_satisfaction : Boolean,
                  val content: String)
