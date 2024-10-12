package Application.IOC.Interface;

public interface IServiceCollection {

        <T> IServiceCollection RegisterTransient(Class<T> clazz)
                        throws Exception;

        <InterT, T extends InterT> IServiceCollection RegisterTransient(Class<InterT> iClazz, Class<T> clazz)
                        throws Exception;

        <T> IServiceCollection RegisterSingleton(Class<T> clazz)
                        throws Exception;

        <InterT, T extends InterT> IServiceCollection RegisterSingleton(Class<InterT> iClazz, Class<T> clazz)
                        throws Exception;

        <T> IServiceCollection RegisterScope(Class<T> clazz)
                        throws Exception;

        <InterT, T extends InterT> IServiceCollection RegisterScope(Class<InterT> iClazz, Class<T> clazz)
                        throws Exception;

        IServiceProvider BuildServiceProvider()
                        throws Exception;
}
