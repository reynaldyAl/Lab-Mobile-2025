package com.example.pertemuan_3;

import android.content.Intent;
import android.os.Bundle;
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

public class PostDetail extends AppCompatActivity {

    RecyclerView rv_post;
    PostFeedAdapter postFeedAdapter;
    List<Post> userPosts;
    ImageButton ib_back, ib_addPost, ib_home;
    ImageView ib_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_post_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ib_back = findViewById(R.id.ib_back);
        ib_back.setOnClickListener(v -> finish());

        ib_addPost = findViewById(R.id.ib_addPost);
        ib_addPost.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddPost.class);
            startActivity(intent);
        });

        ib_home = findViewById(R.id.ib_home);
        ib_home.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        ib_profile = findViewById(R.id.ib_profile);
        ib_profile.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProfilePage.class);
            intent.putExtra("username", "jane.kend");
            startActivity(intent);
        });

        int selectedIndex = getIntent().getIntExtra("selectedIndex", 0);
        String username = getIntent().getStringExtra("username");

        userPosts = PostDataSource.generateDummyPostProfilePage(username);
        rv_post = findViewById(R.id.rv_post);
        postFeedAdapter = new PostFeedAdapter(this, userPosts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_post.setLayoutManager(layoutManager);
        rv_post.setAdapter(postFeedAdapter);

        rv_post.scrollToPosition(selectedIndex);
    }
}