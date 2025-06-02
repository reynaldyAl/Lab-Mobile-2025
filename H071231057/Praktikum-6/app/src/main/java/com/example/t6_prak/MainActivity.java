package com.example.t6_prak;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.t6_prak.adapter.CharAdapter;
import com.example.t6_prak.model.Character;
import com.example.t6_prak.model.CharacterResponse;
import com.example.t6_prak.network.ApiService;
import com.example.t6_prak.network.OnLoadMoreListener;
import com.example.t6_prak.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private RecyclerView recyclerView;
    private CharAdapter adapter;
    private List<Character> characterList = new ArrayList<>();
    private List<Character> filteredList = new ArrayList<>();
    private int currentPage = 1;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;

    private LinearLayout errorLayout;
    private Button btnRefresh;
    private TextView errorTextView;
    private boolean isSearching = false;
    private String currentQuery = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        errorLayout = findViewById(R.id.errorLayout);
        btnRefresh = findViewById(R.id.btnRefresh);
        errorTextView = findViewById(R.id.errorTextView);

        btnRefresh.setOnClickListener(v -> {
            resetSearch();
            loadCharacters();
        });

        // SwipeRefreshLayout config
        swipeRefreshLayout.setColorSchemeResources(R.color.green);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            resetSearch();
            currentPage = 1;
            characterList.clear();
            loadCharacters();
        });

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new CharAdapter(characterList);
        adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMoreClicked() {
                if (!isSearching) {
                    loadMoreCharacters();
                }
            }
        });
        recyclerView.setAdapter(adapter);

        loadCharacters();
    }

    private void resetSearch() {
        isSearching = false;
        currentQuery = "";
        filteredList.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        filterCharacters(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText.isEmpty() && isSearching) {
            resetSearch();
            adapter = new CharAdapter(characterList);
            adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMoreClicked() {
                    loadMoreCharacters();
                }
            });
            recyclerView.setAdapter(adapter);
        } else if (!newText.isEmpty()) {
            filterCharacters(newText);
        }
        return true;
    }

    private void filterCharacters(String query) {
        isSearching = true;
        currentQuery = query.toLowerCase();
        
        filteredList.clear();
        for (Character character : characterList) {
            if (character.getName().toLowerCase().contains(currentQuery)) {
                filteredList.add(character);
            }
        }
        
        adapter = new CharAdapter(filteredList);
        recyclerView.setAdapter(adapter);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void showErrorLayout(boolean show) {
        if (show) {
            errorLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            swipeRefreshLayout.setVisibility(View.GONE);
        } else {
            errorLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setVisibility(View.VISIBLE);
        }
    }

    private void loadCharacters() {
        if (!isNetworkAvailable()) {
            swipeRefreshLayout.setRefreshing(false);
            showErrorLayout(true);
            return;
        } else {
            showErrorLayout(false);
        }

        if (currentPage == 1) {
            progressBar.setVisibility(View.VISIBLE);
        }
        // di detail api end point kedua

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<CharacterResponse> call = apiService.getCharacters(currentPage);

        call.enqueue(new Callback<CharacterResponse>() {
            @Override
            public void onResponse(Call<CharacterResponse> call, Response<CharacterResponse> response) {
                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);

                if (response.isSuccessful() && response.body() != null) {
                    List<Character> newCharacters = response.body().getResults();
                    characterList.addAll(newCharacters);
                    
                    // If we're searching, update filtered list too
                    if (isSearching) {
                        for (Character character : newCharacters) {
                            if (character.getName().toLowerCase().contains(currentQuery)) {
                                filteredList.add(character);
                            }
                        }
                    }

                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CharacterResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
                showErrorLayout(true);
                errorTextView.setText("Tidak dapat terhubung ke internet");
            }
        });
    }

// Tambahkan metode baru untuk menampilkan pesan error tanpa menyembunyikan data yang sudah ada

    private void showLoadMoreError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void loadMoreCharacters() {
        if (!isNetworkAvailable()) {
            showLoadMoreError("Tidak ada jaringan, silahkan hubungkan kembali");
            return;
        }

        currentPage++;
        loadCharacters();
    }


}