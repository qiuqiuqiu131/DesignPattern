package Application.IOC.Interface;

public interface IServiceProvider {
    <T> T GetService(Class<T> clazz) throws Exception;

    IScopeServiceProvider CreateScope();
}
