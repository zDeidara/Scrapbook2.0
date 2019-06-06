package com.example.scrapbook.screens.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.example.scrapbook.data.SearchRepository
import com.example.scrapbook.models.SearchPost
import com.example.scrapbook.screens.common.BaseViewModel
import com.google.android.gms.tasks.OnFailureListener

class SearchViewModel(searchRepo: SearchRepository,
                      onFailureListener: OnFailureListener) : BaseViewModel(onFailureListener) {
    private val searchText = MutableLiveData<String>()

    val posts: LiveData<List<SearchPost>> = Transformations.switchMap(searchText) { text ->
        searchRepo.searchPosts(text)
    }

    fun setSearchText(text: String) {
        searchText.value = text
    }
}