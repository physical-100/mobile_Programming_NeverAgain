package com.example.neveragain.presentation.ui


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.neveragain.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IntroSplashFragment : Fragment(R.layout.fragment_intro_splash) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            delay(1000)
            findNavController().navigate(R.id.action_introSplashFragment_to_setAddressFragment)
        }
    }

}