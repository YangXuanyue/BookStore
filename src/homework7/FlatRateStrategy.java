package homework7;

public class FlatRateStrategy
    implements IPricingStrategy {
    private String id, name;
    private int bookType;
    private double discountPerBook;

    FlatRateStrategy(
        String id,
        String name,
        int bookType,
        double discountPerBook
    ) {
        this.id = id;
        this.name = name;
        this.bookType = bookType;
        this.discountPerBook = discountPerBook;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getType() {
        return IPricingStrategy.FLAT_RATE;
    }

    @Override
    public int getBookType() {
        return bookType;
    }

    @Override
    public double getDiscount() {
        return discountPerBook;
    }

    @Override
    public String getSimpleStrategyIds() {
        return null;
    }

    @Override
    public double getSubTotal(SaleLineItem item) {
        return (item.getBookPrice() - discountPerBook) * item.getCopies();
    }
}
