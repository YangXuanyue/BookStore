package homework7;

public class BookSpecification {
    private String title;
    private String isbn;
    private int type;
    private double price;

    private static String[] typeStrs = {
        "非教材类计算机",
        "教材",
        "连环画",
        "养生",
        "其他"
    };

    BookSpecification(
        String title,
        String isbn,
        int type,
        double price
    ) {
        this.title = title;
        this.isbn = isbn;
        this.type = type;
        this.price = price;
    }

    public static String[] getTypeStrs() {
        return typeStrs;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }
}
