package com.brx.viewpager.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brx.viewpager.R
import com.brx.viewpager.ui.main.content.FirstFragment
import com.brx.viewpager.ui.main.content.SecondFragment
import com.brx.viewpager.ui.main.content.TabContent
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: SectionPageAdapter
    private var firstLoad = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupTabLayout(viewPager)

        adapter = setupAdapter()
        viewPager.adapter = adapter
        viewPager.post {
            firstLoad = true
            viewModel.init()
        }

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.fetch.observe(this, Observer {
            firstFetch()
        })

        setupListeners()
    }

    private fun setupTabLayout(viewPager: ViewPager) {
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab){
                if (firstLoad) firstFetch(tab.position)
            }
            override fun onTabUnselected(tab: TabLayout.Tab) = Unit
            override fun onTabReselected(tab: TabLayout.Tab) = Unit
        })
    }

    private fun setupAdapter(): SectionPageAdapter {
        val adapter = SectionPageAdapter(fragmentManager)
        val firstFragment = FirstFragment()
        adapter.addFragment(firstFragment)
        adapter.addFragment(SecondFragment())
        return adapter
    }

    private fun firstFetch(position: Int) {
        fetchWithColor(R.color.gray, position)
    }

    private fun firstFetch() {
        fetchWithColor(R.color.gray)
    }

    private fun fetchWithColor(color: Int) {
        fetchWithColor(color, viewPager.currentItem)
    }

    private fun fetchWithColor(color: Int, position: Int) {
        (adapter.getItem(position) as TabContent).apply(color)
    }

    private fun setupListeners() {
        red.setOnClickListener {
            fetchWithColor(R.color.red)
        }

        blue.setOnClickListener {
            fetchWithColor(R.color.blue)
        }
    }
}
