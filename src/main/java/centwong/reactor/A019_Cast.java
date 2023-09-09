package centwong.reactor;

import reactor.core.publisher.Flux;

public class A019_Cast {
    public static void main(String[] args) {
        /*
        cast works like instanceof operator
         */
        Flux<Number> flux1 = Flux.just(1L, 2L, 3L);
        Flux<Long> flux2 = flux1.cast(Long.class);
        flux2.subscribe(System.out::println);
    }
}
