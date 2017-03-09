package com.apppartner.androidtest.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Getters and Setters for each attribute of the ChatMessageModel class
 */
public class ChatLogMessageModel {

    //==============================================================================================
    // Class Properties
    //==============================================================================================
    @SerializedName("data")
    private ArrayList<Data> mData;

    //==============================================================================================
    // Class Instance Methods
    //==============================================================================================

    public ArrayList<Data> getData() {
        return mData;
    }

}

