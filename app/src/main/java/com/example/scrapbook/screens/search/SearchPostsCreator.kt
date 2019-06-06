package com.example.scrapbook.screens.search

import android.arch.lifecycle.Observer
import android.util.Log
import com.example.scrapbook.common.BaseEventListener
import com.example.scrapbook.common.Event
import com.example.scrapbook.common.EventBus
import com.example.scrapbook.data.SearchRepository
import com.example.scrapbook.models.SearchPost

class SearchPostsCreator(searchRepo: SearchRepository) : BaseEventListener() {
    init {
        EventBus.events.observe(this, Observer {
            it?.let { event ->
                when (event) {
                    is Event.CreateFeedPost -> {
                        val searchPost = with(event.post) {
                            SearchPost(
                                    image = image,
                                    caption = caption,
                                    postId = id)
                        }
                        searchRepo.createPost(searchPost).addOnFailureListener {
                            Log.d(TAG, "Failed to create search post for event: $event", it)
                        }
                    }
                    else -> {
                    }
                }
            }
        })
    }

    companion object {
        const val TAG = "SearchPostsCreator"
    }
}