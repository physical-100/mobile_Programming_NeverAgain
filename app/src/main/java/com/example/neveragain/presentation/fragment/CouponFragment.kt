package com.example.neveragain.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neveragain.data.Coupon
import com.example.neveragain.adapter.CouponAdapter
import com.example.neveragain.databinding.FragmentCouponBinding

class CouponFragment : Fragment() {
    var binding:FragmentCouponBinding?= null
    var adapter : CouponAdapter?= null
    val coupons: ArrayList<Coupon> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCouponBinding.inflate(layoutInflater,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initRecyclerview()
    }

    private fun initData() {
        TODO("쿼리문이랑 데이터 클래스 보고 수정")
        coupons.add(Coupon(3000,1000,"2023-05-15",false))
        coupons.add(Coupon(2000,500,"2023-05-20",false))
        coupons.add(Coupon(5000,3000,"2023-05-21",true))
    }

    private fun initRecyclerview() {
        binding!!.recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL, false
        )
        adapter = CouponAdapter(coupons)
        binding!!.recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}