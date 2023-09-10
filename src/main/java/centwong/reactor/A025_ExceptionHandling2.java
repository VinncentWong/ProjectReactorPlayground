package centwong.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class A025_ExceptionHandling2 {
    public static void main(String[] args) {
        /*
        onErrorReturn is a method where we provide a fallback value when an error occurred
        however, error is still considered as terminal operation on a stream
         */
        Flux.just(1,2,3,4,5)
                .map((i) -> i/(i-3))
                .onErrorReturn(-999)
                .subscribe((v) -> {
                    System.out.println("v = " + v);
                }, (e) -> {
                    /*
                    this one will be not called because the error
                    is not propagated into onError()
                     */
                    System.out.println("e = " + e);
                });

        /*
        or if an error occured and you want to provide a fallback stream,
        you can use onErrorResume(the mechanism works like onErrorReturn())

         the original sequence will be canceled, and onErrorResume will subscribe
         to the fallback publisher to keep emitting elements to the subscriber.
         */
        Flux.just(1,2,3,4,5)
                .map((i) -> i/(i-3))
                .onErrorResume((e) -> Flux.just(-10,-20,-30)) // when an error occurred, the Flux inside it will be automatically subscribed
                .subscribe(System.out::println, System.out::println);

        /*
        if you want to catch an exception and rethrow another exception in catch block,
        you can use onErrorMap
         */
        Flux.just(1,2,3,4,5)
                .map((i) -> i/(i-3))
                .onErrorMap((e) -> new IllegalArgumentException("argument yang dimasukkan tidak sesuai")) // when an error occurred, the Flux inside it will be automatically subscribed
                .subscribe(System.out::println, System.out::println);
    }
}
