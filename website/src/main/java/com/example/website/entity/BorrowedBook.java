package com.example.website.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "borrowed_books")
public class BorrowedBook {

    @Id
    private int idBorrowedBooks;

    @Column(nullable = false)
    private int readerId;

    @Column(nullable = false)
    private int bookId;

    @Column(nullable = false)
    private String loanDate;

    private String returnDate;

    // Геттеры и сеттеры

    public int getIdBorrowedBooks() {
        return idBorrowedBooks;
    }

    public void setIdBorrowedBooks(int idBorrowedBooks) {
        this.idBorrowedBooks = idBorrowedBooks;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}