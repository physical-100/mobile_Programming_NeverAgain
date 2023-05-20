package com.example.neveragain.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.neveragain.R
import com.example.neveragain.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvAddress.text = arguments?.getString("data")
        //넘어온 데이터 확인 -> 현재 오류 조금 있음
        var data = arguments?.getString("data")
        Log.i(data,"주소값")
    }

    override fun onResume() {
        super.onResume()
        setaddress()

    }

    private fun setaddress() {
        binding.tvAddress.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_setAddressFragment)
        }
        binding.imageView.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_setAddressFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}