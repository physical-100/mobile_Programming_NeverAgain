package com.example.neveragain.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neveragain.adapter.ReviewAdapter
import com.example.neveragain.data.Menu
import com.example.neveragain.data.Ordered
import com.example.neveragain.data.Review
import com.example.neveragain.data.Restaurant
import com.example.neveragain.databinding.FragmentReviewListBinding

class ReviewListFragment : Fragment() {
    var binding : FragmentReviewListBinding?= null
    var reviewAdapter: ReviewAdapter?= null
    val reviews = ArrayList<Review>()
    val restaurants = ArrayList<Restaurant>()
    val orderedLists = ArrayList<Ordered>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentReviewListBinding.inflate(layoutInflater,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding!!.recyclerView.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
        reviewAdapter = ReviewAdapter(reviews,restaurants,orderedLists)
        reviewAdapter!!.itemClickListener = object: ReviewAdapter.OnItemClickListener {
            override fun onItemClick(data: Review) {
                TODO("Fragment 전환")
                /**
                해당 매장 activity로 이동
                 **/
            }

            override fun onButtonClick(position: Int) {
                reviews.removeAt(position)
                reviewAdapter!!.notifyItemRemoved(position)
            }

            override fun onTextClick(data: Review) {
                TODO("Fragment 전환 + 메뉴 담기")
                /**
                해당 메뉴를 보여주고있는 매장 activity로 이동
                 **/
            }

        }
        binding!!.recyclerView.adapter = reviewAdapter
    }

    private fun initData() {
        TODO("쿼리문이랑 데이터 클래스 보고 수정")
        reviews.add(Review(1,12345, arrayListOf(12345,1),true,5,true,"정 시킬 곳 없으면 시킬듯"))
        reviews.add(Review(2,22222,arrayListOf(123,1),false,3,true,"정 시킬 곳 없으면 시킬듯"))
        reviews.add(Review(3,33333,arrayListOf(1),true,1,true,"정 시킬 곳 없으면 시킬듯"))

        restaurants.add(
            Restaurant(12345,"가나다", arrayListOf(
                Menu(12345,"김밥",3000),
                Menu(123,"밥",3000),
                Menu(1,"김",3000)
            ),"1234",true,true,
            arrayListOf(Pair(1000,1000)),"김밥집",1.1,1.1)
        )
        restaurants.add(
            Restaurant(22222,"가나다", arrayListOf(
                Menu(12345,"김밥",3000),
                Menu(123,"밥",3000),
                Menu(1,"김",3000)
            ),"1234",true,true,
            arrayListOf(Pair(1000,1000)),"김밥집",1.1,1.1)
        )
        restaurants.add(
            Restaurant(33333,"가나다", arrayListOf(
                Menu(12345,"김밥",3000),
                Menu(123,"밥",3000),
                Menu(1,"김",3000)
            ),"1234",true,true,
            arrayListOf(Pair(1000,1000)),"김밥집",1.1,1.1)
        )

        orderedLists.add(Ordered(1,12345, arrayListOf(Pair(12345,2),Pair(1,3)),"화양동","2023/5/5",true))
        orderedLists.add(Ordered(2,22222, arrayListOf(Pair(123, 1),Pair(1,2)),"강남구","2023/5/6",false))
        orderedLists.add(Ordered(3,33333, arrayListOf(Pair(1, 3),Pair(12345,1)),"대치동","2023/5/7",true))
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onStop() {
        super.onStop()
        TODO("데이터 저장")
    }
}