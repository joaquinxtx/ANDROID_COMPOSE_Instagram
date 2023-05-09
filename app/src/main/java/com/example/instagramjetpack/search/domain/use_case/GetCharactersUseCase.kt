package com.example.instagramjetpack.search.domain.use_case

import com.example.instagramjetpack.search.data.Result
import com.example.instagramjetpack.search.domain.model.Characters
import com.example.instagramjetpack.search.domain.repositories.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(page:Int): Flow<Result<List<Characters>>> {
        return repository.getCharacters(page)
    }
}