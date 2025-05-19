package com.example.tuprak40.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tuprak40.models.Book;
import com.example.tuprak40.repository.BookRepository;
import com.example.tuprak40.DetailActivity;
import com.example.tuprak40.R;
import com.example.tuprak40.adapter.BookAdapter;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoritesFragment extends Fragment {

    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private ProgressBar progressBar;
    private View emptyView;
    private ExecutorService executorService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        progressBar = view.findViewById(R.id.progress_bar);
        emptyView = view.findViewById(R.id.empty_view);

        if (emptyView != null) {
            emptyView.setVisibility(View.GONE);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BookAdapter(book -> {
            if (getContext() != null) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("BOOK_ID", book.getId());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

        executorService = Executors.newSingleThreadExecutor();

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        loadFavoriteBooks();
    }

    private void loadFavoriteBooks() {
        progressBar.setVisibility(View.VISIBLE);
        if (emptyView != null) {
            emptyView.setVisibility(View.GONE);
        }

        executorService.execute(() -> {
            List<Book> favoriteBooks = BookRepository.getInstance().getLikedBooks();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (getActivity() != null) {
                getActivity().runOnUiThread(() -> {
                    adapter.setBooks(favoriteBooks);
                    progressBar.setVisibility(View.GONE);

                    // Show empty view if no favorites
                    if (favoriteBooks.isEmpty() && emptyView != null) {
                        emptyView.setVisibility(View.VISIBLE);
                    } else if (emptyView != null) {
                        emptyView.setVisibility(View.GONE);
                    }
                });
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}