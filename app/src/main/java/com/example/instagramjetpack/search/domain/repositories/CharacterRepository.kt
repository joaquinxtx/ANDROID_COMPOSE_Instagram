package com.example.instagramjetpack.search.domain.repositories

import kotlinx.coroutines.flow.Flow
import com.example.instagramjetpack.search.data.Result
import com.example.instagramjetpack.search.domain.model.Characters
import com.example.instagramjetpack.search.domain.model.Character

interface CharacterRepository {
    fun getCharacters(page:Int):Flow<Result<List<Characters>>>

    suspend fun getCharacter(id:Int): Result<Character>

}