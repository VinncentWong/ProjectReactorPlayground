package centwong.observer;

public interface Subject<T>{
    void registerObserver(Observer<T> observer);
    void unregisterObserver(Observer<T> observer);
    void notifyObserver(T event);
}
