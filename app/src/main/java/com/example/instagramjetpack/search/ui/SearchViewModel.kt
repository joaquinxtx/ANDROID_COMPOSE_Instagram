package com.example.instagramjetpack.search.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.instagramjetpack.search.data.Result
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instagramjetpack.search.domain.use_case.GetCharacterUseCase
import com.example.instagramjetpack.search.domain.use_case.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase

) : ViewModel() {

    var state by mutableStateOf(SearchState(isLoading = true))
        private set

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentPage = 1

    init {
        getCharacters(increase = false)
        Log.d("cahraterssss", state.characters.toString())
    }

    private fun getCharacters(increase: Boolean) {
        viewModelScope.launch {
            if (increase) currentPage++ else if (currentPage > 1) currentPage--
            val showPrevious = currentPage > 1
            val showNext = currentPage < 42
            getCharactersUseCase(currentPage).onEach { result ->
                when (result) {
                    is Result.Success -> {
                        state = state.copy(
                            characters = result.data ?: emptyList(),
                            isLoading = false,
                            showPrevious = showPrevious,
                            showNext = showNext
                        )
                    }
                    is Result.Error -> {
                        state = state.copy(
                            isLoading = false
                        )
                        _eventFlow.emit(
                            UIEvent.ShowSnackBar(
                                result.message ?: "Unknown error"
                            )
                        )
                    }
                    is Result.Loading -> {
                        state = state.copy(
                            isLoading = true
                        )
                    }
                }
            }.launchIn(this)
        }
    }


    sealed class UIEvent {
        data class ShowSnackBar(val message: String) : UIEvent()
    }

    private val _search = MutableLiveData<String>()
    val search: LiveData<String> = _search

    fun onSearch(search: String) {
        _search.value = search

    }
}

//Explicar estoo