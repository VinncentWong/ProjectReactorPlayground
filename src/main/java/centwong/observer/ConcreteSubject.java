package centwong.observer;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSubject implements Subject<String> {

    private final List<Observer<String>> list = new ArrayList<>();

    @Override
    public void registerObserver(Observer<String> observer) {
        list.add(observer);
    }

    @Override
    public void unregisterObserver(Observer<String> observer) {
        list.remove(observer);
    }

    @Override
    public void notifyObserver(String event) {
        this.list.forEach((obs) -> {
            obs.observe(event);
        });
    }
}
