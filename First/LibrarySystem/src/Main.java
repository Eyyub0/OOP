import library.Library;
import items.Book;
import items.Magazine;
import items.Thesis;
import members.RegularMember;
import members.PlusMember;
import members.EliteMember;
import exceptions.ItemNotAvailableException;
import exceptions.BorrowLimitExceededException;
import items.SearchResult;
import items.LibraryItem;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== LIBRARY SYSTEM ===");
            System.out.println("1. Add new item");
            System.out.println("2. New member registration");
            System.out.println("3. Take a book");
            System.out.println("4. Return the book");
            System.out.println("5. Search");
            System.out.println("6. Member report");
            System.out.println("7. All available items");
            System.out.println("8. Exit");
            System.out.print("Choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Please enter a number!");
                continue;
            }

            if (choice == 1) {
                System.out.print("Type (book/magazine/thesis): ");
                String type = scanner.nextLine();
                System.out.print("ID: ");
                String id = scanner.nextLine();
                System.out.print("Name: ");
                String title = scanner.nextLine();
                System.out.print("The author: ");
                String author = scanner.nextLine();

                if (type.equals("book")) {
                    library.addItem(new Book(id, title, author));
                } else if (type.equals("magazine")) {
                    library.addItem(new Magazine(id, title, author));
                } else if (type.equals("thesis")) {
                    library.addItem(new Thesis(id, title, author));
                } else {
                    System.out.println("The wrong kind!");
                }

            } else if (choice == 2) {
                System.out.print("Type (regular/plus/elite): ");
                String type = scanner.nextLine();
                System.out.print("ID: ");
                String id = scanner.nextLine();
                System.out.print("Name: ");
                String name = scanner.nextLine();

                if (type.equals("regular")) {
                    library.registerMember(new RegularMember(id, name));
                } else if (type.equals("plus")) {
                    library.registerMember(new PlusMember(id, name));
                } else if (type.equals("elite")) {
                    library.registerMember(new EliteMember(id, name));
                } else {
                    System.out.println("The wrong kind!");
                }

            } else if (choice == 3) {
                System.out.print("Member ID: ");
                String memberId = scanner.nextLine();
                System.out.print("Item ID: ");
                String itemId = scanner.nextLine();
                try {
                    library.borrowItem(memberId, itemId);
                } catch (ItemNotAvailableException | BorrowLimitExceededException e) {
                    System.out.println("Error: " + e.getMessage());
                }

            } else if (choice == 4) {
                System.out.print("Member ID: ");
                String memberId = scanner.nextLine();
                System.out.print("Item ID: ");
                String itemId = scanner.nextLine();
                library.returnItem(memberId, itemId);

            } else if (choice == 5) {
                System.out.print("Search type (title/author): ");
                String type = scanner.nextLine();
                System.out.print("Search term: ");
                String keyword = scanner.nextLine();

                if (type.equals("title")) {
                    SearchResult<LibraryItem> result = new SearchResult<>(library.searchByTitle(keyword));
                    System.out.println("Found: " + result.getCount());
                    result.display();
                } else if (type.equals("author")) {
                    SearchResult<LibraryItem> result = new SearchResult<>(library.searchByAuthor(keyword));
                    System.out.println("Found: " + result.getCount());
                    result.display();
                } else {
                    System.out.println("The wrong kind!");
                }

            } else if (choice == 6) {
                System.out.print("Member ID: ");
                String memberId = scanner.nextLine();
                library.getMemberReport(memberId);

            } else if (choice == 7) {
                library.listAllAvailable();

            } else if (choice == 8) {
                System.out.println("Exit...");
                break;

            } else {
                System.out.println("Wrong choice!");
            }
        }
    }
}