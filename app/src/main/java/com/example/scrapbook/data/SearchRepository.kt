package com.example.scrapbook.data

import android.arch.lifecycle.LiveData
import com.example.scrapbook.models.SearchPost
import com.google.android.gms.tasks.Task

interface SearchRepository {
    fun searchPosts(text: String): LiveData<List<SearchPost>>
    fun createPost(post: SearchPost): Task<Unit>
}