package com.brx.viewpager.ui.main.content

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.SystemClock
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brx.viewpager.R
import kotlinx.android.synthetic.main.first_fragment.*

class FirstFragment : Fragment(), TabContent {

    private lateinit var viewModel: FirstViewModel
    private var list = mutableListOf<Pessoa>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        SystemClock.sleep(0) // delay on UI
        return inflater.inflate(R.layout.first_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = FirstAdapter(list)
        val linearLayoutManager = LinearLayoutManager(activity)
        firstList.layoutManager = linearLayoutManager
        firstList.adapter = adapter

        viewModel = ViewModelProviders.of(this).get(FirstViewModel::class.java)

        viewModel.title.observe(this, Observer {
            it?.let {
                val color = ContextCompat.getColor(context!!, it.color)

                label.text = it.title
                label.setTextColor(color)
            }
        })

        viewModel.pessoas.observe(this, Observer { pessoas ->
            pessoas?.let {
                list.clear()
                list.addAll(pessoas)
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun apply(color: Int) {
        label.loading()

        viewModel.init(color)
    }

}
