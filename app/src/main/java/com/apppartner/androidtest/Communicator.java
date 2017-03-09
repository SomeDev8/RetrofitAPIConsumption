package com.apppartner.androidtest;

import android.util.Log;

import com.apppartner.androidtest.api.ChatLogMessageModel;
import com.apppartner.androidtest.api.EndPointInterface;
import com.apppartner.androidtest.bus.BusProvider;
import com.apppartner.androidtest.server.ErrorResponse;
import com.apppartner.androidtest.server.ServerEvent;
import com.apppartner.androidtest.server.ServerResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mr.alexander on 2/22/17.
 */


/**
 *  Regulates POST & GET requests initiated by the user through asynchronous methods. Data from the server is de-serialized by
 *  GSON and sent to the calling activity's handler methods.
 */

public class Communicator {

    //==============================================================================================
    // Class Properties
    //==============================================================================================

    private static final String TAG = "Communicator";
    private static final String SERVER_URL = "http://dev3.apppartner.com/";
    private String mUsername;
    private String mPassword;
    private Call<ServerResponse> mCallLogin;
    private Call <ChatLogMessageModel> mCallChat;
    private String mRequestType;
    private HttpLoggingInterceptor mHttpLoggingInterceptor;

    //==============================================================================================
    // Constructor
    //==============================================================================================

    public Communicator(String requestType) {
        mRequestType = requestType;
    }

    public Communicator(String requestType, String username, String password) {
        mUsername = username;
        mPassword = password;
        mRequestType = requestType;
    }

    //==============================================================================================
    // Class Instance Method
    //==============================================================================================

    public void executeRequest() {

        mHttpLoggingInterceptor = new HttpLoggingInterceptor();
        mHttpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(mHttpLoggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL)
                .build();

        EndPointInterface service = retrofit.create(EndPointInterface.class);

        if(mRequestType.equals("POST")) {
            mCallLogin = service.post(mUsername, mPassword);
            mCallLogin.enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    long responseTime = response.raw().receivedResponseAtMillis() - response.raw().sentRequestAtMillis();
                    BusProvider.getInstance().post(new ServerEvent(response.body(), responseTime));
                    Log.e(TAG, "Operation Successful");
                }

                @Override
                public void onFailure(Call<ServerResponse> call, Throwable t) {
                    BusProvider.getInstance().post(new ErrorResponse(t.getMessage()));
                    Log.e(TAG, "Operation Failed!");
                    t.printStackTrace();
                }
            });

        } else if (mRequestType.equals("GET")) {
            mCallChat = service.getChatLog();
            mCallChat.enqueue(new Callback<ChatLogMessageModel>() {
                @Override
                public void onResponse(Call<ChatLogMessageModel> call, Response<ChatLogMessageModel> response) {
                    BusProvider.getInstance().post(new ServerEvent(response.body()));
                    Log.e(TAG, "Operation Successful");
                }

                @Override
                public void onFailure(Call<ChatLogMessageModel> call, Throwable t) {
                    BusProvider.getInstance().post(new ErrorResponse(t.getMessage()));
                    Log.e(TAG, "Operation Failed!");
                    t.printStackTrace();
                }
            });
        }

    }

}



