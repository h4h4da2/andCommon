package com.hhda.demo.pagerdemo.frags

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hhda.demo.R
import com.hhda.demo.databinding.FragmentPageDemoBinding

class PagerFragments : Fragment() {

    companion object {
        const val ARGS_COLOR = "ARGS_COLOR"
        const val ARGS_FRAG_TEXT = "ARGS_FRAG_TEXT"
    }

    private var binding: FragmentPageDemoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        R.layout.fragment_page_demo
        binding = FragmentPageDemoBinding.inflate(layoutInflater)
        arguments?.apply {
            val bgColor = getInt(ARGS_COLOR, Color.TRANSPARENT)
            binding?.root?.setBackgroundColor(bgColor)
            val fragText = getString(ARGS_FRAG_TEXT, "")
            binding?.fragText?.text = fragText
        }
        return binding?.root
    }


}