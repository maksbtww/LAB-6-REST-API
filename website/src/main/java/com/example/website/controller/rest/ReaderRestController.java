package com.example.website.controller.rest;

import com.example.website.entity.Reader;
import com.example.website.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/readers")
public class ReaderRestController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping
    public List<Reader> getAllReaders() {
        return libraryService.getAllReaders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reader> getReaderById(@PathVariable Long id) {
        return libraryService.getReaderById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Reader> createReader(@RequestBody Reader reader) {
        Reader savedReader = libraryService.saveReader(reader);
        return ResponseEntity.ok(savedReader);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reader> updateReader(@PathVariable Long id, @RequestBody Reader readerDetails) {
        return libraryService.getReaderById(id)
                .map(reader -> {
                    reader.setFirstName(readerDetails.getFirstName());
                    reader.setLastName(readerDetails.getLastName());
                    return ResponseEntity.ok(libraryService.saveReader(reader));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReader(@PathVariable Long id) {
        if (libraryService.getReaderById(id).isPresent()) {
            libraryService.deleteReader(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}