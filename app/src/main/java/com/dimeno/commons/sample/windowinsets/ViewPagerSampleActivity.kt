package com.dimeno.commons.sample.windowinsets

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.dimeno.commons.sample.BaseActivity
import com.dimeno.commons.sample.R

/**
 * ViewPagerSampleActivity
 * Created by wangzhen on 2020/9/11.
 */
class ViewPagerSampleActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window_inset_sample)

        findViewById<ViewPager>(R.id.pager).adapter = SamplePagerAdapter(supportFragmentManager)
    }

    private class SamplePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getCount(): Int {
            return 2
        }

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> InsetOneFragment()
                else -> InsetTwoFragment()
            }
        }

    }
}