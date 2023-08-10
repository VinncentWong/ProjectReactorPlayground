package centwong.observer;

import centwong.MessageFormatterUtil;

public class ConcreteObserverA implements Observer<String>{

    private final MessageFormatterUtil util = MessageFormatterUtil.INSTANCE;

    @Override
    public void observe(String event) {
        System.out.println(this.util.format("Event on {0} = {1}", ConcreteObserverA.class.getName(),event));
    }
}
