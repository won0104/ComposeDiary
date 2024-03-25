package com.example.diary.model

import androidx.lifecycle.LiveData

// dao 구현에 매핑되는 함수를 인터페이스에 추가
interface DiaryRepository {
    fun getALLDiaries(): LiveData<List<Diary>>

    fun getDiary(id:Int) : LiveData<Diary>

    suspend fun insertDiary(diary: Diary)

    suspend fun deleteDiary(id: Int)

    suspend fun updateDiary(diary: Diary)

    suspend fun getDiaryById(id: Int): Diary?
}