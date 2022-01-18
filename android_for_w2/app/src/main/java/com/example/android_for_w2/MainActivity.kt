package com.example.android_for_w2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_for_w2.main_activity.ContentAdapter
import com.example.android_for_w2.main_activity.FindContentByUserId
import com.example.android_for_w2.main_activity.UserAdapter

class MainActivity : AppCompatActivity() {
    private var userArray = ArrayList<User>()
    private var contentArray = ArrayList<Content>()
    private val recyclerView1: RecyclerView
        get() {
            return findViewById(R.id.recyclerView1)
        }
    private val recyclerView2: RecyclerView
        get() {
            return findViewById(R.id.recyclerView2)
        }
    private val textView1: TextView
        get() {
            val textView1 = findViewById<TextView>(R.id.textView1)
            return textView1
        }
    private val userAdapter: UserAdapter
        get() {
            return UserAdapter(this, userArray, contentArray, textView1, recyclerView2, this)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //初始化：
        initData()
        initView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //用于删除用户的函数，传ID和原列表进去，删掉之后返回新的：
        fun delUser(uid: Long, uArray: ArrayList<User>): ArrayList<User> {
            var tempArray = ArrayList<User>()
            for(i in uArray){
                if (i.id != uid) {
                    tempArray.add(i)
                }
            }
            return tempArray
        }

        when (requestCode){
            1 -> if (resultCode == RESULT_OK) {
                Toast.makeText(this, "RESULT_OK", Toast.LENGTH_SHORT).show()
                val isSubmitted: Boolean? =  data?.getBooleanExtra("isSubmitted", false)
                val userId: Long? = data?.getLongExtra("userId", -1)
                //判空，判断是否取关，取关 -> 改数据，初始化界面
                if ((isSubmitted != null) && !isSubmitted && (userId != (-1).toLong()) && userId != null){
                    userArray = delUser(userId, userArray)
                    initData()
                    initView()
                }

            }

        }
    }

    private fun initData(){
        //生成用户数据
        val demoName = listOf("user0000", "user0001", "user0002", "user0003", "user0004",
            "user0005", "user0006", "user0007", "user0008", "user0009", "user0010")
        val userHeadId: Int = R.drawable.user_head// demo使用同一张头像
        var counterForUserId:Long = 1
        for (name in demoName){
            val user = User(counterForUserId, name, userHeadId)
            counterForUserId++
            userArray.add(user)
        }

        //生成动态信息数据
        val demoContent = listOf("content of user0000", "content of user0001",
            "content of user0002", "content of user0003", "content of user0004",
            "content of user0005", "content of user0006", "content of user0007",
            "content of user0008","content of user0009", "content of user0010")
        val demoPicIdArray = arrayListOf(R.drawable.demo_content_pic)
        var counterForContentId:Long = 1
        for (name in demoContent){
            val content = Content(counterForContentId, name, demoPicIdArray)
            val content2 = Content(counterForContentId, "NO.2 $name", demoPicIdArray)
            counterForContentId++
            contentArray.add(content)
            contentArray.add(content2)
        }
    }

    //初始化View
    private fun initView(){
        //横向滚动的关注列表
        fun initRecyclerView1(){
            val layoutManager1 = LinearLayoutManager(this)
            layoutManager1.orientation = LinearLayoutManager.HORIZONTAL
            recyclerView1.layoutManager = layoutManager1
            recyclerView1.adapter = userAdapter
        }
        //“XXX的动态”横幅
        fun initTextView1(){
            val textView1 = textView1
            textView1.text = String.format(userArray[0].name + "的动态")
        }
        //对应用户动态概览
        fun initRecyclerView2(){
            val layoutManager2= LinearLayoutManager(this)
            layoutManager2.orientation = LinearLayoutManager.VERTICAL
            recyclerView2.layoutManager = layoutManager2
            recyclerView2.adapter = ContentAdapter(
                userArray[0],
                FindContentByUserId.method(userArray[0].id, contentArray)
            )
        }

        initRecyclerView1()
        initTextView1()
        initRecyclerView2()
    }
}