package com.example.android_for_w2

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class DetailActivity(private var isSubmitted: Boolean = true) : AppCompatActivity() {
    private val user: User
        get() {
            val user: User = intent.getSerializableExtra("user") as User
            return user
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initLayout()
    }

    private fun initLayout(){
        val userHead: ImageView = findViewById(R.id.userPicture)
        val userName: TextView = findViewById(R.id.userName)
        val roundedCorner: ImageView = findViewById(R.id.roundedCorner)
        userHead.setImageResource(user.userHeadId)
        roundedCorner.setImageResource(R.drawable.round_corner)
        userName.text = user.name

        //关注按钮：
        val submitButton: Button = findViewById(R.id.submitButton)
        submitButton.text = "已关注"
        submitButton.setBackgroundColor(Color.parseColor("#aaaaaa"))
        submitButton.setOnClickListener{
            isSubmitted = !isSubmitted
            if (isSubmitted){
                submitButton.text = "已关注"
                submitButton.setBackgroundColor(Color.parseColor("#aaaaaa"))
                Toast.makeText(this, "已关注", Toast.LENGTH_SHORT).show()
            }else{
                submitButton.text = "关注"
                submitButton.setBackgroundColor(Color.parseColor("#6200ee"))
                Toast.makeText(this, "已取关", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("isSubmitted", isSubmitted)
        intent.putExtra("userId", user.id)
        setResult(RESULT_OK, intent)
        finish()
    }
}