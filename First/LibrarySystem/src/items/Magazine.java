package items;

public class Magazine extends LibraryItem implements Borrowable {

    public Magazine(String id, String title, String author) {
        super(id, title, author);
    }

    @Override
    public String getItemType() { return "Magazine"; }

    @Override
    public int getMaxLoanDays() { return 7; }

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