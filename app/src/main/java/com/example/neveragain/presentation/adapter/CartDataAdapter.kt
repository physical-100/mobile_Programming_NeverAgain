package com.example.neveragain.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neveragain.databinding.CartfoodrowBinding
import com.example.neveragain.presentation.dataclass.FoodMenu

class CartDataAdapter(val orderedItems:ArrayList<FoodMenu>)
    : RecyclerView.Adapter<CartDataAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: CartfoodrowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): ViewHolder {
        val view = CartfoodrowBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return orderedItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.cartFoodName.text = orderedItems[position].menuName
        holder.binding.cartFoodPrice.text = orderedItems[position].menuPrice.toString()
        var intTotalPrice = 0
        orderedItems.forEach {
            intTotalPrice += it.menuPrice
        }
        holder.binding.cartFoodTotalPrice.text = intTotalPrice.toString()
        holder.binding.deleteBtn.setOnClickListener {
            orderedItems.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}