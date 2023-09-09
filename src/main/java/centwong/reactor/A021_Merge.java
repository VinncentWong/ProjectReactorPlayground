package centwong.reactor;

import reactor.core.publisher.Flux;

public class A021_Merge {
    public static void main(String[] args) {
        Flux<Integer> flux1 = Flux.just(1,2,3);
        Flux<Integer> flux2 = Flux.just(4,5,6);
        Flux<Integer> flux3 = Flux.merge(flux1, flux2); // or if u use instance object, u can call mergeWith()
        flux3.subscribe((v) -> { // however, merge is not preserve order so u should be careful
            /*
            the cause of not preserve order because it subcsribe to all stream together
            1
            2
            3
            4
            5
            6
             */
            System.out.println("v = " + v);
        });
        Flux<Integer> flux4 = flux1.mergeWith(flux2); // same like code above
        flux4.subscribe(System.out::println);
    }
}
