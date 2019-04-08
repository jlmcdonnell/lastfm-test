package com.mcdonnellapps.lastfmtest.ui.search

import com.mcdonnellapps.lastfmtest.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.empty_placeholder.*

class EmptyState(private val message: String) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.message.text = message
    }

    override fun getLayout() = R.layout.empty_placeholder
}