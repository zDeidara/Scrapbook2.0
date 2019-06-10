package com.example.scrapbook.screens.share

import android.net.Uri
import com.example.scrapbook.common.SingleLiveEvent
import com.example.scrapbook.data.FeedPostsRepository
import com.example.scrapbook.data.UsersRepository
import com.example.scrapbook.models.FeedPost
import com.example.scrapbook.models.User
import com.example.scrapbook.screens.common.BaseViewModel
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Tasks

class ShareViewModel(private val feedPostsRepo: FeedPostsRepository,
                     private val usersRepo: UsersRepository,
                     onFailureListener: OnFailureListener) : BaseViewModel(onFailureListener) {
    private val _shareCompletedEvent = SingleLiveEvent<Unit>()
    val shareCompletedEvent = _shareCompletedEvent
    val user = usersRepo.getUser()

    fun share(user: User, imageUri: Uri?, caption: String) {
        if (imageUri != null) {
            usersRepo.uploadUserImage(user.uid, imageUri).onSuccessTask { downloadUrl ->
                Tasks.whenAll(
                        usersRepo.setUserImage(user.uid, downloadUrl!!),
                        feedPostsRepo.createFeedPost(user.uid, mkFeedPost(user, caption,
                            imageUri.toString()))
                )
            }.addOnCompleteListener{
                _shareCompletedEvent.call()
            }.addOnFailureListener(onFailureListener)
        }
    }

    private fun mkFeedPost(user: User, caption: String, imageDownloadUrl: String): FeedPost {
        return FeedPost(
                uid = user.uid,
                username = user.username,
                image = imageDownloadUrl,
                caption = caption,
                photo = user.photo
        )
    }
}