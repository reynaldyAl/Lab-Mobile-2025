package com.example.tuprak40.repository;

import android.util.Log;

import com.example.tuprak40.models.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookRepository {
    private static BookRepository instance;
    private List<Book> books;

    private BookRepository() {
        books = new ArrayList<>();
        initializeDummyData();
    }

    public static BookRepository getInstance() {
        if (instance == null) {
            instance = new BookRepository();
        }
        return instance;
    }

    private void initializeDummyData() {
        books.add(new Book("1", "The Great Gatsby", "F. Scott Fitzgerald", 1925,
                "A story of decadence and excess during the Roaring Twenties, following the mysterious millionaire Jay Gatsby and his obsession with Daisy Buchanan.", "great_gatsby", "Classic", 4.5f));

        books.add(new Book("2", "To Kill a Mockingbird", "Harper Lee", 1960,
                "A story of racial injustice and moral growth in the American South during the 1930s, told through the eyes of young Scout Finch.", "mockingbird", "Classic", 4.8f));

        books.add(new Book("3", "Dilan 1990", "Pidi Baiq", 2014,
                "Kisah romantis dan kenakalan remaja yang menceritakan hubungan Milea dengan Dilan, seorang siswa SMA yang berbeda dari yang lain.", "dilan_1990", "Romance", 4.7f));

        books.add(new Book("4", "Laskar Pelangi", "Andrea Hirata", 2005,
                "Kisah perjuangan 10 anak dari keluarga miskin di Belitung untuk mendapatkan pendidikan di sekolah Muhammadiyah.", "laskar_pelangi", "Fiction", 4.6f));

        books.add(new Book("5", "Harry Potter and the Philosopher's Stone", "J.K. Rowling", 1997,
                "The first book in the Harry Potter series, following young wizard Harry Potter during his first year at Hogwarts School of Witchcraft and Wizardry.", "harry_potter", "Fantasy", 4.9f));

        books.add(new Book("6", "Bumi Manusia", "Pramoedya Ananta Toer", 1980,
                "Novel sejarah yang menceritakan perjuangan Minke sebagai pribumi terpelajar dalam menghadapi diskriminasi kolonial Belanda.", "bumi_manusia", "Historical Fiction", 4.8f));

        books.add(new Book("7", "Sapiens: A Brief History of Humankind", "Yuval Noah Harari", 2011,
                "A broad perspective on the history of humanity, exploring the ways in which biology and history have shaped human societies.", "sapiens", "Non-Fiction", 4.7f));

        books.add(new Book("8", "Bumi", "Tere Liye", 2014,
                "Petualangan Raib, seorang gadis remaja yang memiliki kemampuan menghilang, bersama Seli dan Ali di dunia paralel bernama Bumi.", "bumi", "Fantasy", 4.5f));

        books.add(new Book("9", "Pride and Prejudice", "Jane Austen", 1813,
                "A romantic novel of manners that follows the character development of Elizabeth Bennet as she navigates issues of upbringing, morality, and marriage.", "pride_prejudice", "Classic", 4.6f));

        books.add(new Book("10", "Milea: Suara dari Dilan", "Pidi Baiq", 2016,
                "Kelanjutan kisah cinta Dilan dan Milea, diceritakan dari sudut pandang Milea sendiri, penuh dengan romansa dan kenangan masa SMA.", "milea", "Romance", 4.5f));

        books.add(new Book("11", "The Hunger Games", "Suzanne Collins", 2008,
                "In a dystopian future, young Katniss Everdeen volunteers to take her sister's place in the Hunger Games, a televised fight to the death.", "hunger_games", "Sci-Fi", 4.4f));

        books.add(new Book("12", "The Alchemist", "Paulo Coelho", 1988,
                "A philosophical novel about an Andalusian shepherd boy's journey to Egypt after having a recurring dream of finding treasure there.", "alchemist", "Fiction", 4.7f));

        books.add(new Book("13", "Atomic Habits", "James Clear", 2018,
                "A practical guide to breaking bad habits and building good ones, focusing on small changes that lead to remarkable results.", "atomic_habits", "Self-Help", 4.8f));

        books.add(new Book("14", "Perahu Kertas", "Dee Lestari", 2009,
                "Kisah cinta antara Kugy dan Keenan, dua pribadi unik dengan impian dan passion yang berbeda, yang saling melengkapi satu sama lain.", "perahu_kertas", "Romance", 4.5f));

        books.add(new Book("15", "Rich Dad Poor Dad", "Robert T. Kiyosaki", 1997,
                "A book advocating financial independence through investing, real estate, starting businesses, and increasing financial intelligence.", "rich_dad", "Finance", 4.6f));
    }

    public List<Book> getAllBooks() {
        Log.d("BookRepository", "Total books: " + books.size());
        return new ArrayList<>(books);
    }

    public List<Book> getLikedBooks() {
        return books.stream()
                .filter(Book::isLiked)
                .collect(Collectors.toList());
    }

    public List<Book> searchBooks(String query) {
        if (query == null || query.isEmpty()) {
            return getAllBooks();
        }

        String lowerQuery = query.toLowerCase();
        return books.stream()
                .filter(book ->
                        book.getTitle().toLowerCase().contains(lowerQuery) ||
                                book.getAuthor().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByGenre(String genre) {
        if (genre == null || genre.isEmpty()) {
            return getAllBooks();
        }

        return books.stream()
                .filter(book -> book.getGenre() != null && book.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

    // Optional helper methods (not required since we're using existing methods):
    /*
    public List<Book> searchBooksByGenre(String genre) {
        return getBooksByGenre(genre);
    }

    public List<Book> searchBooksByTextAndGenre(String query, String genre) {
        if ((query == null || query.isEmpty()) && (genre == null || genre.isEmpty())) {
            return getAllBooks();
        }

        List<Book> genreResults = getBooksByGenre(genre);

        if (query == null || query.isEmpty()) {
            return genreResults;
        }

        String lowerQuery = query.toLowerCase();
        return genreResults.stream()
                .filter(book ->
                    book.getTitle().toLowerCase().contains(lowerQuery) ||
                    book.getAuthor().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toList());
    }
    */

    public void addBook(Book book) {
        books.add(0, book); // Add new books at the beginning
        Log.d("BookRepository", "Added book: " + book.getTitle());
    }

    public Book getBookById(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }

        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void toggleBookLiked(String id) {
        Book book = getBookById(id);
        if (book != null) {
            book.setLiked(!book.isLiked());
        }
    }
}