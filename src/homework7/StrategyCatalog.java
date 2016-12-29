package homework7;

import java.util.HashMap;

public class StrategyCatalog {
    HashMap<Integer, IPricingStrategy> strategies = new HashMap<>();

    public HashMap<Integer, IPricingStrategy> getStrategies() {
        return strategies;
    }

    void addStrategy(IPricingStrategy strategy)
        throws Exception {
        if (strategies.containsKey(strategy.getBookType())) {
            throw new Exception("该图书类型已有对应策略");
        } else {
            strategies.put(strategy.getBookType(), strategy);
        }
    }

    void deleteStrategy(int bookType) {
        strategies.remove(bookType);
    }

    void updateStrategy(int bookType, IPricingStrategy strategy) {
        strategies.replace(bookType, strategy);
    }

    IPricingStrategy getStrategy(int bookType) {
        return strategies.containsKey(bookType)?
            strategies.get(bookType) : new NoDiscountStrategy();
    }
}
