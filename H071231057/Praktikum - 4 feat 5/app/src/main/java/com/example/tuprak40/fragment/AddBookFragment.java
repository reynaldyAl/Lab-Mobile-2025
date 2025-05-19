package com.example.tuprak40.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import com.example.tuprak40.models.Book;
import com.example.tuprak40.repository.BookRepository;
import com.example.tuprak40.R;
import com.google.android.material.textfield.TextInputEditText;

import static android.app.Activity.RESULT_OK;

public class AddBookFragment extends Fragment {
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView coverImageView;
    private Uri selectedImageUri;
    private TextInputEditText titleInput, authorInput, yearInput, blurbInput, genreInput;

    public Uri getSelectedImageUri() {
        return selectedImageUri;
    }

    public void setSelectedImageUri(Uri selectedImageUri) {
        this.selectedImageUri = selectedImageUri;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_book, container, false);

        // Find all views
        titleInput = view.findViewById(R.id.title_input);
        authorInput = view.findViewById(R.id.author_input);
        yearInput = view.findViewById(R.id.year_input);
        blurbInput = view.findViewById(R.id.blurb_input);
        genreInput = view.findViewById(R.id.genre_input);
        Button selectImageButton = view.findViewById(R.id.select_image_button);
        Button submitButton = view.findViewById(R.id.submit_button);
        coverImageView = view.findViewById(R.id.cover_image_preview);

        // Set up image selection
        selectImageButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Book Cover"), PICK_IMAGE_REQUEST);
        });

        // Set up submit button
        submitButton.setOnClickListener(v -> {
            try {
                // Validate empty fields
                String title = titleInput.getText().toString().trim();
                String author = authorInput.getText().toString().trim();
                String yearStr = yearInput.getText().toString().trim();
                String blurb = blurbInput.getText().toString().trim();
                String genre = genreInput.getText().toString().trim();

                // Check for empty fields
                if (title.isEmpty() || author.isEmpty() || yearStr.isEmpty() ||
                        blurb.isEmpty() || genre.isEmpty()) {
                    throw new IllegalArgumentException("Semua field harus diisi!");
                }

                // Parse and validate year
                int year;
                try {
                    year = Integer.parseInt(yearStr);
                    // Assume valid years are between 1800 and current year + 1
                    int currentYear = java.time.Year.now().getValue();
                    if (year < 1800 || year > currentYear + 1) {
                        throw new IllegalArgumentException("Tahun tidak valid! (1800-" + currentYear + ")");
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Format tahun tidak valid!");
                }

                // Create new book
                Book newBook = new Book(
                        String.valueOf(System.currentTimeMillis()),
                        title,
                        author,
                        year,
                        blurb,
                        selectedImageUri != null ? selectedImageUri.toString() : "",
                        genre,
                        0.0f
                );

                BookRepository.getInstance().addBook(newBook);
                Toast.makeText(getContext(), "Buku berhasil ditambahkan!", Toast.LENGTH_SHORT).show();
                // Navigate back to home
                getActivity().getSupportFragmentManager().popBackStack();

            } catch (IllegalArgumentException e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(getContext(), "Terjadi kesalahan: " + e.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                    && data != null && data.getData() != null) {
                selectedImageUri = data.getData();
                coverImageView.setImageURI(selectedImageUri);
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "Gagal memuat gambar: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
}