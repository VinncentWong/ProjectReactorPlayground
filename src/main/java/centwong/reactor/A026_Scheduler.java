package centwong.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class A026_Scheduler {
    public static void main(String[] args) throws InterruptedException {
        /*
        every operator is executed on main thread

        you can use Schedulers to determine which worker to use on a sequence

        you can use publishOn to make downstream operator run on a different worker

        you should CAREFUL to use this method since switcher thread is a expensive operation!
         */
        Flux.just(1,2,3,4,5)
                .publishOn(Schedulers.boundedElastic())
                .map((i) -> {
                    System.out.printf("""
                            map[%d] - %s
                            """, i, Thread.currentThread().getName());
                    return i * 10;
                })
                .flatMap((i) -> {
                    System.out.printf("""
                            flatMap[%d] - %s
                            """, i, Thread.currentThread().getName());
                    return Mono.just(i * 10);
                })
                .subscribe((i) -> {
                    System.out.printf("""
                            subscribe[%d] - %s
                            """, i, Thread.currentThread().getName());
                });
        Thread.sleep(1000);
    }
}
