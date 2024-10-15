package com.gkp.core.network

import com.gkp.core.network.model.AgendaResponse
import com.gkp.core.network.model.EventBody
import com.gkp.core.network.model.EventResponse
import com.gkp.core.network.model.LoginBody
import com.gkp.core.network.model.LoginResponse
import com.gkp.core.network.model.RefreshTokenBody
import com.gkp.core.network.model.RefreshTokenResponse
import com.gkp.core.network.model.RegisterBody
import com.gkp.core.network.model.ReminderBody
import com.gkp.core.network.model.ReminderResponse
import com.gkp.core.network.model.TaskBody
import com.gkp.core.network.model.TaskResponse
import com.gkp.network.BuildConfig
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Query

interface TaskyRetrofitApi {
    @POST("/register")
    suspend fun register(
       @Body registerBody: RegisterBody
    )

    @POST("/login")
    suspend fun login(
        @Body loginBody: LoginBody
    ) : LoginResponse

    @POST("/accessToken")
    suspend fun refreshToken(
        @Body refreshTokenBody: RefreshTokenBody,
    ) : RefreshTokenResponse

    @GET("/logout")
    suspend fun logout()

    @GET("/agenda")
    suspend fun getAgenda(
        @Query("time") time: Long = System.currentTimeMillis()
    ): AgendaResponse

    @POST("/task")
    suspend fun createTask(
        @Body taskBody: TaskBody
    )

    @GET("/task")
    suspend fun getTask(
        @Query("taskId") id: String
    ) : TaskResponse

    @POST("/reminder")
    suspend fun createReminder(
        @Body reminderBody: ReminderBody
    )

    @GET("/reminder")
    suspend fun getReminder(
        @Query("reminderId") id: String
    ) : ReminderResponse

    @Multipart
    @POST("/event")
    @JvmSuppressWildcards
    suspend fun createEvent(
        @Part eventBodyPart: MultipartBody.Part,
        @Part photosPart: List<MultipartBody.Part>
    ) :EventResponse

}