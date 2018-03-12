package com.weather.dmacedo.weathersnowcheck.app.presentation.onboard.fragments.third

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.weather.dmacedo.weathersnowcheck.R

class ThirdOnBoardFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_third_on_board, container, false)
    }

    companion object {
        fun newInstance(): ThirdOnBoardFragment {
            return ThirdOnBoardFragment()
        }
    }
}
