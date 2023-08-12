package centwong.reactor;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Instant;
import java.util.stream.Stream;

public class A008_IndexTimestampOperator {
    public static void main(String[] args) {
        /*
        use var for best practice, don't declare Flux<Tuple2<Long, Tuple2<Long, Integer>>>
         */
        Flux<Tuple2<Long, Tuple2<Long, Integer>>> flux = Flux.range(1, 10)
                .index()
                .timestamp();
        flux.subscribe((v) -> {
            /*
            T1 is a time in a Long
             */
            System.out.printf("vT1 = %s\n", Instant.ofEpochMilli(v.getT1()));
            System.out.printf("vT2 = %s\n", v.getT2());
            System.out.printf("v.T1 = %d\n", v.getT2().getT1());
            System.out.printf("v.T2 = %s\n", v.getT2().getT2());
        });
    }
}
