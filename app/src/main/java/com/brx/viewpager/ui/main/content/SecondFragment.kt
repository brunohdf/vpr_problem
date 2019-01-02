package com.brx.viewpager.ui.main.content

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.brx.viewpager.R
import kotlinx.android.synthetic.main.first_fragment.*

class SecondFragment : Fragment(), TabContent {

    private lateinit var viewModel: SecondViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.second_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SecondViewModel::class.java)

        viewModel.title.observe(this, Observer {
            it?.let {
                val color = ContextCompat.getColor(FirstFragment@context!!, it.color)

                label.text = it.title
                label.setTextColor(color)
            }
        })
    }

    override fun apply(color: Int) {
        label.loading()

        viewModel.init(color)
    }
}
