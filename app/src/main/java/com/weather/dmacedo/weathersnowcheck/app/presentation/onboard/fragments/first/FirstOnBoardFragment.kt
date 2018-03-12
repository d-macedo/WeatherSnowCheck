package com.weather.dmacedo.weathersnowcheck.app.presentation.onboard.fragments.first

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.weather.dmacedo.weathersnowcheck.R

class FirstOnBoardFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_first_on_board, container, false)
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        fun newInstance(): FirstOnBoardFragment {
            return FirstOnBoardFragment()
        }
    }
}
