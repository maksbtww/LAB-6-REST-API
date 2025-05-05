package com.example.website.controller.rest;

import com.example.website.entity.BorrowedBook;
import com.example.website.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/borrowed")
public class BorrowedBookRestController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping
    public List<BorrowedBook> getAllBorrowedBooks() {
        return libraryService.getAllBorrowedBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowedBook> getBorrowedBookById(@PathVariable int id) {
        Optional<BorrowedBook> book = libraryService.getBorrowedBookById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BorrowedBook> createBorrowedBook(@RequestBody BorrowedBook book) {
        BorrowedBook savedBook = libraryService.saveBorrowedBook(book);
        return ResponseEntity.ok(savedBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BorrowedBook> updateBorrowedBook(@PathVariable int id, @RequestBody BorrowedBook bookDetails) {
        return libraryService.getBorrowedBookById(id)
                .map(book -> {
                    book.setReaderId(bookDetails.getReaderId());
                    book.setBookId(bookDetails.getBookId());
                    book.setLoanDate(bookDetails.getLoanDate());
                    book.setReturnDate(bookDetails.getReturnDate());
                    return ResponseEntity.ok(libraryService.saveBorrowedBook(book));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrowedBook(@PathVariable int id) {
        if (libraryService.getBorrowedBookById(id).isPresent()) {
            libraryService.deleteBorrowedBook(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}