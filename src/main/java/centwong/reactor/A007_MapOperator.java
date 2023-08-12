package centwong.reactor;

import reactor.core.publisher.Flux;

public class A007_MapOperator {
    public static void main(String[] args) {
        Flux<String> flux = Flux
                .range(1 ,10)
                .map((v) -> String.format("Nilai terbaru = %d", v));
        flux.subscribe(System.out::println);
    }
}
