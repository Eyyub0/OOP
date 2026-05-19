package items;

public class Thesis extends LibraryItem implements Borrowable {

    public Thesis(String id, String title, String author) {
        super(id, title, author);
    }

    @Override
    public String getItemType() { return "Thesis"; }

    @Override
    public int getMaxLoanDays() { return 21; }

    @Override
    public void borrow(String memberId) {
        setAvailable(false);
        System.out.println(getTitle() + " götürüldü.");
    }

    @Override
    public void returnItem() {
        setAvailable(true);
        System.out.println(getTitle() + " qaytarıldı.");
    }

    @Override
    public double calculateFine(int overdueDays) {
        return overdueDays * 1.00;
    }
}