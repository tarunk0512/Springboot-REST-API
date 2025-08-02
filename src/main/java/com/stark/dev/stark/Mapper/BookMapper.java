package com.stark.dev.stark.Mapper;

import com.stark.dev.stark.dto.BookDTO;
import com.stark.dev.stark.entity.Book;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO toDto(Book book);
    Book toEntity(BookDTO dto);
    List<BookDTO> toDtoList(List<Book> books);
}