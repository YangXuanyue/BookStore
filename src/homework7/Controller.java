package homework7;

import java.util.ArrayList;

public class Controller {
    private BookCatalog bookCatalog = new BookCatalog();
    private StrategyCatalog strategyCatalog = new StrategyCatalog();
    Sale sale;

    public Controller() {
        PricingStrategyFactory.getInstance().setCatalog(strategyCatalog);
    }

    public BookCatalog getBookCatalog() {
        return bookCatalog;
    }

    public StrategyCatalog getStrategyCatalog() {
        return strategyCatalog;
    }

    public void addBook(String title, String isbn, int type, double price)
        throws Exception {
        try {
            bookCatalog.addBook(
                new BookSpecification(
                    title, isbn, type, price
                )
            );
        } catch (Exception x) {
            throw x;
        }
    }

    public void addSimpleStrategy(
        String id,
        String name,
        int type,
        int bookType,
        double discount
    ) throws Exception {
        try {
            switch (type) {
                case IPricingStrategy.FLAT_RATE:
                    strategyCatalog.addStrategy(
                        new FlatRateStrategy(
                            id, name, bookType, discount
                        )
                    );
                    break;
                case IPricingStrategy.PERCENTAGE:
                    strategyCatalog.addStrategy(
                        new PercentageStrategy(
                            id, name, bookType, discount
                        )
                    );
                    break;
            }
        } catch (Exception x) {
            throw x;
        }
    }

    public void addCompositeStrategy(
        String id,
        String name,
        int bookType,
        ArrayList<Integer> bookTypeOfStrategies
    ) throws Exception {
        try {
            CompositeStrategy compositeStrategy = new CompositeStrategy(
                id, name, bookType
            );
            for (int bookTypeOfStrategy : bookTypeOfStrategies) {
                compositeStrategy.add(
                    strategyCatalog.getStrategy(bookTypeOfStrategy)
                );
            }
            strategyCatalog.addStrategy(compositeStrategy);
        } catch (Exception x) {
            //System.out.println("xxx");
            throw x;
        }
    }

    public void deleteStrategy(int bookType) {
        strategyCatalog.deleteStrategy(bookType);
    }

    public void updateStrategy(
        int bookType,
        String id,
        String name,
        int type,
        double discount,
        ArrayList<Integer> bookTypeOfStrategies
    ) {
        switch (type) {
            case IPricingStrategy.FLAT_RATE:
                strategyCatalog.updateStrategy(
                    bookType,
                    new FlatRateStrategy(
                        id, name, bookType, discount
                    )
                );
                break;
            case IPricingStrategy.PERCENTAGE:
                strategyCatalog.updateStrategy(
                    bookType,
                    new PercentageStrategy(
                        id, name, bookType, discount
                    )
                );
                break;
            case IPricingStrategy.COMPOSITE:
                CompositeStrategy compositeStrategy = new CompositeStrategy(
                    id, name, bookType
                );
                for (int bookTypeOfStrategy : bookTypeOfStrategies) {
                    compositeStrategy.add(
                        strategyCatalog.getStrategy(bookTypeOfStrategy)
                    );
                }
                strategyCatalog.updateStrategy(
                    bookType,
                    compositeStrategy
                );
                break;
        }
    }

    public void buyBook(String isbn, int copies)
        throws Exception {
        try {
            BookSpecification book = bookCatalog.getBookSpecification(isbn);
            IPricingStrategy strategy =
                PricingStrategyFactory
                    .getInstance()
                    .getPricingStrategy(book.getType());
            sale.addBook(book, copies, strategy);
        } catch (Exception x) {
            throw x;
        }
    }

    Sale getSale() {
        return (sale = new Sale());
    }
}
