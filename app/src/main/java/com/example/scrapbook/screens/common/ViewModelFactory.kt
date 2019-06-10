package com.example.scrapbook.screens.common

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.scrapbook.screens.ScrapbookApp
import com.example.scrapbook.screens.addfriends.AddFriendsViewModel
import com.example.scrapbook.screens.comments.CommentsViewModel
import com.example.scrapbook.screens.editprofile.EditProfileViewModel
import com.example.scrapbook.screens.home.HomeViewModel
import com.example.scrapbook.screens.login.LoginViewModel
import com.example.scrapbook.screens.notifications.NotificationsViewModel
import com.example.scrapbook.screens.profile.ProfileViewModel
import com.example.scrapbook.screens.profilesettings.ProfileSettingsViewModel
import com.example.scrapbook.screens.register.RegisterViewModel
import com.example.scrapbook.screens.search.SearchViewModel
import com.example.scrapbook.screens.share.ShareViewModel
import com.google.android.gms.tasks.OnFailureListener

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val app: ScrapbookApp,
                       private val commonViewModel: CommonViewModel,
                       private val onFailureListener: OnFailureListener) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val usersRepo = app.usersRepo
        val feedPostsRepo = app.feedPostsRepo
        val authManager = app.authManager
        val notificationsRepo = app.notificationsRepo
        val searchRepo = app.searchRepo

        if (modelClass.isAssignableFrom(AddFriendsViewModel::class.java)) {
            return AddFriendsViewModel(onFailureListener, usersRepo, feedPostsRepo) as T
        } else if (modelClass.isAssignableFrom(EditProfileViewModel::class.java)) {
            return EditProfileViewModel(onFailureListener, usersRepo) as T
        } else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(onFailureListener, feedPostsRepo) as T
        } else if (modelClass.isAssignableFrom(ProfileSettingsViewModel::class.java)) {
            return ProfileSettingsViewModel(authManager, onFailureListener) as T
        } else if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(authManager, app, commonViewModel, onFailureListener) as T
        } else if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(usersRepo, onFailureListener) as T
        } else if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(commonViewModel, app, onFailureListener, usersRepo) as T
        } else if (modelClass.isAssignableFrom(ShareViewModel::class.java)) {
            return ShareViewModel(feedPostsRepo, usersRepo, onFailureListener) as T
        } else if (modelClass.isAssignableFrom(CommentsViewModel::class.java)) {
            return CommentsViewModel(feedPostsRepo, usersRepo, onFailureListener) as T
        } else if (modelClass.isAssignableFrom(NotificationsViewModel::class.java)) {
            return NotificationsViewModel(notificationsRepo, onFailureListener) as T
        } else if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(searchRepo, onFailureListener) as T
        } else {
            error("Unknown view model class $modelClass")
        }
    }
}