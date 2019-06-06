package com.example.scrapbook.common.firebase

import com.example.scrapbook.common.AuthManager
import com.example.scrapbook.common.toUnit
import com.example.scrapbook.data.firebase.common.auth
import com.google.android.gms.tasks.Task

class FirebaseAuthManager : AuthManager {
    override fun signOut() {
        auth.signOut()
    }

    override fun signIn(email: String, password: String): Task<Unit> =
        auth.signInWithEmailAndPassword(email, password).toUnit()
}