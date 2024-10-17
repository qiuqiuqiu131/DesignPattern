package Application.Event;

public interface IPubSubEmptyEvent {
    public abstract void Subscribe(Runnable action);

    public abstract void Desubscribe(Runnable action);

    public abstract void Publish();
}
