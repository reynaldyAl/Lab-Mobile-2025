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

public class StoryHighlightAdapter extends RecyclerView.Adapter<StoryHighlightAdapter.HighlightViewHolder> {

    private Context context;
    private List<Story> highlightList;

    public StoryHighlightAdapter(Context context, List<Story> highlightList) {
        this.context = context;
        this.highlightList = highlightList;
    }

    @NonNull
    @Override
    public StoryHighlightAdapter.HighlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_story_highlight, parent, false);
        return new HighlightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryHighlightAdapter.HighlightViewHolder holder, int position) {
        Story story = highlightList.get(position);

        User user = story.getUser();
        if (user != null) {
            holder.tv_storyHighlightTitle.setText(story.getTitle());
            holder.iv_storyImage.setImageResource(story.getStoryImage());
        } else {
            holder.tv_storyHighlightTitle.setText("Unknown");
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, StoryDetail.class);

            intent.putExtra("source", "highlight");
            intent.putExtra("highlightUsername", story.getUser().getUsername());
            intent.putExtra("storyIndex", position);
            intent.putExtra("title", story.getTitle());
            intent.putExtra("time", story.getTime());
            intent.putExtra("profileImage", story.getUser().getProfileImage());
            intent.putExtra("storyImage", story.getStoryImage());


            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return highlightList.size();
    }

    public static class HighlightViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_storyImage;
        TextView tv_storyHighlightTitle;

        public HighlightViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_storyImage = itemView.findViewById(R.id.iv_storyImage);
            tv_storyHighlightTitle = itemView.findViewById(R.id.tv_storyHighlightTitle);
        }
    }
}
