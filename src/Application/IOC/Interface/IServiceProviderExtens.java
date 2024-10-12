package Application.IOC.Interface;

public interface IServiceProviderExtens {
    public IServiceAccessor GetServiceAccessor(Class<?> clazz) throws Exception;
}
