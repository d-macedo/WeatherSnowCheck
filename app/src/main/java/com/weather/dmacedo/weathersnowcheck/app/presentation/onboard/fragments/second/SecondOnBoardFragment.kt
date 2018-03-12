package com.weather.dmacedo.weathersnowcheck.app.presentation.onboard.fragments.second

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.weather.dmacedo.weathersnowcheck.R

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SecondOnBoardFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SecondOnBoardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondOnBoardFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_second_on_board, container, false)
    }

    companion object {
        fun newInstance(): SecondOnBoardFragment {
            return SecondOnBoardFragment()
        }
    }
}
