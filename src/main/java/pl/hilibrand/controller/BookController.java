package pl.hilibrand.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.hilibrand.dto.Book;
import pl.hilibrand.entity.BookEntity;
import pl.hilibrand.service.BookService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/book",
        consumes = "application/json",
        produces = "application/json")
public class BookController {
    private final BookService bookService;

    private BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    private @ResponseBody ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.getBooks(), HttpStatus.OK);
    }

    @PostMapping
    private @ResponseBody ResponseEntity<BookEntity> addBook(@Valid @RequestBody Book book) {
        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
    }
}
