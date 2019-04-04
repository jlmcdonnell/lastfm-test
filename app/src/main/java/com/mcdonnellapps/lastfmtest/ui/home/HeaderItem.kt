package com.mcdonnellapps.lastfmtest.ui.home

import com.mcdonnellapps.lastfmtest.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.search_header_item.*

class HeaderItem(private val title: String) : Item() {

    override fun getLayout() = R.layout.search_header_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.title.text = title
    }
}