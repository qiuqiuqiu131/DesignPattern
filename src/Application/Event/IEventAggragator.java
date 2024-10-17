package Application.Event;

public interface IEventAggragator {
    <T extends IPubSubEvent<?>> T GetEvent(Class<T> clazz);

    public <T extends IPubSubEmptyEvent> T GetEmptyEvent(Class<T> clazz);
}
