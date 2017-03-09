package com.apppartner.androidtest.server;

import com.apppartner.androidtest.api.ChatLogMessageModel;

/**
 * Created by mr.alexander on 2/22/17.
 */

/**
 *  Class is used for generating event responses with data from server,
 *  which will be assessed by handlers.
 */

public class ServerEvent {

    //==============================================================================================
    // Class Properties
    //==============================================================================================

    private ServerResponse mServerResponse;
    private ChatLogMessageModel mChatLogMessageModel;
    private long mResponseTime;

    //==============================================================================================
    // Constructor
    //==============================================================================================

    public ServerEvent(ServerResponse serverResponse, long responseTime) {
        mResponseTime = responseTime;
        mServerResponse = serverResponse;
    }

    public ServerEvent(ChatLogMessageModel chatLogMessageModel) {
        mChatLogMessageModel = chatLogMessageModel;
    }

    //==============================================================================================
    // Getter Methods
    //==============================================================================================


    public ServerResponse getServerResponse(){
        return mServerResponse;
    }

    public long getResponseTime() {
        return mResponseTime;
    }

    public ChatLogMessageModel getChatLogMessageModel() {
        return mChatLogMessageModel;
    }

}
