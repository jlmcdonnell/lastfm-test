package com.mcdonnellapps.lastfmtest.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mcdonnellapps.lastfmtest.R
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Result
import com.mcdonnellapps.lastfmtest.presentation.home.HomePresenter
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity(), HomePresenter.View {

    private val presenter by inject<HomePresenter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
        presenter.subscribe(this)
        presenter.query("1234")
    }

    override fun showResults(results: List<Result>) {
        Toast.makeText(this, "Loads results: $results", Toast.LENGTH_SHORT).show()
    }

}
