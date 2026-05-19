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
        System.out.println(item.getTitle() + " kitabxanaya əlavə edildi.");
    }

    public void registerMember(Member member) {
        members.put(member.getId(), member);
        System.out.println(member.getName() + " qeydiyyatdan keçdi.");
    }

    // Kitab götür
    public void borrowItem(String memberId, String itemId) {
        Member member = members.get(memberId);
        LibraryItem item = catalog.get(itemId);

        if (member == null) {
            System.out.println("Üzv tapılmadı!");
            return;
        }
        if (item == null) {
            System.out.println("Item tapılmadı!");
            return;
        }
        if (!item.isAvailable()) {
            throw new ItemNotAvailableException(item.getTitle() + " artıq götürülüb!");
        }
        if (member.getBorrowedItems().size() >= member.getMaxItems()) {
            throw new BorrowLimitExceededException(member.getName() + " limitini aşdı!");
        }

        item.setAvailable(false);
        item.setBorrowDate(LocalDate.now()); // ← TARİX SAXLA
        member.addBorrowedItem(itemId);
        System.out.println(member.getName() + " -> " + item.getTitle() + " götürdü.");
    }

    // Kitab qaytar
    public void returnItem(String memberId, String itemId) {
        Member member = members.get(memberId);
        LibraryItem item = catalog.get(itemId);

        if (member == null) {
            System.out.println("Üzv tapılmadı!");
            return;
        }
        if (item == null) {
            System.out.println("Item tapılmadı!");
            return;
        }

        // ← BUG DÜZƏLİŞİ: member həqiqətən bu item-i götürüb?
        if (!member.getBorrowedItems().contains(itemId)) {
            System.out.println("Xəta: " + member.getName() + " bu item-i götürməyib!");
            return;
        }

        // ← CƏRİMƏ HESABI
        if (item.getBorrowDate() != null) {
            long daysHeld = ChronoUnit.DAYS.between(item.getBorrowDate(), LocalDate.now());
            long overdueDays = daysHeld - item.getMaxLoanDays();

            if (overdueDays > 0) {
                double fine = overdueDays * 1.00; // sənin variant: 1.00 AZN/gün
                System.out.println("Gecikme: " + overdueDays + " gün");
                System.out.println("Cərimə: " + fine + " AZN");
            } else {
                System.out.println("Vaxtında qaytarıldı. Cərimə yoxdur.");
            }
        }

        item.setAvailable(true);
        item.setBorrowDate(null); // ← tarixi sıfırla
        member.removeBorrowedItem(itemId);
        System.out.println(member.getName() + " -> " + item.getTitle() + " qaytardı.");
    }

    public void listAllAvailable() {
        System.out.println("=== Mövcud itemlər ===");
        for (LibraryItem item : catalog.values()) {
            if (item.isAvailable()) {
                item.displayInfo();
            }
        }
    }

    public void getMemberReport(String memberId) {
        Member member = members.get(memberId);
        if (member == null) {
            System.out.println("Üzv tapılmadı!");
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