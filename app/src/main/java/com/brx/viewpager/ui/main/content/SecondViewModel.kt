package com.brx.viewpager.ui.main.content

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel;
import android.os.Handler

class SecondViewModel : ViewModel() {
    var title: MutableLiveData<Content> = MutableLiveData()

    fun init(color: Int) {
        val handler = Handler()
        handler.postDelayed({
            title.value = Content("Second fragment", color)
        }, 2000)
    }
}
