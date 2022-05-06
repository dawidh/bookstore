package pl.hilibrand.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.hilibrand.dto.Book;
import pl.hilibrand.entity.BookEntity;
import pl.hilibrand.exception.DuplicateException;
import pl.hilibrand.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final Logger logger = LoggerFactory.getLogger(BookService.class);
    private final static String DUPLICATE_ERROR_MESSAGE = "Book with %s ISBN already exist";
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    public List<Book> getBooks() {
        logger.debug("Get all books");
        return bookRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public BookEntity createBook(Book book) {
        bookRepository.findByIsbn(book.getIsbn())
                .ifPresent(bookEntity -> {
                    var error = String.format(DUPLICATE_ERROR_MESSAGE, book.getIsbn());
                    throw new DuplicateException(error);
                });

        var newBook = bookRepository.save(convertToEntity(book));
        logger.info("New book has been added: " + newBook);
        return newBook;
    }

    private BookEntity convertToEntity(Book book) {
        logger.debug("Converting book dto to entity: " + book);
        return modelMapper.map(book, BookEntity.class);
    }

    private Book convertToDto(BookEntity bookEntity) {
        logger.debug("Converting book entity to dto: " + bookEntity);
        return modelMapper.map(bookEntity, Book.class);
    }
}
