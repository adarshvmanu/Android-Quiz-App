package com.adarsh.asjquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    private lateinit var scoreboard : TextView
    private lateinit var back : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        scoreboard = findViewById(R.id.textView3)
        back = findViewById(R.id.button2)
        scoreboard.text= intent.getStringExtra("score")+"/50"
        back.setOnClickListener {
           startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}