package com.example.scrapbook.screens.profilesettings

import com.example.scrapbook.common.AuthManager
import com.example.scrapbook.screens.common.BaseViewModel
import com.google.android.gms.tasks.OnFailureListener

class ProfileSettingsViewModel(private val authManager: AuthManager,
                               onFailureListener: OnFailureListener) :
        BaseViewModel(onFailureListener),
        AuthManager by authManager