package com.stark.dev.stark.service;

import com.stark.dev.stark.Mapper.BookMapper;
import com.stark.dev.stark.dto.BookDTO;
import com.stark.dev.stark.entity.Book;
import com.stark.dev.stark.exception.ResourceNotFoundException;
import com.stark.dev.stark.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper mapper;

    public BookDTO getById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found: " + id));
        return mapper.toDto(book);
    }

    public Page<Book> getAll(String author, String title, Pageable pageable) {
        Specification<Book> spec = (root, query, cb) -> cb.conjunction(); // always true

        if (author != null) spec = spec.and(BookSpecifications.hasAuthor(author));
        if (title != null) spec = spec.and(BookSpecifications.hasTitleContaining(title));
        log.info(spec.toString());
        Page<Book> page = bookRepository.findAll(spec, pageable);
        log.info(bookRepository.findAll(spec).toString());
        return page;
        //return page.map(mapper::toDto);
    }

    public BookDTO create(BookDTO dto) {
        Book book = mapper.toEntity(dto);
        return mapper.toDto(bookRepository.save(book));
    }

    public BookDTO update(Long id, BookDTO dto) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found: " + id));

        existing.setTitle(dto.getTitle());
        existing.setAuthor(dto.getAuthor());
        existing.setPrice(dto.getPrice());

        return mapper.toDto(bookRepository.save(existing));
    }

    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found: " + id);
        }
        bookRepository.deleteById(id);
    }
}