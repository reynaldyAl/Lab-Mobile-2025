package com.example.pertemuan_3;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProfilePage extends AppCompatActivity {

    ImageView iv_profile;
    TextView tv_username, tv_name, tv_career, tv_bio, tv_link, tv_postsCount, tv_followersCount, tv_followingCount;
    RecyclerView rv_storyHighlight, rv_postGrid;
    Button btn_following;
    ImageButton ib_back, ib_addPost, ib_home;
    ImageView ib_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String username = getIntent().getStringExtra("username");

        User user = UserDataSource.getUserByUsername(username);
        if (user == null) {
            Toast.makeText(this, "User tidak ditemukan", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        iv_profile = findViewById(R.id.iv_profile);
        tv_username = findViewById(R.id.tv_username);
        tv_name = findViewById(R.id.tv_nameProfile);
        tv_career = findViewById(R.id.tv_career);
        tv_bio = findViewById(R.id.tv_bio);
        tv_link = findViewById(R.id.tv_link);
        tv_postsCount = findViewById(R.id.tv_postsCount);
        tv_followersCount = findViewById(R.id.tv_followersCount);
        tv_followingCount = findViewById(R.id.tv_followingCount);
        rv_storyHighlight = findViewById(R.id.rv_storyHighlight);
        rv_postGrid = findViewById(R.id.rv_postGrid);
        btn_following = findViewById(R.id.btn_following);
        ib_back = findViewById(R.id.ib_back);
        ib_addPost = findViewById(R.id.ib_addPost);
        ib_home = findViewById(R.id.ib_home);

        ib_home.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        ib_back.setOnClickListener(v -> finish() );

        ib_addPost.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddPost.class);
            startActivity(intent);
        });

        ib_profile = findViewById(R.id.ib_profile);
        ib_profile.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProfilePage.class);
            intent.putExtra("username", "jane.kend");
            startActivity(intent);
        });

        if (user.getUsername().equals("jane.kend")) {
            btn_following.setText("Edit profile");
        }

        tv_link.setMovementMethod(LinkMovementMethod.getInstance());

        tv_username.setText(username);
        tv_name.setText(user.getName());
        tv_career.setText(user.getCareer());
        tv_bio.setText(user.getBio());
        tv_link.setText(user.getLink());
        iv_profile.setImageResource(user.getProfileImage());
        tv_followersCount.setText(user.getFollowers());
        tv_followingCount.setText(user.getFollowing());

        int postCount = PostDataSource.generateDummyPostProfilePage(username).size();
        tv_postsCount.setText(String.valueOf(postCount));

        StoryHighlightAdapter highlightAdapter = new StoryHighlightAdapter(this, StoryDataSource.generateDummyStoryHighlights(username));
        rv_storyHighlight.setAdapter(highlightAdapter);
        rv_storyHighlight.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        PostGridAdapter postGridAdapter = new PostGridAdapter(this, PostDataSource.generateDummyPostProfilePage(username));

        rv_postGrid.setAdapter(postGridAdapter);
        rv_postGrid.setLayoutManager(new GridLayoutManager(this, 3));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.grid_spacing);
        rv_postGrid.addItemDecoration(new GridSpacingItemDecoration(3, spacingInPixels, true));

    }
}