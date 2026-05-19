package members;

import java.util.ArrayList;
import java.util.List;

public abstract class Member {

    private String id;
    private String name;
    private List<String> borrowedItems;

    // Constructor
    public Member(String id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedItems = new ArrayList<>();
    }

    // Hər alt sinif öz limitini yazacaq
    public abstract int getMaxItems();
    public abstract String getMemberType();

    // Getter-lər
    public String getId()   { return id; }
    public String getName() { return name; }
    public List<String> getBorrowedItems() { return borrowedItems; }

    // Item əlavə et
    public void addBorrowedItem(String itemId) {
        borrowedItems.add(itemId);
    }

    // Item sil
    public void removeBorrowedItem(String itemId) {
        borrowedItems.remove(itemId);
    }

    // Məlumatı çap et
    public void displayInfo() {
        System.out.println("ID: " + id);
        System.out.println("Ad: " + name);
        System.out.println("Növ: " + getMemberType());
        System.out.println("Limit: " + getMaxItems());
        System.out.println("Götürülən itemlər: " + borrowedItems.size());
        System.out.println("----------------------------");
    }
}