package centwong.reactor;

import reactor.core.publisher.Flux;

public class A012_MaterializeDematerialize {
    public static void main(String[] args) {
        Flux.range(1,3)
                .materialize()
                .doOnNext((s) -> {
                    System.out.println("Signal = " + s);
                })
                .dematerialize()
                .subscribe((v) -> {
                    System.out.println("V = " + v);
                });
    }
}
