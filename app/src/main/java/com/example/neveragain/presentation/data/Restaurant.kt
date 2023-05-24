package com.example.neveragain.data

import java.io.Serializable

data class Restaurant(val ID: Long,
                      val name:String,
                      var menu_list : ArrayList<Menu>,
                      val phone_call : String,
                      var BlackList : Boolean,
                      var WhiteList: Boolean,
                      val tip: ArrayList<Pair<Int,Int>>,
                 //val image :
                      val category: String,
                      val coordX : Double,
                      val coordY : Double) : Serializable//이미지 추가해아함

