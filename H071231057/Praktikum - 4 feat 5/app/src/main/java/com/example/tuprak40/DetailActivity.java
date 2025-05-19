package com.example.tuprak40;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tuprak40.models.Book;
import com.example.tuprak40.repository.BookRepository;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

//      saran : parcelable
        String bookId = getIntent().getStringExtra("BOOK_ID");
        Book book = BookRepository.getInstance().getAllBooks().stream()
                .filter(b -> b.getId().equals(bookId))
                .findFirst()
                .orElse(null);

        if (book != null) {
            setupViews(book);
        } else {
            Toast.makeText(this, "Buku tidak ditemukan", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void setupViews(Book book) {
        TextView titleText = findViewById(R.id.detail_title);
        TextView authorText = findViewById(R.id.detail_author);
        TextView yearText = findViewById(R.id.detail_year);
        TextView blurbText = findViewById(R.id.detail_blurb);
        TextView genreText = findViewById(R.id.detail_genre);
        TextView ratingText = findViewById(R.id.detail_rating);
        ImageView coverImage = findViewById(R.id.detail_cover);
        ToggleButton likeButton = findViewById(R.id.like_button);
        ImageButton backButton = findViewById(R.id.back_button);

        // Configure back button
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        // Enhanced image loading with requestOptions
        RequestOptions requestOptions = new RequestOptions()
                .centerCrop();

        if (book.getCoverImage() != null && !book.getCoverImage().isEmpty()) {
            if (book.getCoverImage().startsWith("content://") || book.getCoverImage().startsWith("file://")) {
                Glide.with(this)
                        .load(book.getCoverImage())
                        .apply(requestOptions)
                        .into(coverImage);
            } else {
                int imageResId = getResources()
                        .getIdentifier(book.getCoverImage(), "drawable", getPackageName());

                if (imageResId != 0) {
                    Glide.with(this)
                            .load(imageResId)
                            .apply(requestOptions)
                            .into(coverImage);
                } else {
                    coverImage.setImageResource(R.drawable.ic_image_placeholder);
                }
            }
        } else {
            coverImage.setImageResource(R.drawable.ic_image_placeholder);
        }

        // Set text content
        titleText.setText(book.getTitle());
        authorText.setText(book.getAuthor());
        yearText.setText(String.valueOf(book.getPublishYear()));
        blurbText.setText(book.getBlurb());
        genreText.setText(book.getGenre());
        ratingText.setText(String.format("%.1f/5.0", book.getRating()));

        // Set like button state and listener
        likeButton.setChecked(book.isLiked());
        likeButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            book.setLiked(isChecked);
            Toast.makeText(DetailActivity.this,
                    isChecked ? "Added to favorites" : "Removed from favorites",
                    Toast.LENGTH_SHORT).show();
        });
    }
}