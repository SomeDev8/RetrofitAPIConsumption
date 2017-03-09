package com.apppartner.androidtest.server;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mr.alexander on 2/22/17.
 */

public class ServerResponse {

    @SerializedName("message")
    private String mMessage;
    @SerializedName("code")
    private String mResponseCode;

    //==============================================================================================
    // Constructor
    //==============================================================================================

    public ServerResponse(String message, String responseCode) {
        mMessage = message;
        mResponseCode = responseCode;
    }

    //==============================================================================================
    // Getter Methods
    //==============================================================================================

    public String getMessage() {
        return mMessage;
    }

    public String getResponseCode() {
        return mResponseCode;
    }
}
