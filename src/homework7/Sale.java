package homework7;


import java.util.ArrayList;

public class Sale
    implements Subject {
    ArrayList<Observer> observers = new ArrayList<>();

    ArrayList<SaleLineItem> items = new ArrayList<>();

    Sale() {

    }

    public ArrayList<SaleLineItem> getItems() {
        return items;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    void addBook(BookSpecification book, int copies, IPricingStrategy strategy) {
        items.add(
            new SaleLineItem(
                copies, book, strategy
            )
        );
        notifyObservers();
    }

    double getTotal() {
        double total = 0.;
        for (SaleLineItem item : items) {
            total += item.getSubTotal();
        }
        return total;
    }
}
