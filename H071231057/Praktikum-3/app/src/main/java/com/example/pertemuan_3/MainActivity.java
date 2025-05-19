package com.example.pertemuan_3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView ib_profile;
    ImageButton ib_addPost;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        try {
            UserDataSource.getAllUsers(this);
            RecyclerView rv_storyFeed = findViewById(R.id.rv_story);
            StoryFeedAdapter storyFeedAdapter = new StoryFeedAdapter(this, StoryDataSource.generateDummyStoriesFeed());
            rv_storyFeed.setAdapter(storyFeedAdapter);
            rv_storyFeed.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

            List<Post> feedPosts = PostDataSource.generateDummyPostsFeed();
            PostFeedAdapter adapter = new PostFeedAdapter(this, feedPosts);
            RecyclerView rv_feed = findViewById(R.id.rv_feed);
            rv_feed.setLayoutManager(new LinearLayoutManager(this));
            rv_feed.setAdapter(adapter);
        } catch (Exception e) {
            Log.e("MainActivityError", "Error saat onCreate: ", e);
        }

        ib_profile = findViewById(R.id.ib_profile);
        ib_addPost = findViewById(R.id.ib_addPost);

        ib_profile.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProfilePage.class);
            intent.putExtra("username", "jane.kend");
            startActivity(intent);
        });

        ib_addPost.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddPost.class);
            startActivity(intent);
        });


    }
}