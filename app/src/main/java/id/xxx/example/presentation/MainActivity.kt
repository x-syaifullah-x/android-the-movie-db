package id.xxx.example.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.xxx.example.App
import id.xxx.example.R
import id.xxx.the.movie.db.domain.usecase.Interactor
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var data: Interactor

    override fun onCreate(savedInstanceState: Bundle?) {

        (application as App).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}