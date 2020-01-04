package com.soo.a20200104_practice_lottotest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }


    override fun setupEvents() {

        lottoBtn.setOnClickListener {
            val intent = Intent(mContext, LottoActivity::class.java)
            startActivity(intent)
        }


    }

    override fun setValues() {

    }


}
