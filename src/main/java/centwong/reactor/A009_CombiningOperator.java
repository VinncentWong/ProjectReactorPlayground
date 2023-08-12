package centwong.reactor;

import reactor.core.publisher.Flux;

public class A009_CombiningOperator {
    public static void main(String[] args) {
        Flux<String> flux = Flux.concat(
                Flux.just("A, B, C"),
                Flux.just("D, E, F"),
                Flux.just("H, I, J")
        );
        flux.subscribe(System.out::println);

        Flux<String> flux2 = Flux.merge(
                Flux.just("A, B, C"),
                Flux.just("D, E, F"),
                Flux.just("H, I, J")
        );
        flux.subscribe(System.out::println);
    }
}
