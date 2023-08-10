package centwong.reactor;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import java.text.MessageFormat;

/*
As an alternative of lambda exp on subscribe(), you can
use the subclass of BaseSubscriber
 */
public class A003_BaseSubscriber {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1,2,3,4,5);
        flux.subscribe(new CustomSubscriber<>());
    }
}

/*
All base subscriber all single use meaning that once you subscribe
into other Publisher then the first one will be removed from
subscription list
 */
final class CustomSubscriber<T> extends BaseSubscriber<T>{

    /*
    HookOnSubscribe is a point where we can modified the behavior when subscribe is called
     */
    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        System.out.println(MessageFormat.format("Subscribtion = {0}", subscription));
        request(1);
    }

    @Override
    protected void hookOnNext(T value) {
        System.out.printf("hookOnNest with value %s\n", value);
        request(1);
    }
}
