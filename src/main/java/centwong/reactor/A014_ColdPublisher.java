package centwong.reactor;

import reactor.core.publisher.Flux;

import java.util.UUID;

public class A014_ColdPublisher {
    public static void main(String[] args) {
        Flux<String> coldFlux = Flux.defer(() -> {
            System.out.println("Generate item");
            return Flux.just(UUID.randomUUID().toString());
        });
        coldFlux.subscribe((v) -> {
            System.out.println("Subscriber 1 accepting data " + v);
        });
        coldFlux.subscribe((v) -> {
            System.out.println("Subscriber 2 accepting data " + v);
        });
        System.out.println("Data generated twice for 2 subscriber");
    }
}
