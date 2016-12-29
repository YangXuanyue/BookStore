package homework7;

public class NoDiscountStrategy
    implements IPricingStrategy {

    NoDiscountStrategy() {
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getType() {
        return -1;
    }

    @Override
    public int getBookType() {
        return -1;
    }

    @Override
    public double getDiscount() {
        return 0.;
    }

    @Override
    public String getSimpleStrategyIds() {
        return null;
    }

    @Override
    public double getSubTotal(SaleLineItem item) {
        return item.getBookPrice() * item.getCopies();
    }
}
