package items;

public class Book extends LibraryItem implements Borrowable {

    public Book(String id, String title, String author) {
        super(id, title, author);
    }

    @Override
    public String getItemType() { return "Book"; }

    @Override
    public int getMaxLoanDays() { return 14; }

    @Override
    public void borrow(String memberId) {
        setAvailable(false);
        System.out.println(getTitle() + " was taken.");
    }

    @Override
    public void returnItem() {
        setAvailable(true);
        System.out.println(getTitle() + " was returned.");
    }

    @Override
    public double calculateFine(int overdueDays) {
        return overdueDays * 1.00;
    }
}