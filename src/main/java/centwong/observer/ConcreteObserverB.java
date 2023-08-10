package centwong.observer;

import centwong.MessageFormatterUtil;

public class ConcreteObserverB implements Observer<String>{

    private final MessageFormatterUtil util = MessageFormatterUtil.INSTANCE;

    @Override
    public void observe(String event) {
        System.out.println(this.util.format("Event on {0} = {1}", ConcreteObserverB.class.getName(),event));
    }
}
