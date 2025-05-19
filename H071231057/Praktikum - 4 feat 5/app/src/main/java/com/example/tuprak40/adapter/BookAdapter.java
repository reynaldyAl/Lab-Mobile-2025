package com.example.tuprak40.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tuprak40.models.Book;
import com.example.tuprak40.R;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private List<Book> books;
    private OnBookClickListener listener;

    public interface OnBookClickListener {
        void onBookClick(Book book);
    }

    public BookAdapter(OnBookClickListener listener) {
        this.listener = listener;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.bind(book);
    }

    @Override
    public int getItemCount() {
        return books != null ? books.size() : 0;
    }

    class BookViewHolder extends RecyclerView.ViewHolder {
        private ImageView coverImage;
        private TextView titleText;
        private TextView authorText;

        public BookViewHolder(View itemView) {
            super(itemView);
            coverImage = itemView.findViewById(R.id.cover_image);
            titleText = itemView.findViewById(R.id.title_text);
            authorText = itemView.findViewById(R.id.author_text);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onBookClick(books.get(position));
                }
            });
        }

        public void bind(Book book) {
            titleText.setText(book.getTitle());
            authorText.setText(book.getAuthor());

            // Enhance image loading with rounded corners
            RequestOptions requestOptions = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.ic_image_placeholder);

            if (book.getCoverImage() != null && !book.getCoverImage().isEmpty()) {
                if (book.getCoverImage().startsWith("content://") || book.getCoverImage().startsWith("file://")) {
                    Glide.with(coverImage.getContext())
                            .load(book.getCoverImage())
                            .apply(requestOptions)
                            .into(coverImage);
                } else {
                    int imageResId = coverImage.getContext().getResources()
                            .getIdentifier(book.getCoverImage(), "drawable", coverImage.getContext().getPackageName());

                    if (imageResId != 0) {
                        Glide.with(coverImage.getContext())
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
        }
    }
}