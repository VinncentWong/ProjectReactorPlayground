package centwong.reactor;

import reactor.core.publisher.Flux;

public class A023_Concat {
    public static void main(String[] args) {
        /*
        concat works like mergeSequantially
        It subcsribe stream by stream not all stream together
         */
        Flux<Integer> flux1 = Flux.just(1,2,3);
        Flux<Integer> flux2 = Flux.just(4,5,6);
        Flux<Integer> flux3 = flux1.concatWith(flux2);
        flux3.subscribe(System.out::println);
    }
}
