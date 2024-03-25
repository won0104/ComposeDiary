package com.example.diary.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * @Database 주석에는 Room이 데이터베이스를 빌드할 수 있도록 인수가 여러 개 필요하다
 *
 * Diary를 entities 목록이 있는 유일한 클래스로 지정한다.
 * version을 1로 설정한다. 데이터베이스 테이블의 스키마를 변경할 때마다 버전 번호를 높여야 함
 * 스키마 버전 기록 백업을 유지하지 않도록 exportSchema를 false로 설정
 */
@Database(entities = [Diary::class], version=1, exportSchema = false)
@TypeConverters(LocalDateTimeConverter::class)
abstract class DiaryDatabase : RoomDatabase() {
    abstract fun diaryDao(): DiaryDao //데이터베이스가 DAO에 관해 알 수 있도록 diaryDao를 반환하는 추상 함수를 클래스 본문 내에서 선언

    companion object{ //데이터베이스를 만들거나 가져오는 메서드에 대한 액세스를 허용하고 클래스 이름을 한정자로 사용하는 companion object를 정의
        @Volatile // Instance값이 항상 최신으로 유지되고 모든 실행 스레드에 동일하게 유지 -> 한 스레드 인스턴스 변경시 모든 스레드에 즉시 반영
        private var instance: DiaryDatabase? = null // 데이터베이스에 관한 null을 허용하는 비공개 변수 Instance를 선언하고 null로 초기화 -> 열린 DB의 단일 인스턴스 유지 가능

        fun getDatabase(context: Context): DiaryDatabase {

            // if the Instance is not null, return it, otherwise create a new database instance.
            return instance ?: synchronized(this) { // 코드를 래핑해 Synchronized 블록 내에 DB를 가져오면 한 번에 한 실행 스레드만 코드블록에 들어가짐 -> 1번만 초기화 -> 경합상태 방지
                Room.databaseBuilder(context, DiaryDatabase::class.java, "diary_database") //동기화된 블록 내에서 데이터베이스 빌더를 사용하여 데이터베이스를 가져옴
                    .build()
                    .also { instance = it } // Instance = it을 할당하여 최근에 만들어진 db 인스턴스에 대한 참조를 유지
            }
        }
    }
}