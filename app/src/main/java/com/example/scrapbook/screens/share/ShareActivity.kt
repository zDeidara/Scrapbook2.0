package com.example.scrapbook.screens.share

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.scrapbook.R
import com.example.scrapbook.data.firebase.common.FirebaseHelper
import com.example.scrapbook.models.User
import com.example.scrapbook.screens.common.BaseActivity
import com.example.scrapbook.screens.common.CameraHelper
import com.example.scrapbook.screens.common.loadImage
import com.example.scrapbook.screens.common.setupAuthGuard
import kotlinx.android.synthetic.main.activity_share.*

class ShareActivity : BaseActivity() {
    private lateinit var mCamera: CameraHelper
    private lateinit var mFirebase: FirebaseHelper
    private lateinit var mUser: User
    private lateinit var mViewModel: ShareViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)
        Log.d(TAG, "onCreate")

        setupAuthGuard {
            mViewModel = initViewModel()
            mFirebase = FirebaseHelper(this)

            mCamera = CameraHelper(this)
            mCamera.takeCameraPicture()

            back_image.setOnClickListener { finish() }
            share_text.setOnClickListener { share() }

            mViewModel.user.observe(this, Observer {
                it?.let {
                    mUser = it
                }
            })
            mViewModel.shareCompletedEvent.observe(this, Observer {
                finish()
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == mCamera.REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                post_image.loadImage(mCamera.imageUri?.toString())
            } else {
                finish()
            }
        }
    }

    private fun share() {
        mViewModel.share(mUser, mCamera.imageUri, caption_input.text.toString())
    }

    companion object {
        const val TAG = "ShareActivity"
    }
}