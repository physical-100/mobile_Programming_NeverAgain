package com.example.neveragain.data

/**
 * id는 Review와 공유
 */
data class Ordered(val id : Long,
                       val store_id:Long,
                        val menu: ArrayList<Pair<Long,Int>>, //menu_id, 주문 수량
                        val location: String,
                        //val coupon:
                        val date: String,
                        val delivery: Boolean)
