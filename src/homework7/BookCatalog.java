package homework7;

import java.util.ArrayList;
import java.util.HashMap;

public class BookCatalog {
    private ArrayList<BookSpecification> books = new ArrayList<>();
    private HashMap<String, Integer> isbnToBookIdx = new HashMap<>();

    BookCatalog() {
    }

    public ArrayList<BookSpecification> getBooks() {
        return books;
    }

    public void addBook(BookSpecification book)
        throws Exception {
        if (isbnToBookIdx.containsKey(book.getIsbn())) {
            throw new Exception("该ISBN已存在");
        } else {
            isbnToBookIdx.put(book.getIsbn(), books.size());
            books.add(book);
        }
    }

    public BookSpecification getBookSpecification(String isbn)
        throws Exception {
        if (isbnToBookIdx.containsKey(isbn)) {
            return books.get(
                isbnToBookIdx.get(isbn)
            );
        } else {
            throw new Exception("该ISBN不存在");
        }
    }
}
