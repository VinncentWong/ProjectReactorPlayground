package centwong.reactor;

import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class A005_FluxMonoFactoryMethod {
    public static void main(String[] args) {
        /*
        There are a lot of Mono/Flux Factory method (we already see some factory
        method on A001_FluxMono)
         */

        /*
        You can create async process on Mono/Flux with CompletableFuture or CompletionStage or Runnable or Callable
         */
        Mono<String> mono = Mono.fromFuture(CompletableFuture.supplyAsync(() -> "hello world"));
        Mono<String> mono2 = Mono.fromCallable(() -> "callable");
        /*
        empty mono is a mono that doesn't have data to emit
         */
        Mono<String> mono3 = Mono.empty();

        /*
        never mono is a mono that will never send a signal, emit data, send error, etc.
         */
        Mono<String> mono4 = Mono.never();
        /*
        error mono is a mono that will always propagate to onError
         */
        Mono<String> mono5 = Mono.error(new IllegalArgumentException("error argument"));

        Mono<Runnable> mono6 = Mono.defer(() -> {
            System.out.println("Deferred");
            return validateNumber(10) ?
                    Mono.fromRunnable(() -> {
                    /*
                    Runnable code
                     */
                        System.out.println("Runnable code");
                    })
                    :
                    Mono.error(new IllegalArgumentException("must be even number"));
        });
        mono6.subscribe(Runnable::run, (e) -> System.out.printf("Error occurred with message %s", e.getMessage()));
    }

    public static boolean validateNumber(int number){
        return number % 2 == 0;
    }
}
