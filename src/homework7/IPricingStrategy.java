package homework7;

public interface IPricingStrategy {
    int
        FLAT_RATE = 0,
        PERCENTAGE = 1,
        COMPOSITE = 2;
    String[] typeStrs = {
        "绝对值优惠策略",
        "百分比折扣策略",
        "组合策略"
    };
    String getId();
    String getName();
    int getType();
    int getBookType();
    double getDiscount();
    String getSimpleStrategyIds();
    double getSubTotal(SaleLineItem item);
}
