package Application.Event;

import java.util.LinkedList;
import java.util.List;

public class PubSubEmptyEvent implements IPubSubEmptyEvent {

    private List<Runnable> list = new LinkedList<>();

    @Override
    public void Subscribe(Runnable action) {
        list.add(action);
    }

    @Override
    public void Desubscribe(Runnable action) {
        list.remove(action);
    }

    @Override
    public void Publish() {
        for (var runnable : list) {
            runnable.run();
        }
    }

}
