package items;

import java.time.LocalDate;

public abstract class LibraryItem {

    private String id;
    private String title;
    private String author;
    private boolean isAvailable;
    private LocalDate borrowDate;

    public LibraryItem(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public abstract String getItemType();
    public abstract int getMaxLoanDays();

    public String getId()        { return id; }
    public String getTitle()     { return title; }
    public String getAuthor()    { return author; }
    public boolean isAvailable() { return isAvailable; }
    public LocalDate getBorrowDate() { return borrowDate; }  // ← YENİ

    public void setAvailable(boolean available) { this.isAvailable = available; }
    public void setBorrowDate(LocalDate date)   { this.borrowDate = date; }  // ← YENİ

    public void displayInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + title);
        System.out.println("Author: " + author);
        System.out.println("Type: " + getItemType());
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
        System.out.println("----------------------------");
    }
}