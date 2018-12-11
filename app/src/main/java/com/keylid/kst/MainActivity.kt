package com.keylid.kst
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.content.Intent
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ok.setOnClickListener{
            val intent = Intent(this@MainActivity,Main2Activity::class.java)
            var Number=editText2.text.toString()
            intent.putExtra("number",Number)
            startActivity(intent)
        }


    }
}
