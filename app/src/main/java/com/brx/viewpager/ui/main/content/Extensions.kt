package com.brx.viewpager.ui.main.content

import android.support.v4.content.ContextCompat
import android.widget.TextView
import com.brx.viewpager.R

fun TextView.loading() {
    this.text = context.getString(R.string.loading_message)
    this.setTextColor(ContextCompat.getColor(context!!, R.color.gray))

}