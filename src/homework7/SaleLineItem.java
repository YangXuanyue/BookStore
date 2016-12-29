package homework7;

public class SaleLineItem {
    private int copies;
    private BookSpecification bookSpec;
    private IPricingStrategy strategy;

    SaleLineItem(int copies, BookSpecification bookSpec, IPricingStrategy strategy) {
        this.copies = copies;
        this.bookSpec = bookSpec;
        this.strategy = strategy;
    }

    public int getCopies() {
        return copies;
    }

    public double getBookPrice() {
        return bookSpec.getPrice();
    }

    public String getBookTitle() {
        return bookSpec.getTitle();
    }

    double getSubTotal() {
        return strategy.getSubTotal(this);
    }
}
