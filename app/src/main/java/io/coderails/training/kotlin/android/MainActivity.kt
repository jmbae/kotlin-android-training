package io.coderails.training.kotlin.android

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mButton: Button

    private val REQUEST_CODE_SECOND_ACTIVITY: Int = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mButton = findViewById(R.id.button)

        mButton.setOnClickListener {
            Toast.makeText(this, "Go second!", Toast.LENGTH_LONG).show()
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("textFromMain", "Hello SecondActivity?")
            startActivityForResult(intent, REQUEST_CODE_SECOND_ACTIVITY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SECOND_ACTIVITY && resultCode == Activity.RESULT_OK) {
            textView.text = data?.getStringExtra("returnValue")
        }
    }
}
