package com.example.pertemuan_3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostGridAdapter extends RecyclerView.Adapter<PostGridAdapter.PostGridViewHolder> {

    private Context context;

    private List<Post> postList;

    public PostGridAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostGridAdapter.PostGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gridpost, parent, false);
        return new PostGridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostGridAdapter.PostGridViewHolder holder, int position) {
        Post post = postList.get(position);

        if (post.isFromUser()) {
            holder.iv_imagePost.setImageURI(post.getPostImageUri());
        } else {
            holder.iv_imagePost.setImageResource(post.getPostImage());
        }

        holder.itemView.setOnClickListener(v-> {
            Intent intent = new Intent(context, PostDetail.class);
            intent.putExtra("selectedIndex", position);
            intent.putExtra("username", post.getUser().getUsername());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class PostGridViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_imagePost;
        public PostGridViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_imagePost = itemView.findViewById(R.id.iv_imagePost);
        }
    }
}
