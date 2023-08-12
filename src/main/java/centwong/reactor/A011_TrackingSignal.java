package centwong.reactor;

import reactor.core.publisher.Flux;

public class A011_TrackingSignal {
    public static void main(String[] args) {
        Flux.concat(
                Flux.just(1,2,3),
                Flux.error(new Exception("error when concat"))
        ).doOnEach(s -> {
            /*
            doOnEach accepting Signal which is equal to all signal(onNext, onSubscribe, etc.)

            it also equals to combination of all method
            doOnNext
            doOnSubscribe
            doOnError
            doOnTerminate
            doOnRequest
            etc.
             */
            System.out.println("accepting signal " + s);
        }).subscribe(
                (v) -> {

                },
                (e) -> {
                    System.out.println("accepting error " + e);
                }
        );
    }
}
