package com.example.neveragain.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.neveragain.data.Menu
import com.example.neveragain.data.Ordered
import com.example.neveragain.data.Restaurant
import com.example.neveragain.databinding.OrderedRowBinding
import kotlin.collections.ArrayList

class OrderedAdapter(val orders : ArrayList<Ordered>, val restaurants : ArrayList<Restaurant>, val menus : ArrayList<Menu>):RecyclerView.Adapter<OrderedAdapter.ViewHolder>(), Filterable {
    interface OnItemClickListener{
        fun onTextClick(data : Ordered)
        fun onButtonClick(data : Ordered)
        fun onReviewClick(data : Ordered)
    }

    var filteredorders = ArrayList<Ordered>()
    var itemFilter = ItemFilter()
    init{
        filteredorders.addAll(orders)
    }
    override fun getFilter(): Filter {
        return itemFilter
    }
    inner class ItemFilter : Filter(){
        override fun performFiltering(p0: CharSequence): FilterResults {
            val line = p0.toString()

            val result = FilterResults()
            val filteredList = ArrayList<Ordered>()

            /**     Filtering by search      **/
            if(line.replace("\\s".toRegex(), "").isEmpty()) {

                result.values = orders
                result.count = orders.size
                return result
            }else{
                for(order in orders){
                    if(restaurants.find { it.ID == order.store_id}!!.name.contains(line))
                        filteredList.add(order)
                }
            }

            result.values = filteredList
            result.count = filteredList.size
            return result
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults) {
            filteredorders.clear()
            filteredorders.addAll(p1.values as ArrayList<Ordered>)
            notifyDataSetChanged()
        }

    }
    var itemClickListener: OnItemClickListener?= null
    inner class ViewHolder(val binding : OrderedRowBinding):RecyclerView.ViewHolder(binding.root){
        init{
            binding.apply{
                storeName.setOnClickListener {itemClickListener?.onReviewClick(orders[adapterPosition])}
                reviewbtn.setOnClickListener {itemClickListener?.onButtonClick(orders[adapterPosition])}
                orderbtn.setOnClickListener {itemClickListener?.onTextClick(orders[adapterPosition])}
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = OrderedRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filteredorders.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            orderedDate.text = filteredorders[position].date
            delivery.text = if(filteredorders[position].delivery) "배달완료" else "픽업완료"
            storeName.text = restaurants.find { it.ID == filteredorders[position].store_id }?.name
            val str : String
            val menu = menus.find{it.id == filteredorders[position].menu[0].first}!!
            if(filteredorders[position].menu.size == 1) {

                str = "${menu.name} ${filteredorders[position].menu[0].second}+개 ${menu.price * filteredorders[position].menu[0].second} 원"
            }else{
                var count = 0
                var price = 0
                filteredorders[position].menu.forEach { t->
                    count += t.second
                    price += menus.find{it.id == t.first}!!.price * t.second
                }
                str = "${menu.name} 외 ${count -1}+개 ${price} 원"
            }
            context.text = str
        }
    }
}