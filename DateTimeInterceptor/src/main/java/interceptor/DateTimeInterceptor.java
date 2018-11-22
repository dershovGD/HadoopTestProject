package interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateTimeInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        byte[] bytes = event.getBody();
        String body = new String(bytes);
        Timestamp localDateTime = Timestamp.valueOf(body.split(",")[2]);
        long time = localDateTime.getTime();
        Logger slf4jLogger = LoggerFactory.getLogger("some-logger");
        slf4jLogger.info("Intercepted timestamp= {}", localDateTime);

        Map<String, String> headers = event.getHeaders();
        headers.put("timestamp", String.valueOf(time));

        return event;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        for (Event e : events){
            intercept(e);
        }

        return events;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder{
        @Override
        public Interceptor build() {
            return new DateTimeInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
