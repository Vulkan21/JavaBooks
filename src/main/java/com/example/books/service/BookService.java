package com.example.books.service;

import com.example.books.entity.Book;
import com.example.books.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public List<Book> getAll() {
        return repo.findAll();
    }

    public Book getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found"));
    }

    public Book create(Book b) {
        b.setId(null);
        return repo.save(b);
    }

    public Book update(Long id, Book data) {
        Book existing = getById(id);
        existing.setTitle(data.getTitle());
        existing.setAuthor(data.getAuthor());
        existing.setYear(data.getYear());
        existing.setDescription(data.getDescription());
        return repo.save(existing);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new EntityNotFoundException("Book not found");
        repo.deleteById(id);
    }
}
