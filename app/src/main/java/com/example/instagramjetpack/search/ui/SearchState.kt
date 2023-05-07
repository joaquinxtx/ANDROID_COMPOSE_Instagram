package com.example.instagramjetpack.search.ui

import com.example.instagramjetpack.search.domain.model.Characters

data class SearchState(
    val characters:List<Characters> = emptyList(),
    val showPrevious : Boolean = false,
    val showNext:Boolean = false ,
    val isLoading:Boolean = false
)
