package centwong.reactor;

import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;

public class A013_HandlingErrors {
    public static void main(String[] args) {
        Flux.just(1)
                .flatMap((v) -> validateUserId(v)
                        .retryWhen(Retry.backoff(5, Duration.ofMillis(100)))
                        .timeout(Duration.ofSeconds(5))
                        .onErrorResume((e) -> {
                            System.out.println("onErrorResume");
                            return Flux.just(-1);
                        })
                )
                .subscribe((v) -> {
                    System.out.println("data = " + v);
                }, (e) -> {
                    System.out.println("e = " + e);
                }, () -> {
                    System.out.println("Complete");
                });
    }

    public static Flux<Integer> validateUserId(Integer userId){
        return Flux.defer(
                () -> {
                    if(userId < 10){
                        System.out.println("User id < 10");
                        return Flux
                                .<Integer>error(new IllegalArgumentException("angka kurang dari sepuluh"))
                                .delaySequence(Duration.ofMillis(100));
                    } else {
                        System.out.println("User id > 10");
                        return Flux
                                .<Integer>just(11,12,13)
                                .delayElements(Duration.ofMillis(50));
                    }
                }
        ).doOnSubscribe((s) -> {
            System.out.println("Request for " + userId);
        });
    }
}
