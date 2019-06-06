package com.example.scrapbook.screens.profile

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.scrapbook.data.UsersRepository
import com.example.scrapbook.screens.common.BaseViewModel
import com.google.android.gms.tasks.OnFailureListener

class ProfileViewModel(private val usersRepo: UsersRepository, onFailureListener: OnFailureListener)
    : BaseViewModel(onFailureListener) {
    val user = usersRepo.getUser()
    lateinit var images: LiveData<List<String>>

    fun init(uid: String) {
        if (!this::images.isInitialized) {
            images = usersRepo.getImages(uid)
        }
    }
}