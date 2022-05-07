package pl.hilibrand.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import pl.hilibrand.dto.Book;
import pl.hilibrand.entity.BookEntity;
import pl.hilibrand.exception.DuplicateException;
import pl.hilibrand.repository.BookRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class BookServiceTest {

    private BookRepository bookRepository;

    private ModelMapper modelMapper;

    private BookService bookService;

    private static final BookEntity bookEntity = new BookEntity(1L, "Author 1", "Title 1", "1");

    @BeforeEach
    private void setup() {
        bookRepository = mock(BookRepository.class);
        modelMapper = mock(ModelMapper.class);
        bookService = new BookService(bookRepository, modelMapper);
    }

    @Test
    public void testGetBooksWithEmptyList() {
        when(bookRepository.findAll()).thenReturn(Collections.emptyList());

        var list = bookService.getBooks();

        verify(bookRepository, times(1)).findAll();
        verify(modelMapper, times(0)).map(any(), any());
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    public void testGetBooksWithNotEmptyList() {
        when(bookRepository.findAll()).thenReturn(List.of(
                bookEntity, new BookEntity()
        ));

        var list = bookService.getBooks();

        verify(bookRepository, times(1)).findAll();
        verify(modelMapper, times(2)).map(any(), any());
        Assertions.assertAll("Verify if list is not empty and size is 2",
                () -> Assertions.assertFalse(list.isEmpty()),
                () -> Assertions.assertEquals(2, list.size())
        );
    }

    @Test
    public void testCreateBook() {
        when(bookRepository.findByIsbn(any())).thenReturn(Optional.empty());
        when(bookRepository.save(any())).thenReturn(bookEntity);

        var book = bookService.createBook(new Book());

        verify(bookRepository, times(1)).findByIsbn(any());
        verify(bookRepository, times(1)).save(any());
        verify(modelMapper, times(1)).map(any(), any());
        Assertions.assertEquals(book, bookEntity);
    }

    @Test
    public void testCreateBookWhenIsbnAlreadyExist() {
        when(bookRepository.findByIsbn(any())).thenReturn(Optional.of(bookEntity));

        var exception = Assertions.assertThrows(DuplicateException.class, () ->
            bookService.createBook(new Book(1L, "Author 1", "Title 1", "1"))
        );

        var expectedMessage = String.format("Book with %s ISBN already exist", bookEntity.getIsbn());
        var actualMessage = exception.getMessage();

        verify(bookRepository, times(1)).findByIsbn(any());
        verify(bookRepository, times(0)).save(any());
        verify(modelMapper, times(0)).map(any(), any());
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

}