package centwong.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/*
Flux is the standard implementation of Publisher
that represent an async sequence that emit 0..N items

Remember that the emitted items is optional
 */
public class A001_FluxMono {
    public static void main(String[] args) {

        /*
        Use factory method insteadof new instantiation
         */
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);
        /*
        U also can use iterable collection to pass as an argument
        to Flux factory method
         */
        Iterable<Integer> iterable = List.of(1,2,3,4,5);
        Flux<Integer> fluxIterable = Flux.fromIterable(iterable);

        Mono<String> emptyMono = Mono.empty();
        Mono<String> singleMono = Mono.just("1");

        /*
        First param is start number, and second param is the number of the value

        for example
        first param: 5
        second param: 3

        so the result would be 5,6,7
         */
        Flux<Integer> rangeFlux = Flux.range(5, 3);

        /*
        All of the stream data will never happen if you not call subscribe()

        basically subscribe will trigger the sequence
         */
        flux.subscribe();
        fluxIterable.subscribe((v) -> {
            /*
            or you can do something with the triggered data?
             */
            System.out.printf("emitted data = %d\n", v);
        });
        emptyMono.subscribe();
        singleMono.subscribe();

        /*
        or you can do something with the triggered data and when it error?
         */
        rangeFlux.subscribe((v) -> {
            System.out.printf("emitted data from range flux = %d\n", v);
        }, (e) -> {
            System.out.println("ERROR ERROR ERROR!!!");
        });

        /*
        Here are the more appropriate example of error subscribe
         */
        Flux<Integer> errorFlux = Flux.range(0, 5)
                .map((v) -> {
                    if (v == 3){
                        throw new IllegalArgumentException("v can't be 3");
                    }
                    System.out.printf("stil catching errorFlux with value %d\n", v);
                    return v;
                });
        errorFlux.subscribe((v) -> {

        }, (e) -> {
            System.out.printf("error message = %s", e.getMessage());
        }, () -> {
            /*
            To make the completion consumer work, we must take care not to trigger an error.

            on third param, you can do something when the sequence emitted data complete(done and no error)
             */
            System.out.println("DONE!");
        });
    }
}
