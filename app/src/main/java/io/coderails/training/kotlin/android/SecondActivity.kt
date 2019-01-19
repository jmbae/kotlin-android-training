package io.coderails.training.kotlin.android

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val stringFromMain = intent.getStringExtra("textFromMain")
        val textView2 = findViewById<TextView>(R.id.textView2)

        textView2.text = stringFromMain

        button2.setOnClickListener {
            val intent = Intent()
            intent.putExtra("returnValue", "go back main")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}
