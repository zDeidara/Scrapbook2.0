package com.example.scrapbook.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import com.example.scrapbook.R
import com.example.scrapbook.screens.comments.CommentsActivity
import com.example.scrapbook.screens.common.BaseActivity
import kotlinx.android.synthetic.main.activity_edit_profile.*

class PostDetail: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feed_item)
        val postId = 0

    }

    companion object {
        fun start(context: Context, postId: String, isEdit: Boolean) {
            val intent = Intent(context, CommentsActivity::class.java)
            context.startActivity(intent)
        }
    }

}

