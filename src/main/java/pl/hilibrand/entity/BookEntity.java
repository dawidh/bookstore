package pl.hilibrand.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    private String author;

    private String title;

    @Column(unique = true)
    private String isbn;

    public BookEntity() {
    }

    public BookEntity(String author, String title, String isbn) {
        this.author = author;
        this.title = title;
        this.isbn = isbn;
    }

    public BookEntity(Long id, String author, String title, String isbn) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookEntity)) return false;

        BookEntity that = (BookEntity) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(author, that.author)) return false;
        if (!Objects.equals(title, that.title)) return false;
        return Objects.equals(isbn, that.isbn);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
