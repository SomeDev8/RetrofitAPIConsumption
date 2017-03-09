package com.apppartner.androidtest.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mr.alexander on 2/23/17.
 */

public class Data {

    //==============================================================================================
    // Class Properties
    //==============================================================================================

    @SerializedName("avatar_url")
    private String mAvatarUrl;
    @SerializedName("message")
    private String mMessage;

    //==============================================================================================
    // Setter & Getter methods
    //==============================================================================================

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setAvatarUrl(String mAvatarUrl) {
        this.mAvatarUrl = mAvatarUrl;
    }

    public void setMessage(String mMessage) {
        this.mMessage = mMessage;
    }
}