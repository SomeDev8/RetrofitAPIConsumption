package com.apppartner.androidtest.api;

import com.apppartner.androidtest.server.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by mr.alexander on 2/22/17.
 */

/**
 * All HTTP request methods are defined by this interface.
 */

public interface EndPointInterface {

    //==============================================================================================
    // HTTP Request Methods
    //==============================================================================================

    @FormUrlEncoded
    @POST("AppPartnerDeveloperTest/scripts/login.php")
    Call<ServerResponse> post(
            @Field("username") String username,
            @Field ("password") String password
    );

    @GET("AppPartnerDeveloperTest/scripts/chat_log.php")
    Call<ChatLogMessageModel> getChatLog();
}