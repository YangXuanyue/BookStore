package homework7;

public class PercentageStrategy
    implements IPricingStrategy {
    private String id, name;
    private int bookType;
    private double discountPercentage;

    PercentageStrategy(
        String id,
        String name,
        int bookType,
        double discountPercentage
    ) {
        this.id = id;
        this.name = name;
        this.bookType = bookType;
        this.discountPercentage = discountPercentage;
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
        return IPricingStrategy.PERCENTAGE;
    }

    @Override
    public int getBookType() {
        return bookType;
    }

    @Override
    public double getDiscount() {
        return discountPercentage;
    }

    @Override
    public String getSimpleStrategyIds() {
        return null;
    }

    @Override
    public double getSubTotal(SaleLineItem item) {
        return (item.getBookPrice() * (1. - (discountPercentage / 100.0))) * item.getCopies();
    }
}
