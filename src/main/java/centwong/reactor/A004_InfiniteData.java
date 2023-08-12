package centwong.reactor;

import reactor.core.publisher.Flux;

public class A004_InfiniteData {
    public static void main(String[] args) {
        Flux<Integer> infiniteFlux = Flux.range(1, 5)
                .repeat();
        infiniteFlux.subscribe((v) -> {
            System.out.printf("Accepting value %d\n", v);
        });

        /*
        Since the data is consumed, it's okay to handle infinite data
        but beware not to collect the infinite data into List
        since it will cause OutOfMemoryException

        Please comment code below to prevent from OutOfMemoryException
         */
        infiniteFlux
                .collectList()
                .block();
    }
}
