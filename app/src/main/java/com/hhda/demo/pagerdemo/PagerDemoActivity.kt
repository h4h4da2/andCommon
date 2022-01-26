package com.hhda.demo.pagerdemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.hhda.widget.fragment.FragmentUtil
import com.hhda.demo.R
import com.hhda.demo.databinding.ActivityPagerDemoActivityBinding
import com.hhda.demo.pagerdemo.frags.PagerFragments

class PagerDemoActivity : AppCompatActivity() {

    companion object {
        fun route(ctx: Context) {
            val intent = Intent(ctx, PagerDemoActivity::class.java)
            ctx.startActivity(intent)
        }
    }


    private lateinit var fragList: MutableList<Fragment>
    private var curIndex = 0

    private lateinit var binding: ActivityPagerDemoActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagerDemoActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fragList = mutableListOf()
        for (i in 0..5) {
            val f = PagerFragments()
            f.arguments = Bundle().apply {
                putString(PagerFragments.ARGS_FRAG_TEXT, "this is page $i")
            }
            FragmentUtil.setFragmentTag(f, i.toString())
            fragList.add(f)
        }

        switchFragment()

        binding.switchBtn.setOnClickListener { switchFragment() }
    }

    private fun switchFragment() {

        FragmentUtil.showOrHide(
            curIndex % fragList.size,
            fragList,
            supportFragmentManager,
            R.id.pageContainer
        )
        curIndex++


    }


}