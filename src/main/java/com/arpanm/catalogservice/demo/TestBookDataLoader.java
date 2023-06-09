package com.arpanm.catalogservice.demo;

import com.arpanm.catalogservice.domain.Book;
import com.arpanm.catalogservice.exception.BookAlreadyExistsException;
import com.arpanm.catalogservice.exception.BookNotFoundException;
import com.arpanm.catalogservice.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(prefix = "application", value = "loadTestData", havingValue = "true")
public class TestBookDataLoader {

    private final BookService bookService;

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookData() {
        try{
            log.info("Loading Initial Books to Catalog for testing purposes...");
            this.bookService.addNewBookInCatalog(
                    Book.of("1234567", "Title1", "Author1", 1000d, "Publisher1"));
            this.bookService.addNewBookInCatalog(
                    Book.of("1234568", "Title2", "Author2", 2000d, "Publisher2"));
            this.bookService.addNewBookInCatalog(
                    Book.of("1234569", "Title3", "Author3", 3000d, "Publisher3"));
            this.bookService.addNewBookInCatalog(
                    Book.of("2345671", "Title4", "Author4", 4000d, "Publisher4"));
            log.info("Loading Initial Books to Catalog Complete.");
        } catch (BookNotFoundException | BookAlreadyExistsException ex) {
            log.info(ex.getMessage());
            log.info("Books are already present in Catalog");
        }
    }
}
