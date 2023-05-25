package com.example.neveragain.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.example.neveragain.R
import com.example.neveragain.ReviewViewPageAdapter
import com.example.neveragain.databinding.FragmentReviewBinding
import com.google.android.material.tabs.TabLayoutMediator

class ReviewFragment : Fragment() {
    lateinit var binding: FragmentReviewBinding
    val tabTextArray = arrayListOf<String>("리뷰작성", "코멘트작성")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentReviewBinding.bind(view)
        initTabLayout()

    }

    private fun initTabLayout() {
        // @ 어댑터의 파라미터값 수정 필요 -> 해결 완료
        binding.reviewViewPager.adapter = ReviewViewPageAdapter(this)
        TabLayoutMediator(binding.reviewTabLayout, binding.reviewViewPager) {
                tab, pos ->
            tab.text = tabTextArray[pos]
        }.attach()
    }
}