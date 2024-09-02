package Services;
import Models.Book;
import DB.DATABASECONNCETION;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookServices {
	public void addBook(Book book) {
        String sql = "INSERT INTO Books (title, author, publisher, year, available_copies) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DB.DATABASECONNCETION.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getPublisher());
            stmt.setInt(4, book.getYear());
            stmt.setInt(5, book.getAvailableCopies());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all books from the database
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM Books";
        try (Connection conn = DB.DATABASECONNCETION.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Book book = new Book(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        rs.getInt("year"),
                        rs.getInt("available_copies")
                );
                book.setId(rs.getInt("id"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    // Method to update an existing book in the database
    public void updateBook(Book book) {
        String sql = "UPDATE Books SET title = ?, author = ?, publisher = ?, year = ?, available_copies = ? WHERE id = ?";
        try (Connection conn = DB.DATABASECONNCETION.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getPublisher());
            stmt.setInt(4, book.getYear());
            stmt.setInt(5, book.getAvailableCopies());
            stmt.setInt(6, book.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a book from the database by ID
    public void deleteBook(int bookId) {
        String sql = "DELETE FROM Books WHERE id = ?";
        try (Connection conn = DB.DATABASECONNCETION.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to search for books by title
    public List<Book> searchBooksByTitle(String title) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM Books WHERE title LIKE ?";
        try (Connection conn = DB.DATABASECONNCETION.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + title + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Book book = new Book(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        rs.getInt("year"),
                        rs.getInt("available_copies")
                );
                book.setId(rs.getInt("id"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

}
