package com.example.neveragain.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neveragain.data.Coupon
import com.example.neveragain.databinding.CouponRowBinding
import java.time.LocalDate

class CouponAdapter(val coupons : ArrayList<Coupon>):RecyclerView.Adapter<CouponAdapter.ViewHolder>() {
    val filteredcoupons = ArrayList<Coupon>()

    init{
        for(i in coupons){
            val valid = !i.isUsed &&
                    i.validDate.replace("\\D".toRegex(),"") >= LocalDate.now().toString().replace("\\D".toRegex(),"")
            if(valid)
                filteredcoupons.add(i)

        }
    }
    inner class ViewHolder(val binding: CouponRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = CouponRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filteredcoupons.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            "최소 주문 금액 : ${filteredcoupons[position].condition.toString()}원".also { condition.text = it }
            "사용 기한 : ${filteredcoupons[position].validDate}".also { validdate.text = it }
            "${filteredcoupons[position].discount.toString()}원".also { discount.text = it }
        }
    }
}