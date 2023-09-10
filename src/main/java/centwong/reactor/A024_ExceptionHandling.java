package centwong.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class A024_ExceptionHandling {
    public static void main(String[] args) {
        /*
        doOnError has > 1 version but all of them mechanism is same
        They execute the Consumer then the onError signal is propagated
         */
        Flux<Integer> fluxInteger = Flux.just(1,2,3,4,5);
        fluxInteger
                .filter((i) -> i > 10)
                .switchIfEmpty(Mono.error(new ArithmeticException("tidak ada yang > 10")))
                /*
                at the end, the error will be propagated into onError
                 */
                .doOnError((v) -> { // works like catch block
                    System.out.println("v = " + v.getMessage());
                })
                .doFinally((s) -> {
                    /*
                    finally is after a sequence terminated(because of cancellation or exception)
                     */
                    System.out.println("s = " + s);
                }) // works like finally block
                .subscribe(System.out::println, System.out::println);
    }
}
