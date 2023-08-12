package centwong.reactor;

import reactor.core.publisher.Flux;

public class A006_SubscriptionFluxMono {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.range(1,100);
        flux.subscribe(
                (v) -> {
                    System.out.printf("v = %d\n", v);
                },
                (e) -> {
                    /*
                    do nothing
                     */
                },
                () -> {
                    /*
                    do nothing
                     */
                },
                (s) -> {
                    System.out.println("ask first 10 data");
                    s.request(10L);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                    System.out.println("ask second 10 data");
                    s.request(10L);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                    s.cancel();
                    System.out.println("CANCELLED");
                }
        );
    }
}
