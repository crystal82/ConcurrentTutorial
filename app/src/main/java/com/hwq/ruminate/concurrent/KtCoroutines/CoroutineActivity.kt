package com.hwq.ruminate.concurrent.KtCoroutines

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hwq.ruminate.concurrent.R

class CoroutineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)

        CorontinesCenter.launchTest()
    }
}
