package com.example.android_for_w2

import java.io.Serializable

class Content(val belongingUserId: Long, var text: String, val picIdArray: ArrayList<Int>):
    Serializable {

}