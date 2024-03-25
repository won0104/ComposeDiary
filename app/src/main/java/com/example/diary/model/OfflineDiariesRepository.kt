package com.example.diary.model

import androidx.lifecycle.LiveData

//Repository 인터페이스에 정의된 함수를 재정의하고 DAO에서 사용하는 함수를 호출한다.
class OfflineDiariesRepository(private val diaryDao: DiaryDao) : DiaryRepository{

    override fun getALLDiaries(): LiveData<List<Diary>> = diaryDao.getALLDiaries()

    override fun getDiary(id:Int): LiveData<Diary> = diaryDao.getDiary(id)
    override suspend fun getDiaryById(id: Int): Diary? = diaryDao.getDiaryById(id)

    override suspend fun deleteDiary(id: Int) = diaryDao.delete(id)

    override suspend fun insertDiary(diary: Diary) = diaryDao.insert(diary)

    override suspend fun updateDiary(diary: Diary) = diaryDao.update(diary)
}
