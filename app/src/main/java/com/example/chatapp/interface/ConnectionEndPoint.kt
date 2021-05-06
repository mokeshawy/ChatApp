package com.example.chatapp.`interface`

import com.example.chatapp.model.PushNotificationModel
import com.example.chatapp.util.Constants
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ConnectionEndPoint {

    @Headers("Authorization : key =${Constants.SERVICE_KEY}" , "ContentType : ${Constants.CONTENT_TYPE}")
    @POST("fcm/send ")
    suspend fun postNotification(
        @Body notification : PushNotificationModel
    ) : Response<ResponseBody>

}