package Application.IOC.Provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Application.IOC.ServiceDescriptor;
import Application.IOC.Interface.IScopeServiceProvider;
import Application.IOC.Interface.IServiceAccessor;
import Application.IOC.Interface.IServiceProvider;
import Application.IOC.Interface.IServiceProviderExtens;

public class ServiceProvider implements IServiceProvider, IServiceProviderExtens {
    private Map<Class<?>, IServiceAccessor> serviceCollection = new HashMap<>();
    private AccessorFactory accessorFactory;

    public ServiceProvider(List<ServiceDescriptor> serviceCollection) {
        accessorFactory = new AccessorFactory(serviceCollection, this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T GetService(Class<T> clazz) throws Exception {
        if (clazz == IServiceProvider.class)
            return (T) this;

        if (serviceCollection.containsKey(clazz))
            return (T) serviceCollection.get(clazz).Resolve();

        IServiceAccessor accessor = accessorFactory.CreateAccessor(clazz);
        if (accessor == null)
            throw new Exception(clazz.getName() + " is not registered in Collection");

        serviceCollection.put(clazz, accessor);
        return (T) accessor.Resolve();
    }

    @Override
    public IScopeServiceProvider CreateScope() {
        return new ScopeServiceProvider(this);
    }

    @Override
    public IServiceAccessor GetServiceAccessor(Class<?> clazz) throws Exception {
        if (serviceCollection.containsKey(clazz))
            return serviceCollection.get(clazz);

        IServiceAccessor accessor = accessorFactory.CreateAccessor(clazz);
        if (accessor == null)
            throw new Exception("This Service is not registered in Collection");

        serviceCollection.put(clazz, accessor);
        return accessor;
    }
}
