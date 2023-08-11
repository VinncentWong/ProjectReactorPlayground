package centwong.rxjava;

import centwong.MessageFormatterUtil;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class A001_Observable {
    public static void main(String[] args) {
        /*
        create an observable that know what to do
        when subscriber exist(with subscribe() method)
         */
        Observable<String> observable = Observable.<String>create(
                (sub) -> {
                    sub.onNext("hello world");
                    sub.onComplete();
                }
        );
        observable.subscribe(new CustomSubscriber());
    }
}

class CustomSubscriber implements Observer<String> {

    private final MessageFormatterUtil util = MessageFormatterUtil.INSTANCE;

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(String s) {
        System.out.println(util.format("accepting data {0}", s));
    }

    @Override
    public void onError(Throwable t) {
        System.out.println(util.format("acceptign error {0}", t.getMessage()));
    }

    @Override
    public void onComplete() {
        System.out.println("Finish!!!");
    }
}