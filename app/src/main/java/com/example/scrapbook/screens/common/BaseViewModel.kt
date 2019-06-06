package com.example.scrapbook.screens.common

import android.arch.lifecycle.ViewModel
import com.google.android.gms.tasks.OnFailureListener

abstract class BaseViewModel(protected val onFailureListener: OnFailureListener) : ViewModel()