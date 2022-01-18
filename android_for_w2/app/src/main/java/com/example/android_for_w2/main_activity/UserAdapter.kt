package com.example.android_for_w2.main_activity

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_for_w2.*
import java.io.Serializable

class UserAdapter(private val contextOfMainActivity: Context, private val userList: List<User>,
                  private val contentArray: ArrayList<Content>, private val textView1: TextView,
                  private val recyclerView2: RecyclerView, private val mainActivity: MainActivity): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userImage: ImageView = view.findViewById(R.id.userPicture)
        val userImageRoundedCorner: ImageView = view.findViewById(R.id.roundedCorner)
        val userName: TextView = view.findViewById(R.id.userName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.user_head, parent, false)
        val viewHolder = ViewHolder(view)

        //用户头像列表区域的 onClickListener
        //用户头像圆角是用圆角的透明图层覆盖实现的 ₍ᐢ•⌄• ₎
        viewHolder.itemView.setOnLongClickListener {
            onLongClickUserHead(viewHolder, contextOfMainActivity)
            true
        }

        viewHolder.userImageRoundedCorner.setOnLongClickListener {
            onLongClickUserHead(viewHolder, contextOfMainActivity)
            true
        }

        viewHolder.itemView.setOnClickListener {
            onClickUserHead(viewHolder)
        }
        viewHolder.userImageRoundedCorner.setOnClickListener {
            onClickUserHead(viewHolder)
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.userImage.setImageResource(user.userHeadId)
        holder.userImageRoundedCorner.setImageResource(R.drawable.round_corner)
        holder.userName.text = user.name
    }

    override fun getItemCount() = userList.size

    private fun onClickUserHead(viewHolder: ViewHolder) {
        fun setRecyclerView2(user: User, recyclerView2: RecyclerView, contentArray: ArrayList<Content>){
            //搜索所点击用户的所有动态，插入列表
            val contentArrayOfUser = FindContentByUserId.method(user.id, contentArray)
            //重设 recyclerView2 的 adapter 至当前用户
            val recyclerView2Adapter = ContentAdapter(user, contentArrayOfUser)
            recyclerView2.adapter = recyclerView2Adapter
        }
        fun setTextView1(user: User){
            textView1.text = String.format(user.name + "的动态")
        }

        val position = viewHolder.bindingAdapterPosition
        val user = userList[position]
        setTextView1(user)
        setRecyclerView2(user, recyclerView2, contentArray)
    }

    private fun onLongClickUserHead(viewHolder: UserAdapter.ViewHolder, contextOfMainActivity: Context){
        val intent = Intent(contextOfMainActivity, DetailActivity::class.java)
        val position = viewHolder.bindingAdapterPosition
        val user = userList[position]
        //传一个对象进去
        intent.putExtra("user", user as Serializable?)
        mainActivity.startActivityForResult(intent, 1)
    }



}