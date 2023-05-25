package com.example.neveragain.presentation.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.neveragain.R
import com.example.neveragain.databinding.FragmentSetAddressBinding

class SetAddressFragment : Fragment(R.layout.fragment_set_address) {

    private var _binding: FragmentSetAddressBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSetAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        BridgeInterface().data = null
    }


    private fun init() {
        binding.apply {
            webView.settings.javaScriptEnabled = true
            webView.addJavascriptInterface(BridgeInterface(), "Android")
            webView.webViewClient = object : WebViewClient() {
                // android -> javascript 함수 호출
                override fun onPageFinished(view: WebView?, url: String?) {
                    webView.loadUrl("javascript:sample2_execDaumPostcode();")
                }
            }
            // 최초 웹뷰 로드
            webView.loadUrl("https://neveragain-d7be6.web.app")
        }
    }


    inner class BridgeInterface {
        var data: String? = null

        @JavascriptInterface
        open fun processDATA(data: String?) {
            // 다음(카카오)주소 검색 API의 결과값이 브릿지 통로를 통해 전달받는다.
            // javascript -> android
            this.data = data
            var bundle = bundleOf("data" to this.data)
            val navController = findNavController()


            navController.navigate(R.id.action_setAddressFragment_to_mainFragment, bundle)
            navController.popBackStack(R.id.mainFragment, false)

        }

    }
}
