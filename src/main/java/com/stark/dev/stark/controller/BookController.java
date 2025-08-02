package com.stark.dev.stark.controller;

import com.stark.dev.stark.dto.BookDTO;
import com.stark.dev.stark.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService service;

    @GetMapping("/{id}")
    public BookDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public Page<BookDTO> getAll(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String title,
            Pageable pageable
    ) {
        return service.getAll(author, title, pageable);
    }

    @PostMapping
    public BookDTO create(@RequestBody BookDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public BookDTO update(@PathVariable Long id, @RequestBody BookDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
