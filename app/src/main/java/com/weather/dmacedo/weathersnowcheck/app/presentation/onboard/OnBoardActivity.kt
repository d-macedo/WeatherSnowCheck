package com.weather.dmacedo.weathersnowcheck.app.presentation.onboard

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.weather.dmacedo.weathersnowcheck.R
import com.weather.dmacedo.weathersnowcheck.app.presentation.onboard.fragments.first.FirstOnBoardFragment
import com.weather.dmacedo.weathersnowcheck.app.presentation.onboard.fragments.second.SecondOnBoardFragment
import com.weather.dmacedo.weathersnowcheck.app.presentation.onboard.fragments.third.ThirdOnBoardFragment
import kotlinx.android.synthetic.main.activity_on_board.*

class OnBoardActivity : FragmentActivity() {

    lateinit var onBoardAdapter: OnBoardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_board)
        onBoardAdapter = OnBoardAdapter(supportFragmentManager)
        viewpager_onboard.adapter = onBoardAdapter
    }
}

class OnBoardAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return FirstOnBoardFragment.newInstance()
            1 -> return SecondOnBoardFragment.newInstance()
            2 -> return ThirdOnBoardFragment.newInstance()
            else -> {
                return FirstOnBoardFragment.newInstance()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }
}
