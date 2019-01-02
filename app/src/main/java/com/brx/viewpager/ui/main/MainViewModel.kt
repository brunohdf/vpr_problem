package com.brx.viewpager.ui.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var fetch: MutableLiveData<Boolean> = MutableLiveData()

    fun init() {
        fetch.value = true
    }
}
