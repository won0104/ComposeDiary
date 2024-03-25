package com.example.diary.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.diary.model.Diary
import com.example.diary.model.DiaryRepository
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class DiaryViewModel(private val repository: DiaryRepository) : ViewModel() {
    fun insertDiary(title: String, content: String, createdDate: LocalDateTime) = viewModelScope.launch {
        val diary = Diary(title = title, content = content, createdDate = createdDate)
        repository.insertDiary(diary)
    }

    fun getDiary(id:Int) : LiveData<Diary> = repository.getDiary(id)

    val getAllDiaries: LiveData<List<Diary>> = repository.getALLDiaries()


    fun updateDiary(id: Int, title: String, content: String, createdDate: LocalDateTime) = viewModelScope.launch {
        val existingDiary = repository.getDiaryById(id) ?: return@launch
        val updatedDiary = existingDiary.copy(title = title, content = content, createdDate = createdDate, modifiedDate = LocalDateTime.now())
        repository.updateDiary(updatedDiary)
    }

    fun deleteDiary(id:Int) = viewModelScope.launch {
        repository.deleteDiary(id)
    }

    private val _selectedDate = MutableLiveData(LocalDateTime.now())
    val selectedDate: LiveData<LocalDateTime> = _selectedDate
    fun updateSelectedDate(date:LocalDateTime){
        _selectedDate.value = date
    }
}

// ViewModel을 생성하기 위한 Factory 클래스
class DiaryViewModelFactory(private val repository: DiaryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DiaryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DiaryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
