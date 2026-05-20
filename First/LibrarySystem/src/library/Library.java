package library;

import items.LibraryItem;
import members.Member;
import exceptions.ItemNotAvailableException;
import exceptions.BorrowLimitExceededException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Library {

    private Map<String, LibraryItem> catalog;
    private Map<String, Member> members;

    public Library() {
        catalog = new HashMap<>();
        members = new HashMap<>();
    }

    public void addItem(LibraryItem item) {
        catalog.put(item.getId(), item);
        System.out.println(item.getTitle() + " added to library.");
    }

    public void registerMember(Member member) {
        members.put(member.getId(), member);
        System.out.println(member.getName() + " registered.");
    }

    // Kitab götür
    public void borrowItem(String memberId, String itemId) {
        Member member = members.get(memberId);
        LibraryItem item = catalog.get(itemId);

        if (member == null) {
            System.out.println("No member found!");
            return;
        }
        if (item == null) {
            System.out.println("Item not found!");
            return;
        }
        if (!item.isAvailable()) {
            throw new ItemNotAvailableException(item.getTitle() + " already taken!");
        }
        if (member.getBorrowedItems().size() >= member.getMaxItems()) {
            throw new BorrowLimitExceededException(member.getName() + " exceeded the limit!");
        }

        item.setAvailable(false);
        item.setBorrowDate(LocalDate.now());
        member.addBorrowedItem(itemId);
        System.out.println(member.getName() + " -> " + item.getTitle() + " took it.");
    }

    // Kitab qaytar
    public void returnItem(String memberId, String itemId) {
        Member member = members.get(memberId);
        LibraryItem item = catalog.get(itemId);

        if (member == null) {
            System.out.println("No member found!");
            return;
        }
        if (item == null) {
            System.out.println("Item not found!");
            return;
        }


        if (!member.getBorrowedItems().contains(itemId)) {
            System.out.println("Error: " + member.getName() + " did not take this item!");
            return;
        }

        // CƏRİMƏ
        if (item.getBorrowDate() != null) {
            long daysHeld = ChronoUnit.DAYS.between(item.getBorrowDate(), LocalDate.now());
            long overdueDays = daysHeld - item.getMaxLoanDays();

            if (overdueDays > 0) {
                double fine = overdueDays * 1.00;
                System.out.println("Delay: " + overdueDays + " day");
                System.out.println("fine: " + fine + " AZN");
            } else {
                System.out.println("Returned on time. No fine.");
            }
        }

        item.setAvailable(true);
        item.setBorrowDate(null); // restart date
        member.removeBorrowedItem(itemId);
        System.out.println(member.getName() + " -> " + item.getTitle() + " returned.");
    }

    public void listAllAvailable() {
        System.out.println("=== Available items ===");
        for (LibraryItem item : catalog.values()) {
            if (item.isAvailable()) {
                item.displayInfo();
            }
        }
    }

    public void getMemberReport(String memberId) {
        Member member = members.get(memberId);
        if (member == null) {
            System.out.println("No member found!");
            return;
        }
        member.displayInfo();
    }

    public List<LibraryItem> searchByTitle(String keyword) {
        List<LibraryItem> results = new ArrayList<>();
        for (LibraryItem item : catalog.values()) {
            if (item.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(item);
            }
        }
        return results;
    }

    public List<LibraryItem> searchByAuthor(String keyword) {
        List<LibraryItem> results = new ArrayList<>();
        for (LibraryItem item : catalog.values()) {
            if (item.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(item);
            }
        }
        return results;
    }
}