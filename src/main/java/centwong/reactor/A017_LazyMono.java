package centwong.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class A017_LazyMono {
    public static void main(String[] args) {
        // create lazy Mono that will called only when we call Subscribe
        // this defer also make a consumer can consume this res infinite times, this because defer make new Publisher everytime we call subscription
        // if u not use defer, then you can only consume this res 1 time
//        var res = Flux.fromStream(Stream.of(1, 2, 3)); please uncomment this code and comment code below to see the difference
        var res = Flux.defer(() -> Flux.fromStream(Stream.of(1,2,3))); // create new Object everytime new subscription made. This also make the stream lazily created -> or u can say created only when subscription happened
        /*
        by default, all from*() method is eager to create object inside itself
        so you will always consumed to same object. Eager make the creation of the
        object is when u use the factory method.

        To make lazily, please use defer() so u can subcsribe to another new Publisher everytime subscribe()

        In Project Reactor, methods that start with "from" typically indicate that
        they are used to create a reactive stream (like Mono or Flux) from some source,
         and the creation of that source is eager, but the execution of the source
         (e.g., invoking a Callable or creating a Flux from an iterable) is deferred
         until someone subscribes.

         In all of these cases, the creation of the reactive stream itself is eager
         (when you call the method), but the actual execution or processing of the
         underlying source is deferred until a subscriber expresses interest by calling
         subscribe().
         */
        Mono.just(generateValue()); // create eager Mono that will called once the Mono was created
        Mono.fromFuture(CompletableFuture.supplyAsync(() -> { // Mono.fromFuture and Mono.fromCompletionStage is also eager by default, u can make it lazily by providing Supplier params
            System.out.println("Terpanggil dalam future");
            return "";
        }));
        res.subscribe((v) -> {
            System.out.println("Accept v on subscribe 1 with value " + v);
        });
        res.subscribe((v) -> {
            System.out.println("Accept v on subscribe 2 with value " + v);
        });
        res.subscribe((v) -> {
            System.out.println("Accept v on subscribe 3 with value " + v);
        });
    }

    public static String generateValue(){
        try{
            System.out.println("Terpanggil");
            return 1/0 + "";
        } catch(Exception ex){
            return ex.getMessage();
        }
    }
}
