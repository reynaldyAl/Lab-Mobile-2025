package com.example.pertemuan_3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostFeedAdapter extends RecyclerView.Adapter<PostFeedAdapter.PostFeedViewHolder> {

    private Context context;
    private List<Post> postList;

    public PostFeedAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostFeedAdapter.PostFeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false);
        return new PostFeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostFeedAdapter.PostFeedViewHolder holder, int position) {
        Post post = postList.get(position);
        User user = post.getUser();

        holder.iv_profile.setImageResource(user.getProfileImage());
        holder.tv_username.setText(user.getUsername());
        holder.tv_timePost.setText(post.getTime());
        holder.tv_likes.setText(post.getLike());
        holder.tv_comments.setText(post.getComment());
        holder.tv_shares.setText(post.getShare());

        SpannableString spannableCaption = new SpannableString(user.getUsername() + ". " + post.getCaption());

        spannableCaption.setSpan(new StyleSpan(Typeface.BOLD), 0, user.getUsername().length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        holder.tv_caption.setText(spannableCaption);


        if (post.isFromUser()) {
            holder.iv_imagePost.setImageURI(post.getPostImageUri());
        } else {
            holder.iv_imagePost.setImageResource(post.getPostImage());
        }


        holder.btn_toProfilePage.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProfilePage.class);
            intent.putExtra("username", user.getUsername());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class PostFeedViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_profile, iv_imagePost;
        private TextView tv_username, tv_caption, tv_timePost, tv_likes, tv_comments, tv_shares;
        private LinearLayout btn_toProfilePage;

        public PostFeedViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_profile = itemView.findViewById(R.id.iv_profile);
            iv_imagePost = itemView.findViewById(R.id.iv_imagePost);
            tv_username = itemView.findViewById(R.id.tv_username);
            tv_caption = itemView.findViewById(R.id.tv_caption);
            tv_timePost = itemView.findViewById(R.id.tv_timePost);
            tv_likes = itemView.findViewById(R.id.tv_likes);
            tv_comments = itemView.findViewById(R.id.tv_comments);
            tv_shares = itemView.findViewById(R.id.tv_shares);
            btn_toProfilePage = itemView.findViewById(R.id.btn_toProfilePage);
        }
    }
}
