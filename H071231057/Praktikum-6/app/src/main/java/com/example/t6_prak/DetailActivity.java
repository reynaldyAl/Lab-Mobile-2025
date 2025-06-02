package com.example.t6_prak;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.t6_prak.model.Character;
import com.example.t6_prak.network.ApiService;
import com.example.t6_prak.network.RetrofitClient;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView nameTextView, statusTextView, speciesTextView, genderTextView;
    private ScrollView contentLayout;
    private LinearLayout errorLayout;
    private Button btnRefresh;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;

    // ID karakter yang akan ditampilkan
    private int characterId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Inisialisasi views
        imageView = findViewById(R.id.detailImageView);
        nameTextView = findViewById(R.id.detailNameTextView);
        statusTextView = findViewById(R.id.detailStatusTextView);
        speciesTextView = findViewById(R.id.detailSpeciesTextView);
        genderTextView = findViewById(R.id.detailGenderTextView);

        // Inisialisasi layout error
        contentLayout = findViewById(R.id.detailContentLayout);
        errorLayout = findViewById(R.id.errorLayoutDetail);
        btnRefresh = findViewById(R.id.btnRefreshDetail);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayoutDetail);

        // Tambahkan ProgressBar jika belum ada
        // progressBar = findViewById(R.id.progressBarDetail);

        // Configure SwipeRefreshLayout
        swipeRefreshLayout.setColorSchemeResources(R.color.green);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            loadCharacterData();
        });

        // Mengambil character_id dari intent
        characterId = getIntent().getIntExtra("character_id", -1);

        if (characterId == -1) {
            // Jika tidak ada ID valid, tampilkan error
            Toast.makeText(this, "Invalid character ID", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Menambahkan listener untuk refresh
        btnRefresh.setOnClickListener(v -> {
            loadCharacterData();
        });

        // Load data karakter
        loadCharacterData();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void showErrorLayout(boolean show) {
        if (show) {
            errorLayout.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setVisibility(View.GONE);
        } else {
            errorLayout.setVisibility(View.GONE);
            swipeRefreshLayout.setVisibility(View.VISIBLE);
        }
    }

    private void loadCharacterData() {
        // Cek koneksi internet
        if (!isNetworkAvailable()) {
            swipeRefreshLayout.setRefreshing(false);
            showErrorLayout(true);
            return;
        } else {
            showErrorLayout(false);
        }

        swipeRefreshLayout.setRefreshing(true);

        // Buat request ke API untuk mendapatkan detail karakter
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<Character> call = apiService.getCharacterById(characterId);

        call.enqueue(new Callback<Character>() {
            @Override
            public void onResponse(Call<Character> call, Response<Character> response) {
                swipeRefreshLayout.setRefreshing(false);

                if (response.isSuccessful() && response.body() != null) {
                    Character character = response.body();

                    // Set data ke views
                    nameTextView.setText(character.getName());
                    statusTextView.setText(character.getStatus());
                    speciesTextView.setText(character.getSpecies());
                    genderTextView.setText(character.getGender());

                    // Load gambar dengan Picasso
                    Picasso.get()
                            .load(character.getImage())
                            .placeholder(R.drawable.placeholder_image)
                            .error(R.drawable.error_image)
                            .into(imageView);
                } else {
                    // Handle error response
                    Toast.makeText(DetailActivity.this, "Failed to load character data", Toast.LENGTH_SHORT).show();
                    showErrorLayout(true);
                }
            }

            @Override
            public void onFailure(Call<Character> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                showErrorLayout(true);
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}