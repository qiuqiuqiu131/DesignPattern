package Application.Event;

import java.util.function.Consumer;

public interface IPubSubEvent<T> {
    public abstract void Subscribe(Consumer<T> action);

    public abstract void Desubscribe(Consumer<T> action);

    public abstract void Publish(T obj);
}
