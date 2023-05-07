package com.example.instagramjetpack.search.domain.use_case

import com.example.instagramjetpack.search.data.Result
import com.example.instagramjetpack.search.domain.repositories.CharacterRepository
import com.example.instagramjetpack.search.domain.model.Character
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(id:Int): Result<Character> {
        return repository.getCharacter(id)
    }
}