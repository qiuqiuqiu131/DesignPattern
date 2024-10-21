package Application.Event;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class PubSubEvent<T> implements IPubSubEvent<T> {

    private List<Consumer<T>> list = new LinkedList<>();

    public PubSubEvent() {
    }

    @Override
    public void Subscribe(Consumer<T> action) {
        list.add(action);
    }

    @Override
    public void Desubscribe(Consumer<T> action) {
        Consumer<T> obj = null;
        for (var c : list) {
            if (c.equals(action)) {
                obj = c;
                break;
            }
        }
        if (obj != null)
            list.remove(obj);
    }

    @Override
    public void Publish(T obj) {
        for (var c : list) {
            c.accept(obj);
        }
    }
}
