package com.example.diary.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface DiaryDao {
    // Suspend : 별도의 스레드에서 실행
    // onConflict~~ : 충돌이 발생할 경우 Room에 실행할 작업 알려줌 -> 여기선 IGNORE 즉 새 항목을 무시
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(diary: Diary)

    @Update
    suspend fun update(diary: Diary)

    @Query("DELETE FROM diarys WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * from diarys WHERE id = :id")
    fun getDiary(id:Int): LiveData<Diary>

    @Query("SELECT * from diarys ORDER BY createdDate")
    fun getALLDiaries(): LiveData<List<Diary>>

    @Query("SELECT * FROM diarys WHERE id = :id")
    suspend fun getDiaryById(id: Int): Diary?
}

