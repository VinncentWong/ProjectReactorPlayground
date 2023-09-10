package centwong.reactor;

import reactor.core.publisher.Flux;
import reactor.util.context.Context;
import reactor.util.context.ContextView;

public class A027_Context {
    public static void main(String[] args) {
        /*
        Context is instance of ContextView that is a readonly context

        Context is similar to ThreadLocal impl but safe on switching Thread
        since ThreadLocal is not safe when switching Thread

        You should notice too that Context is Immutable so adding new data
        means creating new Context with all old values + your new added value

        Context is written in Subscription time
         */

        // create context with factory method .of()
        Context context = Context.of("id", 10);

        /*
        adding new entries
         */
        context.put("name", "CentWong");

        // you can convert it into ContextView too
        ContextView contextView = context.readOnly();

        Flux.just(1,2,3,4,5)
                .map(i -> i * 10)
                .transformDeferredContextual((flux, ctx) -> {
                    /*
                    to get context, you can use transformDeferredContextual(Flux) or deferContextual(Mono)
                     */
                    return flux.map((i) -> i * ctx.getOrDefault("id", -100));
                })
                .contextWrite(Context.of("id", -1)) // adding new context -> create new context
                .subscribe(System.out::println); // creating empty context -> each Subsciber has it's own Context
    }
}
