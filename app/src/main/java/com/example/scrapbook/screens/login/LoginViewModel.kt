package com.example.scrapbook.screens.login

import android.app.Application
import android.arch.lifecycle.LiveData
import com.example.scrapbook.R
import com.example.scrapbook.common.AuthManager
import com.example.scrapbook.common.SingleLiveEvent
import com.example.scrapbook.screens.common.BaseViewModel
import com.example.scrapbook.screens.common.CommonViewModel
import com.google.android.gms.tasks.OnFailureListener

class LoginViewModel(private val authManager: AuthManager,
                     private val app: Application,
                     private val commonViewModel: CommonViewModel,
                     onFailureListener: OnFailureListener) : BaseViewModel(onFailureListener) {
    private val _goToHomeScreen = SingleLiveEvent<Unit>()
    val goToHomeScreen: LiveData<Unit> = _goToHomeScreen
    private val _goToRegisterScreen = SingleLiveEvent<Unit>()
    val goToRegisterScreen: LiveData<Unit> = _goToRegisterScreen

    fun onLoginClick(email: String, password: String) {
        if (validate(email, password)) {
            authManager.signIn(email, password).addOnSuccessListener {
                _goToHomeScreen.value = Unit
            }.addOnFailureListener(onFailureListener)
        } else {
            commonViewModel.setErrorMessage(app.getString(R.string.please_enter_email_and_password))
        }
    }

    private fun validate(email: String, password: String) =
            email.isNotEmpty() && password.isNotEmpty()

    fun onRegisterClick() {
        _goToRegisterScreen.call()
    }
}