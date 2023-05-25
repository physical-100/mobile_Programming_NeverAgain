package com.example.neveragain

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.neveragain.presentation.fragment.writeCommentFragment
import com.example.neveragain.presentation.fragment.writeReviewFragment

class ReviewViewPageAdapter(fragment:Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2;
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> writeReviewFragment()
            1 -> writeCommentFragment()
            else -> writeReviewFragment()
        }
    }
}