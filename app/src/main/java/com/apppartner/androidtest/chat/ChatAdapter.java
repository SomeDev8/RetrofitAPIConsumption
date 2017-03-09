package com.apppartner.androidtest.chat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apppartner.androidtest.R;
import com.apppartner.androidtest.api.Data;
import com.apppartner.androidtest.shape.DrawCircle;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * The ArrayList is passed to the adapter once the postExecute method of Async has completed.
 * Each view is populated with avatar and message data and recycled when it is no longer
 * on screen.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    //==============================================================================================
    // Class Properties
    //==============================================================================================

    private List<Data> mChatLogMessageModelList;
    private Context mContext;

    //==============================================================================================
    // Constructor
    //==============================================================================================

    public ChatAdapter(Context context) {
        this.mContext = context;
    }

    //==============================================================================================
    // Class Instance Methods
    //==============================================================================================

    public void setChatLogMessageModelList(ArrayList<Data> chatLogMessageModelList) {
        this.mChatLogMessageModelList = chatLogMessageModelList;
        notifyDataSetChanged();
    }

    //==============================================================================================
    // RecyclerView.Adapter Methods
    //==============================================================================================

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_chat, parent, false);

        return new ChatViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder viewHolder, int position) {

        Data chatLogMessageModel = mChatLogMessageModelList.get(position);
        viewHolder.messageTextView.setText(chatLogMessageModel.getMessage());
        Picasso.with(mContext)
                .load(chatLogMessageModel.getAvatarUrl())
                .transform(new DrawCircle(70, 0))
                .into(viewHolder.avatarImageView);

    }

    @Override
    public int getItemCount() {
        return mChatLogMessageModelList.size();
    }

    //==============================================================================================
    // ChatViewHolder Class
    //==============================================================================================

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        ImageView avatarImageView;
        TextView messageTextView;

        public ChatViewHolder(View view) {
            super(view);
            avatarImageView = (ImageView) view.findViewById(R.id.avatarImageView);
            messageTextView = (TextView) view.findViewById(R.id.messageTextView);
        }
    }

}
