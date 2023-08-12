package centwong.reactor;

import reactor.core.publisher.Flux;

import java.util.List;

public class A010_BatchOperator {
    public static void main(String[] args) {

        /*
        Buffering is a process where we collect some data from the stream
        into a List

        All data from the stream will be converted into a List with size
        from the parameter of the buffer
         */
        Flux.just(1,2,3,4,5)
                .buffer(3) // Will result List<Integer>
                .subscribe((v) -> {
                    /*
                    Buffering = [1, 2, 3]
                    Buffering = [4, 5]
                     */
                    System.out.println("Buffering = " + v);
                });

        Flux<Flux<Integer>> windowsFlux = Flux.range(100, 50)
                .windowUntil(integer -> integer % 2 != 0, true);

        /*
        Collect List make the stream into a List that is wrapped inside a Mono
         */
        windowsFlux
                .subscribe(
                        flux -> {
                            flux.collectList()
                                    .subscribe(
                                            (v) -> {
                                                System.out.println("v = " + v);
                                            }
                                    );
                        }
                );
    }
}
