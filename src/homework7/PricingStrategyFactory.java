package homework7;

public class PricingStrategyFactory {
    private StrategyCatalog catalog;
    private static PricingStrategyFactory instance = new PricingStrategyFactory();

    public static PricingStrategyFactory getInstance() {
        return instance;
    }

    private PricingStrategyFactory() {
    }

    public void setCatalog(StrategyCatalog catalog) {
        this.catalog = catalog;
    }

    public IPricingStrategy getPricingStrategy(int bookType) {
        return catalog.getStrategy(bookType);
    }
}
