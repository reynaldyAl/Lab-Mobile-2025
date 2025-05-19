package com.example.pertemuan_3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StoryFeedAdapter extends RecyclerView.Adapter<StoryFeedAdapter.StoryViewHolder> {

    private Context context;
    private List<Story> storyList;

    public StoryFeedAdapter(Context context, List<Story> storyList) {
        this.context = context;
        this.storyList = storyList;
    }

    @NonNull
    @Override
    public StoryFeedAdapter.StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_story, parent, false);
        return new StoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryFeedAdapter.StoryViewHolder holder, int position) {
        Story story = storyList.get(position);

        User user = story.getUser();
        if (user != null) {
            holder.tv_username.setText(user.getUsername());
            holder.iv_profile.setImageResource(user.getProfileImage());
        } else {
            holder.tv_username.setText("Unknown");
        }

        if (position == 0) {
            holder.tv_username.setText("Your story");
            holder.iv_plus.setVisibility(View.VISIBLE);
        } else {
            holder.iv_plus.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, StoryDetail.class);
            intent.putExtra("source", "feed");
            intent.putExtra("storyIndex", position);
            intent.putExtra("username", story.getUser().getUsername());
            intent.putExtra("time", story.getTime());
            intent.putExtra("profileImage", story.getUser().getProfileImage());
            intent.putExtra("storyImage", story.getStoryImage());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return storyList.size();
    }

    public static class StoryViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_profile;
        private TextView tv_username;
        private ImageView iv_plus;

        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_profile = itemView.findViewById(R.id.iv_profile);
            tv_username = itemView.findViewById(R.id.tv_username);
            iv_plus = itemView.findViewById(R.id.iv_plus);
        }
    }
}
