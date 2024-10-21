package Application.IOC.Interface;

public interface IServiceAccessor {
    Object Resolve() throws Exception;

    void SetServiceProvider(IGetService getService);
}
