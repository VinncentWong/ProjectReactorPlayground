package centwong.reactor;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

public class A018_FlatMap {
    public static void main(String[] args) {
        /*
        flatMap doesn't reserve order
         */
        Flux<Integer> fluxInt = Flux.just(1, 2, 3, 4);
        Flux<Integer> fluxFlat = fluxInt
                .flatMap(A018_FlatMap::transformAsyncPublisherDelay);
        fluxFlat.subscribe(System.out::println);

        /*
        you can use flatMapIterable that will transform into a Iterable and then
        pass it into a Flux
         */
    }

    public static Publisher<Integer> transformAsyncPublisherDelay(int i) {
        return i % 2 == 0
                ? Flux.just(i, i+1)
                .delayElements(Duration.ofMillis(1))
                : Mono.just(i * 10);
    }
}
