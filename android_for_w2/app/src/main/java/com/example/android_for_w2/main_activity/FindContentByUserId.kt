package com.example.android_for_w2.main_activity
import com.example.android_for_w2.Content
import kotlin.collections.ArrayList

object FindContentByUserId {
    fun method(userId: Long, contentArray: ArrayList<Content>): List<Content> {
        val contentIdList: ArrayList<Content> = ArrayList()
        for (i in contentArray) {
            if (i.belongingUserId == userId) {
                contentIdList.add(i)
            }
        }
        return (contentIdList)
    }
}