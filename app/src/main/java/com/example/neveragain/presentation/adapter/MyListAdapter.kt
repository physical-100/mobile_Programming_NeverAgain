package com.example.neveragain.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.neveragain.data.Restaurant
import com.example.neveragain.databinding.StoreRowBinding
import java.util.*
import kotlin.collections.ArrayList

class MyListAdapter(val items : ArrayList<Restaurant>, val wb:String):RecyclerView.Adapter<MyListAdapter.ViewHolder>(), Filterable {
    interface OnItemClickListener{
        fun onItemClick(data : Restaurant)
    }

    var filtereditems = ArrayList<Restaurant>()
    var itemFilter = ItemFilter()

    override fun getFilter(): Filter {
        return itemFilter
    }
    inner class ItemFilter : Filter(){
        override fun performFiltering(p0: CharSequence): FilterResults {
            val line = p0.toString()
            var filter = line.split(",")
            val t = filter[1].replace("[^0-9]".toRegex(),"")

            val result = FilterResults()
            val filteredList = ArrayList<Restaurant>()

            filtereditems.clear()
            /**     check White/Black List      **/
            if(wb == "White") {
                for (item in items)
                    if (item.WhiteList) filtereditems.add(item)
            }else if(wb == "Black") {
                for (item in items)
                    if (item.BlackList) filtereditems.add(item)
            }
            /**     category filter     **/
            if(filter[0] == "전체"){
                filteredList.addAll(filtereditems)
            }else {
                for (item in filtereditems) {
                    if (item.category == filter[0])
                        filteredList.add(item)
                }
            }
            /**         여기서부터는 전체에서 빼는 방식       **/
            /**     tip filter      **/
            if(t.length == 0) {
                //do nothing
            }else {
                for (item in filteredList.reversed()) {
                    if (item.tip.last().second > t.toInt()) { //팁 저장하는 순서에 따라 바꿔야 함
                        filteredList.remove(item)
                    }
                }
            }
            TODO("Data class")
                /*  여기는 data class 상의하고 설정
          /**     address filter      **/

            if(filter[2] == "전체"){
                //do nothing
            }else {
                for (item in filteredList) {
                    if (item.    .second >= filter[1].toInt()) //
                        filteredList.remove(item)
                }
            }*/

            result.values = filteredList
            result.count = filteredList.size
            return result
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults) {
            filtereditems.clear()
            filtereditems.addAll(p1.values as ArrayList<Restaurant>)
            notifyDataSetChanged()
        }

    }
    var itemClickListener: OnItemClickListener?= null
    inner class ViewHolder(val binding : StoreRowBinding):RecyclerView.ViewHolder(binding.root){
        init{
            binding.root.setOnClickListener{
                itemClickListener?.onItemClick(items[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = StoreRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return filtereditems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            price.text = filtereditems[position].name
            storeName.text = filtereditems[position].name
            orderedMenu.text = filtereditems[position].menu_list[0].name
            myComment.text = filtereditems[position].name
            category.text = filtereditems[position].category
        }
    }

    fun moveItem(adapterPosition: Int, adapterPosition1: Int) {
        Collections.swap(items,adapterPosition ,adapterPosition1)

        notifyItemMoved(adapterPosition,adapterPosition1)
    }

    fun removeItem(adapterPosition: Int) {
        Log.e("items", "begin remove ${items}")
        when(wb){
            "White" -> {
                items.find { it == filtereditems[adapterPosition] }?.WhiteList = false
                filtereditems.removeAt(adapterPosition)
            }
            "Black" -> {
                items.find { it == filtereditems[adapterPosition] }?.BlackList = false
                filtereditems.removeAt(adapterPosition)
            }
        }
    }



}