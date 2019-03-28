package com.mcdonnellapps.lastfmtest.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mcdonnellapps.lastfmtest.R
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.MusicSearch
import com.mcdonnellapps.lastfmtest.presentation.home.HomePresenter
import org.koin.android.ext.android.inject
import timber.log.Timber

class HomeActivity : AppCompatActivity(), HomePresenter.View {

    private val presenter by inject<HomePresenter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
        presenter.subscribe(this)
        presenter.query("Time")
    }

    override fun showSearchResult(searchResult: MusicSearch) {
        Toast.makeText(this, "Loads results: $searchResult", Toast.LENGTH_SHORT).show()
        Timber.d("Results: $searchResult")
    }

    override fun showGenericError() {
        Toast.makeText(this, "Something no good", Toast.LENGTH_SHORT).show()
    }
}
