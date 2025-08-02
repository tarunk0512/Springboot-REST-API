package com.stark.dev.stark.service;

import com.stark.dev.stark.entity.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecifications {
    public static Specification<Book> hasAuthor(String author) {
        return (root, query, cb) -> cb.equal(root.get("author"), author);
    }

    public static Specification<Book> hasTitleContaining(String keyword) {
        return (root, query, cb) -> cb.like(root.get("title"), "%" + keyword + "%");
    }
}