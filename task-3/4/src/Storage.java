import java.util.List;
import java.util.ArrayList;

public class Storage {
    private List<Book> storageList = new ArrayList<Book>();

    public void addBook() {
        storageList.add(new Book());
    }
}
