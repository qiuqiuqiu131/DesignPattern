package Application.IOC.Interface;

public interface IScopeServiceProvider {
    <T> T GetService(Class<T> clazz) throws Exception;

    void Dispose();
}
