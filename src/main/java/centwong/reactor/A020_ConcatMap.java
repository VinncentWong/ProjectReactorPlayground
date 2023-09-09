package centwong.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class A020_ConcatMap {
    public static void main(String[] args) {
        /*
        You can use concatMap for preserve order in Flux

        It works like flatMap but little a bit different in preserving order

        flatMapSequential basically subcsribe just like flatMap
        however, if the bigger number has finished first and the
        smaller number not yet finished, it will queue the order
        so at the end, the result will have preserve order

        concatMap on other side works differently, it really
        subscribe from the source order so
        if you have 1,2,3
        it will focused on finished 1, then 2, and then 3

        concatMap naturally preserves the same order as the source elements.
         */
        var res = Flux.just(1,2,5,10,12)
                .flatMapSequential((v) -> Mono.just(100 * v))
                .concatMap((v) -> Mono.just(v * 10));
    }
}
