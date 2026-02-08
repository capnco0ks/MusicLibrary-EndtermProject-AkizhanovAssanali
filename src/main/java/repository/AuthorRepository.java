package repository;

import org.springframework.stereotype.Repository;
import exception.AuthorNotFoundException;
import exception.DataBaseException;
import model.Author;
import repository.interfaces.CrudRepository;
import config.DatabaseConfigManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorRepository implements CrudRepository<Author> {

    @Override
    public void create(Author author) {
        String sql = "INSERT INTO authors(id, name, rating) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfigManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, author.getId());
            ps.setString(2, author.getName());
            ps.setInt(3, author.getRating());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseException("Failed to insert author", e);
        }
    }

    @Override
    public List<Author> getAll() {
        List<Author> authors = new ArrayList<>();
        String sql = "SELECT id, name, rating FROM authors";
        try (Connection conn = DatabaseConfigManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                authors.add(new Author(rs.getInt("id"), rs.getString("name"), rs.getInt("rating")));
            }
        } catch (SQLException e) {
            throw new DataBaseException("Failed to fetch authors", e);
        }
        return authors;
    }

    @Override
    public Author getById(int id) {
        String sql = "SELECT id, name, rating FROM authors WHERE id = ?";
        try (Connection conn = DatabaseConfigManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Author(rs.getInt("id"), rs.getString("name"), rs.getInt("rating"));
            }
            throw new AuthorNotFoundException(id);
        } catch (SQLException e) {
            throw new DataBaseException("Failed to get author by ID", e);
        }
    }

    @Override
    public void update(int id, Author author) {
        String sql = "UPDATE authors SET name = ?, rating = ? WHERE id = ?";
        try (Connection conn = DatabaseConfigManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, author.getName());
            ps.setInt(2, author.getRating());
            ps.setInt(3, id);

            int updated = ps.executeUpdate();
            if (updated == 0) throw new AuthorNotFoundException(id);
        } catch (SQLException e) {
            throw new DataBaseException("Failed to update author", e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM authors WHERE id = ?";
        try (Connection conn = DatabaseConfigManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int deleted = ps.executeUpdate();
            if (deleted == 0) throw new AuthorNotFoundException(id);
        } catch (SQLException e) {
            throw new DataBaseException("Failed to delete author", e);
        }
    }
}
