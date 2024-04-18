package com.sejongunivclub.network.repository

import com.sejongunivclub.network_api.entity.Member
import com.sejongunivclub.network_api.entity.Position
import com.sejongunivclub.network_api.entity.PositionPreset
import com.sejongunivclub.network_api.entity.RemoteScreen
import com.sejongunivclub.network_api.repository.TempSquadRepository
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

//추후 API로 대체
@Singleton
internal class TempSquadRepositoryImpl @Inject constructor(
    private val screen: RemoteScreen
) : TempSquadRepository {
    private val tempMemberStore = MutableStateFlow<List<Member>>(listOf())

    init {
        tempMemberStore.value = generateDummyMembers(11)
    }

    //스크린 크기는 nexus 4 임시로 설정
    override suspend fun loadMyCustomSquadPreset(): PositionPreset {
        return PositionPreset(
            screenSize = screen, //기기 변동이 있을 경우를 대비하여 DB에 저장된 스크린 사이즈 필요
            members = tempMemberStore.value
        )
    }

    override suspend fun loadOtherUserCustomSquadPreset(): PositionPreset {
        return PositionPreset(screenSize = RemoteScreen(768.0, 1280.0), members = tempMemberStore.value)
    }

    override suspend fun saveMyCustomSquadPreset(positionPreset: PositionPreset) {
        tempMemberStore.value = positionPreset.members
    }

    // 랜덤한 Position 객체를 생성하는 함수 (실제 코드로 대체해야 함)
    fun generateDummyMembers(numMembers: Int): List<Member> {
        return List(numMembers) { createDummyMember(it) }
    }

    fun createDummyMember(index: Int): Member {
        return Member(
            id = "ID${index}",
            name = "Member$index",
            number = "010-1234-${String.format("%04d", index)}",
            role = listOf("Leader", "Member").random(),
            position = Position(Random.nextFloat() * 1000, Random.nextFloat() * 1000)
        )
    }

}