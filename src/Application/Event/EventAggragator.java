package Application.Event;

import java.util.Map;
import java.util.HashMap;

public class EventAggragator implements IEventAggragator {
    private Map<Class<?>, IPubSubEvent<?>> events = new HashMap<Class<?>, IPubSubEvent<?>>();

    private Map<Class<?>, IPubSubEmptyEvent> emptyEvents = new HashMap<>();

    public EventAggragator() {
    }

    @Override
    public <T extends IPubSubEvent<?>> T GetEvent(Class<T> clazz) {
        if (events.containsKey(clazz))
            return clazz.cast(events.get(clazz));

        try {
            T event = clazz.getDeclaredConstructor().newInstance();
            events.put(clazz, event);
            return event;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T extends IPubSubEmptyEvent> T GetEmptyEvent(Class<T> clazz) {
        if (emptyEvents.containsKey(clazz))
            return clazz.cast(emptyEvents.get(clazz));

        try {
            T event = clazz.getDeclaredConstructor().newInstance();
            emptyEvents.put(clazz, event);
            return event;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
