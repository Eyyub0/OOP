package items;

public interface Borrowable {

    void borrow(String memberId);

    void returnItem();

    double calculateFine(int overdueDays);
}