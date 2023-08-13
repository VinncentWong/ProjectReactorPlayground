package centwong.reactor;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class A015_HotPublisher {
    public static void main(String[] args) throws InterruptedException{
        Flux<Integer> hotFlux = Flux.range(1,100)
                .doOnSubscribe((s) -> {
            System.out.println("New subscription added");
        });
        /*
        ConnectableFuture.publish() is still waiting for the subcsriber, however, it share
        to all subscriber in real-time. However, ConnectableFuture already share the
        data into all subscriber.
         */
//        ConnectableFlux<Integer> connectableFlux = hotFlux.publish();

        /*
        use share() instead
        it will turn the cold into hot publisher
         */
        Flux<Integer> connectableFlux = hotFlux.share();
        connectableFlux.subscribe((v) -> {
            System.out.println("Accepting value sub 1 " + v);
        });
        connectableFlux.subscribe((v) -> {
            System.out.println("Accepting value sub 2 " + v);
        });
        System.out.println("Finish");
        /*
        activate the stream
         */
//        connectableFlux.connect();
    }
}
