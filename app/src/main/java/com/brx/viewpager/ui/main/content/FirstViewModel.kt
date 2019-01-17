package com.brx.viewpager.ui.main.content

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.Handler

class FirstViewModel : ViewModel() {

    var title: MutableLiveData<Content> = MutableLiveData()
    var pessoas: MutableLiveData<List<Pessoa>> = MutableLiveData()

    fun init(color: Int) {
        val handler = Handler()
        handler.postDelayed({
            title.value = Content("First fragment", color)
            pessoas.value = listOf(Pessoa("Maria"), Pessoa("Joao"), Pessoa("Carlos"))
        }, 2000)
    }
}
