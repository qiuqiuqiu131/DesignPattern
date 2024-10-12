package Application.IOC;

import java.util.ArrayList;
import java.util.List;

import Application.IOC.Interface.IServiceCollection;
import Application.IOC.Interface.IServiceProvider;
import Application.IOC.Provider.ServiceProvider;

public class ServiceCollection implements IServiceCollection {

    private boolean isBuilt;
    private List<ServiceDescriptor> serviceCollection = new ArrayList<>();
    private List<Class<?>> clazzList = new ArrayList<>();

    @Override
    public <T> IServiceCollection RegisterTransient(Class<T> clazz) throws Exception {
        return Register(clazz, null, ServiceLiftTime.Transient);
    }

    @Override
    public <InterT, T extends InterT> IServiceCollection RegisterTransient(Class<InterT> iClazz, Class<T> clazz)
            throws Exception {
        return Register(clazz, iClazz, ServiceLiftTime.Transient);
    }

    @Override
    public <T> IServiceCollection RegisterSingleton(Class<T> clazz)
            throws Exception {
        return Register(clazz, null, ServiceLiftTime.Singleton);
    }

    @Override
    public <InterT, T extends InterT> IServiceCollection RegisterSingleton(Class<InterT> iClazz, Class<T> clazz)
            throws Exception {
        return Register(clazz, iClazz, ServiceLiftTime.Singleton);
    }

    @Override
    public <T> IServiceCollection RegisterScope(Class<T> clazz) throws Exception {
        return Register(clazz, null, ServiceLiftTime.Scope);
    }

    @Override
    public <InterT, T extends InterT> IServiceCollection RegisterScope(Class<InterT> iClazz, Class<T> clazz)
            throws Exception {
        return Register(clazz, iClazz, ServiceLiftTime.Scope);
    }

    private IServiceCollection Register(Class<?> clazz, Class<?> iClazz, ServiceLiftTime liftTime)
            throws Exception {
        if (clazzList.contains(clazz) || iClazz != null && clazzList.contains(iClazz))
            throw new Exception("This Class is already Registered");

        if (iClazz != null)
            clazzList.add(iClazz);
        clazzList.add(clazz);

        serviceCollection.add(new ServiceDescriptor(clazz, iClazz, liftTime));

        return this;
    }

    @Override
    public IServiceProvider BuildServiceProvider() throws Exception {
        if (isBuilt)
            throw new Exception("This ServiceCollection has built");

        isBuilt = true;
        return new ServiceProvider(serviceCollection);
    }

}
