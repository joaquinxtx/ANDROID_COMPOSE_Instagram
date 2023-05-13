package com.example.instagramjetpack.search.ui

import com.example.instagramjetpack.search.domain.model.Character



data class DetailState(
    val character: Character? = null,
    val isLoading: Boolean = false
)