package com.example.diary.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.time.LocalDateTime

class FakeDiariesRepository : DiaryRepository {

    // 테스트 데이터 생성
    private val diariesList = mutableListOf<Diary>()
    private val diaries = MutableLiveData<List<Diary>>(diariesList)

    init {
        // 초기 테스트 데이터 추가
        diariesList.add(Diary(1, "일기1", "아무말이나 열심히 써보고 있는데 어떻게 하면 길게 써볼 수 있을지 모르겠네요..", LocalDateTime.now()))
        diariesList.add(Diary(2, "일기2", "어제는 친구들이랑 서면에 가서 고기를 먹었는데 너무 맛있더라구요 이 모든 일의 시작은 백종원 아저씨 유튜브 입니다. 왜 거기서 차돌을 맛있게 먹어서 내가 차돌을 먹고싶게 만드는지 참;; 어제는 무슨 푸파 찍는줄 알았답니다.. 그만큼 맛있었으면 된거 아니겠어요?", LocalDateTime.now()))
        diariesList.add(Diary(3, "일기3", "어제 열심히 먹었으니 오늘은 운동을 하러 가봐야게썽요 요즘 자세가 많이 무너져서 맘이 아픕니다. 중량.. 언제 다시 늘리지..? 하하 눈물이 흐르네요 여름맞이 운동 아자아자", LocalDateTime.now()))
        diariesList.add(Diary(4, "일기1", "아무말이나 열심히 써보고 있는데 어떻게 하면 길게 써볼 수 있을지 모르겠네요..", LocalDateTime.now()))
        diariesList.add(Diary(5, "일기2", "어제는 친구들이랑 서면에 가서 고기를 먹었는데 너무 맛있더라구요 이 모든 일의 시작은 백종원 아저씨 유튜브 입니다. 왜 거기서 차돌을 맛있게 먹어서 내가 차돌을 먹고싶게 만드는지 참;; 어제는 무슨 푸파 찍는줄 알았답니다.. 그만큼 맛있었으면 된거 아니겠어요?", LocalDateTime.now()))
        diariesList.add(Diary(6, "일기3", "어제 열심히 먹었으니 오늘은 운동을 하러 가봐야게썽요 요즘 자세가 많이 무너져서 맘이 아픕니다. 중량.. 언제 다시 늘리지..? 하하 눈물이 흐르네요 여름맞이 운동 아자아자", LocalDateTime.now()))
        diariesList.add(Diary(7, "일기1", "아무말이나 열심히 써보고 있는데 어떻게 하면 길게 써볼 수 있을지 모르겠네요..", LocalDateTime.now()))
        diariesList.add(Diary(8, "일기2", "어제는 친구들이랑 서면에 가서 고기를 먹었는데 너무 맛있더라구요 이 모든 일의 시작은 백종원 아저씨 유튜브 입니다. 왜 거기서 차돌을 맛있게 먹어서 내가 차돌을 먹고싶게 만드는지 참;; 어제는 무슨 푸파 찍는줄 알았답니다.. 그만큼 맛있었으면 된거 아니겠어요?", LocalDateTime.now()))
        diariesList.add(Diary(9, "일기3", "어제 열심히 먹었으니 오늘은 운동을 하러 가봐야게썽요 요즘 자세가 많이 무너져서 맘이 아픕니다. 중량.. 언제 다시 늘리지..? 하하 눈물이 흐르네요 여름맞이 운동 아자아자", LocalDateTime.now()))
        diariesList.add(Diary(10, "일기1", "아무말이나 열심히 써보고 있는데 어떻게 하면 길게 써볼 수 있을지 모르겠네요..", LocalDateTime.now()))
        diariesList.add(Diary(11, "일기2", "어제는 친구들이랑 서면에 가서 고기를 먹었는데 너무 맛있더라구요 이 모든 일의 시작은 백종원 아저씨 유튜브 입니다. 왜 거기서 차돌을 맛있게 먹어서 내가 차돌을 먹고싶게 만드는지 참;; 어제는 무슨 푸파 찍는줄 알았답니다.. 그만큼 맛있었으면 된거 아니겠어요?", LocalDateTime.now()))
        diariesList.add(Diary(12 , "일기3", "어제 열심히 먹었으니 오늘은 운동을 하러 가봐야게썽요 요즘 자세가 많이 무너져서 맘이 아픕니다. 중량.. 언제 다시 늘리지..? 하하 눈물이 흐르네요 여름맞이 운동 아자아자", LocalDateTime.now()))
        diaries.value = diariesList
    }

    override fun getALLDiaries(): LiveData<List<Diary>> {
        return diaries
    }

    override fun getDiary(id: Int): LiveData<Diary> {
        val diary = diariesList.find { it.id == id }
        return MutableLiveData(diary)
    }

    override suspend fun deleteDiary(id: Int) {
        diariesList.remove(getDiaryById(id))
        diaries.value = diariesList
    }

    override suspend fun insertDiary(diary: Diary) {
        diariesList.add(diary)
        diaries.value = diariesList
    }

    override suspend fun updateDiary(diary: Diary) {
        val index = diariesList.indexOfFirst { it.id == diary.id }
        if (index != -1) {
            diariesList[index] = diary
            diaries.value = diariesList
        }
    }

    override suspend fun getDiaryById(id: Int): Diary? {
        TODO("Not yet implemented")
    }
}
