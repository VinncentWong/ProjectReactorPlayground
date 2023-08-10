package centwong.reactor;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;

public class A002_Disposable {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1,2,3,4,5);

        /*
        Disposable means that the subscription can be cancelled or just used one time
         */
        Disposable disposable = flux.subscribe((v) -> {
            System.out.printf("v = %d\n", v);
        });

        /*
        Cancelled the Mono/Flux
         */
        disposable.dispose();
        /*
        Cancelling Mono/Flux means that the flux should stop emit items/elements
         */
    }
}
