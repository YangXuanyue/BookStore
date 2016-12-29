package homework7;

import java.util.ArrayList;

public class CompositeStrategy
    implements IPricingStrategy {
    private String id, name;
    private int bookType;
    private ArrayList<IPricingStrategy> strategies = new ArrayList<>();

    CompositeStrategy(
        String id,
        String name,
        int bookType
    ) {
        this.id = id;
        this.name = name;
        this.bookType = bookType;
    }

    public void add(IPricingStrategy strategy) {
        strategies.add(strategy);
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
        return IPricingStrategy.COMPOSITE;
    }

    @Override
    public int getBookType() {
        return bookType;
    }

    @Override
    public double getDiscount() {
        return 0;
    }

    @Override
    public String getSimpleStrategyIds() {
        String[] strategyIds = new String[strategies.size()];
        for (int i = 0; i < strategies.size(); ++i) {
            strategyIds[i] = strategies.get(i).getId();
        }
        return String.join(" | ", strategyIds);
    }

    @Override
    public double getSubTotal(SaleLineItem item) {
        double subTotal = Double.MAX_VALUE;
        for (IPricingStrategy strategy : strategies) {
            subTotal = Math.min(subTotal, strategy.getSubTotal(item));
        }
        return subTotal;
    }
}
