package centwong;

import java.text.MessageFormat;

public class MessageFormatterUtil {

    private MessageFormatterUtil(){}

    public static final MessageFormatterUtil INSTANCE = new MessageFormatterUtil();

    public String format(String pattern, Object... data){
        return MessageFormat.format(pattern, data);
    }
}
