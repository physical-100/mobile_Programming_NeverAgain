package com.example.neveragain.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neveragain.data.Menu
import com.example.neveragain.data.Ordered
import com.example.neveragain.adapter.OrderedAdapter
import com.example.neveragain.data.Restaurant
import com.example.neveragain.databinding.FragmentOrderedListBinding

class OrderedListFragment : Fragment() {
    var binding: FragmentOrderedListBinding?=null
    var adapter: OrderedAdapter?= null

    val orders: ArrayList<Ordered> = ArrayList()
    val restaurants: ArrayList<Restaurant> = ArrayList()
    val menus: ArrayList<Menu> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOrderedListBinding.inflate(layoutInflater,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initRecyclerView()
        initSearchView()
    }

    private fun initSearchView() {
        binding!!.keyword.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                adapter!!.filter.filter(p0)
                return true;
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }

        })
    }

    private fun initRecyclerView() {
        binding!!.recyclerView.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
        adapter = OrderedAdapter(orders,restaurants,menus)
        adapter!!.itemClickListener = object : OrderedAdapter.OnItemClickListener {

            override fun onTextClick(data: Ordered) {
                TODO("클래스 이름 정해지면")

            }

            override fun onButtonClick(data: Ordered) {
                TODO("클래스 이름 정해지면")

            }

            override fun onReviewClick(data: Ordered) {
                TODO("클래스 이름 정해지면")

            }


        }
        binding!!.recyclerView.adapter =adapter
    }

    private fun initData() {
        TODO("쿼리문이랑 데이터 클래스 보고 수정")
        restaurants.add(
            Restaurant(12345,"가나다", menus,"1234",true,true,
            arrayListOf(Pair(1000,1000)),"김밥집",1.1,1.1)
        )
        restaurants.add(
            Restaurant(22222,"건국대", menus,"1234",true,true,
            arrayListOf(Pair(1000,1000)),"김밥집",1.1,1.1)
        )
        restaurants.add(
            Restaurant(33333,"라마", menus,"1234",true,true,
            arrayListOf(Pair(1000,1000)),"김밥집",1.1,1.1)
        )

        orders.add(Ordered(1,12345, arrayListOf(Pair(12345,2),Pair(1,3)),"화양동","2023/5/5",true))
        orders.add(Ordered(2,22222, arrayListOf(Pair(123, 1),Pair(1,2)),"강남구","2023/5/6",false))
        orders.add(Ordered(3,33333, arrayListOf(Pair(1, 3),Pair(12345,1)),"대치동","2023/5/7",true))

        menus.add(Menu(12345,"김밥",3000))
        menus.add(Menu(123,"밥",3000))
        menus.add(Menu(1,"김",3000))
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}