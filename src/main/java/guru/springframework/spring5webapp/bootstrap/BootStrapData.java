package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher = new Publisher("Name", "some street");
        Author author = new Author("eric", "events");
        Book book = new Book("Domain Driven Design", "123456");
        publisherRepository.save(publisher);

        author.getBooks().add(book);
        book.getAuthors().add(author);
        book.setPublisher(publisher);
        publisher.getBooks().add(book);

        authorRepository.save(author);
        bookRepository.save(book);

        Author rod = new Author("Rod", "Jonson");
        Book enEjbBook = new Book("J2EE book", "789456");

        rod.getBooks().add(enEjbBook);
        enEjbBook.getAuthors().add(rod);
        enEjbBook.setPublisher(publisher);
        publisher.getBooks().add(enEjbBook);
        authorRepository.save(rod);
        bookRepository.save(enEjbBook);

        publisherRepository.save(publisher);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of book loaded into DB is " + bookRepository.count());

        System.out.println("Number of publishers loaded into DB is " + publisherRepository.count());
        System.out.println("Publisher has books  " + publisher.getBooks().size());



    }
}
