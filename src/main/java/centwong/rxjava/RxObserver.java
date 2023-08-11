package centwong.rxjava;

/*
RxObserver basically inspired by Iterator class

but instead of calling next(), the RxObserver will be notified by onNext()
instead of checking hasNext(), the RxObserver will be notified by onComplete()
and to check whether the error occurred, you can use onError()
 */
public interface RxObserver<T> {
    void onNext(T next);
    void onComplete();
    void onError(Throwable e);
}
