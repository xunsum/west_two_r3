package com.example.android_for_w2.main_activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_for_w2.Content
import com.example.android_for_w2.R
import com.example.android_for_w2.User

class ContentAdapter(private val user: User, private val contentArray: List<Content>): RecyclerView.Adapter<ContentAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView2: ImageView = view.findViewById(R.id.imageView2)
        val textView2: TextView = view.findViewById(R.id.textView2)
        val roundedCorner: ImageView = view.findViewById(R.id.roundedCorner)
        val userName: TextView = view.findViewById(R.id.userName)
        val userPicture: ImageView = view.findViewById(R.id.userPicture)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.showing_content_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContentAdapter.ViewHolder, position: Int) {
        val content = contentArray[position]
        holder.userPicture.setImageResource(user.userHeadId)
        holder.userName.text = user.name
        holder.roundedCorner.setImageResource(R.drawable.round_corner)
        holder.textView2.text = content.text
        holder.imageView2.setImageResource(content.picIdArray[0])

    }

    override fun getItemCount() = contentArray.size


}