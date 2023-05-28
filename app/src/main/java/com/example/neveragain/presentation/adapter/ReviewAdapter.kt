package com.example.neveragain.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neveragain.data.Ordered
import com.example.neveragain.data.Review
import com.example.neveragain.data.Restaurant
import com.example.neveragain.databinding.ReviewRowBinding

class ReviewAdapter(val reviews: ArrayList<Review>, val restaurants:ArrayList<Restaurant>, val orderedList: ArrayList<Ordered>):RecyclerView.Adapter<ReviewAdapter.ViewHolder>(){
    interface OnItemClickListener{
        fun onItemClick(data : Review)
        fun onButtonClick(position: Int)
        fun onTextClick(data : Review)

    }
    var itemClickListener: OnItemClickListener?= null
    inner class ViewHolder(val binding : ReviewRowBinding):RecyclerView.ViewHolder(binding.root){
        init{
            binding.apply{
                textView.setOnClickListener{
                    itemClickListener?.onItemClick(reviews[adapterPosition])
                }
                deleteBtn.setOnClickListener {
                    itemClickListener?.onButtonClick(adapterPosition)
                }
                orderedMenu.setOnClickListener{
                    itemClickListener?.onTextClick(reviews[adapterPosition])
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ReviewRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            val store = restaurants.find{it.ID == reviews[position].store_id}
            textView.text = store?.name.toString()
            orderedDate.text = orderedList.find{it.store_id == reviews[position].store_id}?.date.toString()
            //imageView6.setImageResource()
            context.text = reviews[position].content


            var str = ""
            for(menu in store!!.menu_list)
                if(menu.id in reviews[position].menu_id)
                     str += menu.name
            orderedMenu.text = str
            orderedDate.text = orderedList.find {it.id == reviews[position].id}?.date
            val stars = arrayListOf(imageView,imageView2,imageView3,imageView4,imageView5)
            for(i in 0..reviews[position].food_satisfaction-1){
                stars[i].setImageResource(android.R.drawable.btn_star_big_on)
            }
        }
    }

    override fun getItemCount(): Int {
        return reviews.size
    }
}