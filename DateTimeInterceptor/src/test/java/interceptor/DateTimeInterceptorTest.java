package interceptor;

import org.apache.flume.Event;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DateTimeInterceptorTest {

    @Test
    public void intercept() {
        Event event = mock(Event.class);
        String stringToTest = "SyuXkLmzj6,161.258481776363481685621081851422786712646484375,2018-10-30 12:59:37,8tgYjXGINi,81.212.56.25";

        when(event.getBody()).thenReturn(stringToTest.getBytes());
        when(event.getHeaders()).thenReturn(new HashMap<String, String>());
        new DateTimeInterceptor().intercept(event);

        Map<String, String> headers = event.getHeaders();
        assertEquals("1540893577000", headers.get("timestamp"));
    }


    @Test
    public void intercept1() {
        Event event1 = mock(Event.class);
        Event event2 = mock(Event.class);
        String stringToTest1 = "SyuXkLmzj6,161.258481776363481685621081851422786712646484375,1980-01-01 00:00:01,8tgYjXGINi,81.212.56.25";
        String stringToTest2 = "SyuXkLmzj6,161.258481776363481685621081851422786712646484375,2018-10-30 12:59:37,8tgYjXGINi,81.212.56.25";

        when(event1.getBody()).thenReturn(stringToTest1.getBytes());
        when(event1.getHeaders()).thenReturn(new HashMap<String, String>());
        when(event2.getBody()).thenReturn(stringToTest2.getBytes());
        when(event2.getHeaders()).thenReturn(new HashMap<String, String>());

        List<Event> before = new ArrayList<>();
        before.add(event1); before.add(event2);

        List<Event> after = new DateTimeInterceptor().intercept(before);

        Map<String, String> headers1 = after.get(0).getHeaders();
        assertEquals("315522001000", headers1.get("timestamp"));

        Map<String, String> headers2 = after.get(1).getHeaders();
        assertEquals("1540893577000", headers2.get("timestamp"));

    }
}