package items;

import java.util.List;

public class SearchResult<T extends LibraryItem> {

    private List<T> results;

    public SearchResult(List<T> results) {
        this.results = results;
    }

    public void display() {
        if (results.isEmpty()) {
            System.out.println("Heç nə tapılmadı.");
        } else {
            results.forEach(LibraryItem::displayInfo);
        }
    }

    public int getCount() {
        return results.size();
    }
}
