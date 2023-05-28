package com.example.neveragain.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neveragain.R
import com.example.neveragain.databinding.FragmentCartBinding
import com.example.neveragain.presentation.adapter.CartDataAdapter
import com.example.neveragain.presentation.dataclass.FoodMenu


class CartFragment : Fragment() {

    lateinit var binding: FragmentCartBinding
    lateinit var adapter: CartDataAdapter
    var tempList = ArrayList<FoodMenu>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // @ fragment에서 바인딩할때 작성하는 법 주의
        binding = FragmentCartBinding.bind(view)
        initButton()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL ,false)
        adapter = CartDataAdapter(tempList)

        // @ 테스트를 위한 임시 생성 값
        tempList.add(FoodMenu("피자", 5000))
        tempList.add(FoodMenu("라면", 3000))
        tempList.add(FoodMenu("삼겹살", 9000))
        tempList.add(FoodMenu("스파게티", 6500))

        binding.cartRecyclerView.adapter = adapter

    }

    // 주문 완료 버튼 이벤트 리스너 연결
    private fun initButton() {
        binding.cartOKBtn.setOnClickListener {
            Toast.makeText(this.context, "주문 완료되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}