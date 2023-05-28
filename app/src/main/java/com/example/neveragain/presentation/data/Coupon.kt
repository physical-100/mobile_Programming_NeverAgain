package com.example.neveragain.data

data class Coupon(val condition: Int,
                val discount: Int,
                val validDate : String,
                val isUsed: Boolean)
