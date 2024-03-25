package com.example.diary.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "diarys")
data class Diary(
    @PrimaryKey (autoGenerate = true)
    val id: Int=0,
    val title: String,
    val content: String,
    val createdDate: LocalDateTime = LocalDateTime.now(), // 기본값 설정
    val modifiedDate: LocalDateTime? = null
)
