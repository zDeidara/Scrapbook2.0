package com.example.scrapbook.screens

import android.app.Application
import com.example.scrapbook.common.firebase.FirebaseAuthManager
import com.example.scrapbook.data.firebase.FirebaseFeedPostsRepository
import com.example.scrapbook.data.firebase.FirebaseNotificationsRepository
import com.example.scrapbook.data.firebase.FirebaseSearchRepository
import com.example.scrapbook.data.firebase.FirebaseUsersRepository
import com.example.scrapbook.screens.notifications.NotificationsCreator
import com.example.scrapbook.screens.search.SearchPostsCreator

class ScrapbookApp : Application() {
    val usersRepo by lazy { FirebaseUsersRepository() }
    val feedPostsRepo by lazy { FirebaseFeedPostsRepository() }
    val notificationsRepo by lazy { FirebaseNotificationsRepository() }
    val authManager by lazy { FirebaseAuthManager() }
    val searchRepo by lazy { FirebaseSearchRepository() }

    override fun onCreate() {
        super.onCreate()
        NotificationsCreator(notificationsRepo, usersRepo, feedPostsRepo)
        SearchPostsCreator(searchRepo)
    }
}