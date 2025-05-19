package com.example.pertemuan_3;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class StoryDetail extends AppCompatActivity {

    private ProgressBar progressBar;
    private Handler handler = new Handler();
    private Runnable progressRunnable;
    private String source;
    private String highlightUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_story_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String title = intent.getStringExtra("title");
        String time = intent.getStringExtra("time");
        int profileImage = intent.getIntExtra("profileImage", -1);
        int storyImage = intent.getIntExtra("storyImage", -1);

        source = getIntent().getStringExtra("source");

        if ("highlight".equals(source)) {
            highlightUsername = getIntent().getStringExtra("highlightUsername");
        }

        ImageView iv_profile = findViewById(R.id.iv_profile);
        ImageView iv_storyImage = findViewById(R.id.iv_imageStory);
        TextView tv_username = findViewById(R.id.tv_username);
        TextView tv_timeStory = findViewById(R.id.tv_timeStory);
        LinearLayout btn_toProfilePage = findViewById(R.id.btn_toProfilePage);

        iv_profile.setImageResource(profileImage);
        iv_storyImage.setImageResource(storyImage);
        tv_timeStory.setText(time);

        if (title != null && !title.isEmpty()) {
            tv_username.setText(title);
        } else {
            tv_username.setText(username);
            btn_toProfilePage.setOnClickListener(v -> {
                handler.removeCallbacks(progressRunnable);

                Intent intentToProfilePage = new Intent(this, ProfilePage.class);
                intentToProfilePage.putExtra("username", username);
                startActivity(intentToProfilePage);

                finish();
            });
        }

        progressBar = findViewById(R.id.progressBar);

        ObjectAnimator animator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);
        animator.setDuration(5000);
        animator.start();

        progressRunnable = this::goToNextStory;
        handler.postDelayed(progressRunnable, 5000);
    }

    private void goToNextStory() {
        int currentIndex = getIntent().getIntExtra("storyIndex", 0);
        int nextIndex = currentIndex + 1;

        List<Story> stories;

        if ("highlight".equals(source)) {
            stories = StoryDataSource.generateDummyStoryHighlights(highlightUsername);
        } else {
            stories = StoryDataSource.generateDummyStoriesFeed();
        }

        if (nextIndex < stories.size()) {
            Story nextStory = stories.get(nextIndex);
            Intent nextIntent = new Intent(this, StoryDetail.class);
            nextIntent.putExtra("storyIndex", nextIndex);
            nextIntent.putExtra("source", source);

            if ("highlight".equals(source)) {
                nextIntent.putExtra("highlightUsername", highlightUsername);
                nextIntent.putExtra("title", nextStory.getTitle());
            } else {
                nextIntent.putExtra("username", nextStory.getUser().getUsername());
            }

            nextIntent.putExtra("time", nextStory.getTime());
            nextIntent.putExtra("profileImage", nextStory.getUser().getProfileImage());
            nextIntent.putExtra("storyImage", nextStory.getStoryImage());

            startActivity(nextIntent);
            finish();
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(progressRunnable);
    }

    @Override
    public void onBackPressed() {
        handler.removeCallbacks(progressRunnable);
        super.onBackPressed();
    }

}