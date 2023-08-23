package centwong.reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;

public class A016_ReactorContext {
    public static void main(String[] args) {
        String key = "test";
        Flux
                .range(1, 100)
                .flatMap((v) ->
                    Flux.deferContextual((ctx) -> Flux.just(v + "" + ctx.get(key)))
                )
                .contextWrite((ctx) -> ctx.put(key, " hasilnya"))
                .subscribe((d) -> {
                    System.out.println("d = " + d);
                });
    }
}
