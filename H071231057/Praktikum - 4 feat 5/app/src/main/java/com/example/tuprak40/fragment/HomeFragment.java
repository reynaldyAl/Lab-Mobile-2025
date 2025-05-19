package com.example.tuprak40.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tuprak40.models.Book;
import com.example.tuprak40.repository.BookRepository;
import com.example.tuprak40.DetailActivity;
import com.example.tuprak40.R;
import com.example.tuprak40.adapter.BookAdapter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private SearchView searchView;
    private View progressBar;
    private ChipGroup genreChipGroup;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    // Track selected genre and current search query
    private String selectedGenre = null;
    private String currentQuery = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        searchView = view.findViewById(R.id.search_view);
        progressBar = view.findViewById(R.id.progress_bar);
        genreChipGroup = view.findViewById(R.id.genre_chip_group);

        setupRecyclerView();
        setupSearchView();
        loadBooks();

        return view;
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BookAdapter(book -> {
            Intent intent = new Intent(getContext(), DetailActivity.class);
            intent.putExtra("BOOK_ID", book.getId());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    private void setupSearchView() {
        // Log untuk debugging
        Log.d("HomeFragment", "Setting up SearchView");

        // Kustomisasi tampilan SearchView secara programatis
        try {
            // Set warna ikon search
            ImageView searchIcon = searchView.findViewById(androidx.appcompat.R.id.search_mag_icon);
            if (searchIcon != null) {
                searchIcon.setColorFilter(Color.WHITE);
                Log.d("HomeFragment", "Search icon customized");
            }

            // Set warna teks input
            EditText searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
            if (searchEditText != null) {
                searchEditText.setTextColor(Color.WHITE);
                searchEditText.setHintTextColor(Color.parseColor("#80FFFFFF"));
                Log.d("HomeFragment", "Search edit text customized");
            }
        } catch (Exception e) {
            Log.e("HomeFragment", "Error customizing SearchView: " + e.getMessage());
        }

        // Set query listeners dengan threshold dan debounce sederhana
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            private Handler searchHandler = new Handler(Looper.getMainLooper());
            private Runnable searchRunnable;

            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("HomeFragment", "Search submitted: " + query);
                currentQuery = query;
                performSearch(query, selectedGenre);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Cancel any pending search
                if (searchRunnable != null) {
                    searchHandler.removeCallbacks(searchRunnable);
                }

                // Delay search for better UX (debounce)
                searchRunnable = () -> {
                    Log.d("HomeFragment", "Search text changed: " + newText);
                    currentQuery = newText;

                    // Hanya search jika minimal 2 karakter atau kosong (untuk reset)
                    if (newText.length() >= 2) {
                        performSearch(newText, selectedGenre);
                    } else if (newText.isEmpty()) {
                        Log.d("HomeFragment", "Empty query, loading filtered books");
                        if (selectedGenre != null) {
                            performSearch("", selectedGenre);
                        } else {
                            loadBooks();
                        }
                    }
                };

                // Delay 300ms untuk mengurangi beban saat user mengetik cepat
                searchHandler.postDelayed(searchRunnable, 300);
                return true;
            }
        });

        // Focus management (optional)
        searchView.setOnQueryTextFocusChangeListener((v, hasFocus) -> {
            Log.d("HomeFragment", "SearchView focus changed: " + hasFocus);
        });

        // Tambahan untuk memastikan SearchView terlihat dengan benar
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search books...");
    }

    // Metode untuk menyiapkan genre chips
    private void setupGenreChips(List<String> genres) {
        if (genreChipGroup == null || genres == null || getContext() == null) return;

        genreChipGroup.removeAllViews();

        // Tambahkan chip "All" untuk menampilkan semua buku
        Chip allChip = new Chip(getContext());
        allChip.setText("All");
        allChip.setCheckable(true);
        allChip.setChecked(selectedGenre == null);
        allChip.setChipBackgroundColorResource(R.color.chip_background_color_selector);
        allChip.setTextColor(getResources().getColorStateList(R.color.chip_text_color_selector, null));
        allChip.setOnClickListener(v -> {
            selectedGenre = null;
            allChip.setChecked(true);
            if (currentQuery.isEmpty()) {
                loadBooks();
            } else {
                performSearch(currentQuery, null);
            }
        });
        genreChipGroup.addView(allChip);

        // Tambahkan chip untuk setiap genre
        for (String genre : genres) {
            Chip chip = new Chip(getContext());
            chip.setText(genre);
            chip.setCheckable(true);
            chip.setChecked(genre.equals(selectedGenre));
            chip.setChipBackgroundColorResource(R.color.chip_background_color_selector);
            chip.setTextColor(getResources().getColorStateList(R.color.chip_text_color_selector, null));
            chip.setOnClickListener(v -> {
                selectedGenre = genre;
                chip.setChecked(true);
                performSearch(currentQuery, genre);
            });
            genreChipGroup.addView(chip);
        }
    }

    // Metode untuk mendapatkan daftar genre unik
    private List<String> extractGenres(List<Book> books) {
        Set<String> uniqueGenres = new HashSet<>();
        for (Book book : books) {
            if (book.getGenre() != null && !book.getGenre().isEmpty()) {
                uniqueGenres.add(book.getGenre());
            }
        }
        return new ArrayList<>(uniqueGenres);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (selectedGenre == null && currentQuery.isEmpty()) {
            loadBooks();
        } else {
            performSearch(currentQuery, selectedGenre);
        }
    }

    private void performSearch(String query, String genre) {
        Log.d("HomeFragment", "Performing search for: " + query + ", genre: " + genre);
        progressBar.setVisibility(View.VISIBLE);

        executorService.execute(() -> {
            try {
                // Lakukan pencarian
                List<Book> searchResults;

                if (genre != null && !genre.isEmpty()) {
                    // Filter by genre first
                    List<Book> genreResults = BookRepository.getInstance().getBooksByGenre(genre);

                    if (query != null && !query.isEmpty()) {
                        // Then filter by query if needed
                        String lowerQuery = query.toLowerCase();
                        searchResults = new ArrayList<>();
                        for (Book book : genreResults) {
                            if (book.getTitle().toLowerCase().contains(lowerQuery) ||
                                    book.getAuthor().toLowerCase().contains(lowerQuery)) {
                                searchResults.add(book);
                            }
                        }
                    } else {
                        searchResults = genreResults;
                    }
                } else {
                    // Filter by query only
                    if (query != null && !query.isEmpty()) {
                        searchResults = BookRepository.getInstance().searchBooks(query);
                    } else {
                        searchResults = BookRepository.getInstance().getAllBooks();
                    }
                }

                Log.d("HomeFragment", "Search found " + searchResults.size() + " results");

                // Delay pendek untuk feedback visual
                Thread.sleep(300);

                // Update UI di thread utama dengan safety check
                if (isAdded() && getActivity() != null) {
                    mainHandler.post(() -> {
                        try {
                            adapter.setBooks(searchResults);
                            progressBar.setVisibility(View.GONE);

                            // Tampilkan feedback visual jika tidak ada hasil
                            if (searchResults.isEmpty() && (query.length() > 0 || genre != null)) {
                                Log.d("HomeFragment", "No search results found");
                                // Optional: Show "No results" message
                                // noResultsView.setVisibility(View.VISIBLE);
                            }/* else {
                                // noResultsView.setVisibility(View.GONE);
                            }*/
                        } catch (Exception e) {
                            Log.e("HomeFragment", "Error updating UI with search results: " + e.getMessage());
                        }
                    });
                }
            } catch (Exception e) {
                Log.e("HomeFragment", "Error during search: " + e.getMessage());
                if (isAdded() && getActivity() != null) {
                    mainHandler.post(() -> progressBar.setVisibility(View.GONE));
                }
            }
        });
    }

    private void loadBooks() {
        progressBar.setVisibility(View.VISIBLE);
        executorService.execute(() -> {
            List<Book> books = BookRepository.getInstance().getAllBooks();

            // Extract genres for chips
            List<String> genres = extractGenres(books);

            mainHandler.post(() -> {
                adapter.setBooks(books);
                progressBar.setVisibility(View.GONE);

                // Setup genre chips
                setupGenreChips(genres);
            });
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Bersihkan callback yang mungkin masih pending
        mainHandler.removeCallbacksAndMessages(null);
    }
}