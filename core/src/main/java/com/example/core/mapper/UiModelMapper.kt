package com.example.core.mapper

import android.util.Log
import com.example.core.model.Club
import com.example.core.model.ClubInfo
import com.example.core.model.JoinedClub
import com.example.core.model.JoinedClubData
import com.example.core.model.JoinedClubInfo
import com.example.core.model.LocalScreen
import com.example.core.model.LocationInfo
import com.example.core.model.MainHomeScheduleUiModel
import com.example.core.model.MainHomeStudentDataUiModel
import com.example.core.model.MainSchedule
import com.example.core.model.Map
import com.example.core.model.MemberUiModel
import com.example.core.model.MyPageUserInfoUiModel
import com.example.core.model.Position
import com.example.core.model.PositionPresetUIModel
import com.example.core.model.SignOut
import com.example.core.model.StudentUiModel
import com.example.core.model.Team
import com.example.core.model.UserTeamInfoModel
import com.example.network_api.entity.Member
import com.example.network_api.entity.PositionPreset
import com.example.network_api.entity.RemoteScreen
import com.example.network_api.response.ClubInfoResponse
import com.example.network_api.response.JoinedClubDataResponse
import com.example.network_api.response.JoinedClubInfoResponse
import com.example.network_api.response.JoinedClubResponse
import com.example.network_api.response.LocationInfoResponse
import com.example.network_api.response.MainHomeScheduleResponse
import com.example.network_api.response.MainHomeStudentDataResponse
import com.example.network_api.response.MainScheduleResponse
import com.example.network_api.response.MapResponse
import com.example.network_api.response.RespResult
import com.example.network_api.response.SearchClubResponse
import com.example.network_api.response.SignOutResponse
import com.example.network_api.response.Student
import com.example.network_api.response.TeamResponse
import com.example.network_api.response.UserInfoResponse
import com.example.network_api.response.UserTeamInfo

object UiModelMapper {
    fun PositionPreset.mapToUiModel() =
        PositionPresetUIModel(
            screenSize = this.screenSize.mapToUiModel(),
            members = this.members.map { it.mapToUiModel() }
        )

    fun RemoteScreen.mapToUiModel() =
        LocalScreen(this.width, this.height)

    fun com.example.network_api.entity.Position.mapToUiModel() = Position(
        x = this.x,
        y = this.y
    )

    fun Member.mapToUiModel() = MemberUiModel(
        id = this.id,
        name = this.name,
        role = this.role,
        number = this.number,
        position = this.position.mapToUiModel()
    )

    fun RespResult<MainHomeStudentDataResponse>.mapToUiModel(): MainHomeStudentDataUiModel {
        return when (this) {
            is RespResult.Success -> {
                MainHomeStudentDataUiModel(
                    status = data.status,
                    code = data.code ?: "null",
                    message = data.message,
                    data = data.data.mapToUiModel()
                )
            }

            is RespResult.Error -> {
                MainHomeStudentDataUiModel(
                    status = -1,
                    code = this.error.code ?: "error",
                    message = this.error.errorMessage,
                    data = StudentUiModel(
                        name = "error",
                        game = 0,
                        goal = 0,
                        position = "error",
                        foot = "0",
                        image = "",
                        age = 0
                    )
                )
            }

        }
    }

    fun Student.mapToUiModel() = StudentUiModel(
        name = this.name,
        game = this.game,
        goal = this.goal,
        position = this.position,
        foot = this.foot,
        image = this.image,
        age = this.age
    )


    fun RespResult<UserInfoResponse>.mapToUiModel(): MyPageUserInfoUiModel {
        return when (this) {
            is RespResult.Success -> {
                MyPageUserInfoUiModel(
                    studentId = data.userData.studentId,
                    name = data.userData.name,
                    image = data.userData.image
                )
            }

            is RespResult.Error -> {
                MyPageUserInfoUiModel(
                    studentId = "err",
                    name = "err",
                    image = null
                )
            }
        }
    }


    fun RespResult<SearchClubResponse>.mapToUiModel(): Club {
        return when (this) {
            is RespResult.Success -> {
                Club(
                    status = data.status,
                    message = data.message,
                    data = data.data.map { it.mapToUiModel() }
                )
            }

            is RespResult.Error -> {
                Club(
                    status = 0,
                    message = this.error.errorMessage,
                    data = listOf(
                        ClubInfo(
                            teamId = -1,
                            teamName = "err",
                            totalMemberCnt = 0,
                            details = "",
                            uniqueNum = "err",
                            emblem = ""
                        )
                    )
                )
            }
        }
    }

    fun RespResult<MapResponse>.mapToUiModel(): Map {
        return when (this) {
            is RespResult.Success -> {
                Map(
                    lastBuildDate = data.lastBuildDate,
                    total = data.total,
                    start = data.start,
                    display = data.display,
                    items = data.items.map{it.mapToUiModel()}

                )
            }

            is RespResult.Error -> {
                Map(
                    lastBuildDate = "0",
                    total = 0,
                    start = 0,
                    display = 0,
                    items = listOf(
                        LocationInfo(
                            address = "1",
                            title = "1",
                            link = "1",
                            category = "1",
                            description = "1",
                            telephone = "1",
                            roadAddress = "1",
                            mapx = "1",
                            mapy = "1"
                        )
                    )
                )


            }

        }
    }


    fun ClubInfoResponse.mapToUiModel() = ClubInfo(
        teamId = this.teamId,
        teamName = this.teamName,
        totalMemberCnt = this.totalMemberCnt,
        details = this.details,
        uniqueNum = this.uniqueNum,
        emblem = this.emblem
    )

    fun LocationInfoResponse.mapToUiModel() = LocationInfo(
        title = this.title,
        link = this.link,
        category = this.category,
        description = this.description,
        telephone = this.telephone,
        address = this.address,
        roadAddress = this.roadAddress,
        mapx = this.mapx,
        mapy = this.mapy
    )

    fun MainScheduleResponse.mapToUiModel() = MainSchedule(
        place = this.place,
        startTime = this.startTime,
        homeTeam = this.homeTeam.mapToUiModel(),
        awayTeam = this.awayTeam.mapToUiModel()
    )

    fun TeamResponse.mapToUiModel() = Team(
        name = this.name,
        emblem = this.emblem
    )

    fun RespResult<MainHomeScheduleResponse>.mapToUiModel(): MainHomeScheduleUiModel {
        return when (this) {
            is RespResult.Success -> {
                MainHomeScheduleUiModel(
                    status = data.status,
                    code = data.code ?: "",
                    message = data.message,
                    data = data.data.map { it?.mapToUiModel() }
                )
            }

            is RespResult.Error -> {
                MainHomeScheduleUiModel(
                    status = -1,
                    code = this.error.code ?: "Unknown code",
                    message = this.error.errorMessage,
                    data = emptyList()
                )
            }
        }
    }

    fun RespResult<JoinedClubInfoResponse>.mapToUiModel(): JoinedClubInfo {
        return when (this) {
            is RespResult.Success -> {
                JoinedClubInfo(
                    status = data.status,
                    code = data.code ?: "",
                    message = data.message,
                    data = data.data.map { it.mapToUiModel() }
                )
            }

            is RespResult.Error -> {
                JoinedClubInfo(
                    status = -1,
                    code = this.error.code ?: "Unknown code",
                    message = this.error.errorMessage,
                    data = emptyList()
                )
            }
        }
    }

    fun JoinedClubDataResponse.mapToUiModel() = JoinedClubData(
        id = this.id,
        uniqueNum = this.uniqueNum
    )

    fun RespResult<JoinedClubResponse>.mapToUiModel(): JoinedClub {
        return when (this) {
            is RespResult.Success -> {
                Log.e("test", data.data.toString())
                JoinedClub(
                    status = data.status,
                    message = data.message,
                    data = data.data.map {
                        it.mapToUiModel()
                    }
                )
            }

            is RespResult.Error -> {
                JoinedClub(
                    status = -1,
                    message = this.error.code ?: "Unknown code",
                    data = emptyList()
                )
            }
        }
    }

    fun UserTeamInfo.mapToUiModel() = UserTeamInfoModel(
        role = this.role,
        introduce = this.introduce,
        teamName = this.teamName,
        unique_num = this.unique_num,
        teamEmblem = this.teamEmblem,
        createdAt = this.createdAt,
        sizeOfUsers = this.sizeOfUsers
    )

    fun RespResult<SignOutResponse>.mapToUiModel(): SignOut {
        return when (this) {
            is RespResult.Success -> {
                SignOut(
                    status = data.status,
                    message = data.message
                )
            }

            is RespResult.Error -> {
                SignOut(
                    status = this.error.code.toString() ?: "",
                    message = this.error.errorMessage
                )
            }
        }
    }
}
