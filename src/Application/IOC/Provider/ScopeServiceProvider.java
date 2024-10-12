package Application.IOC.Provider;

import java.util.HashMap;
import java.util.Map;

import Application.IOC.Interface.IScopeServiceProvider;
import Application.IOC.Interface.IServiceAccessor;
import Application.IOC.Interface.IServiceProviderExtens;
import Application.IOC.Provider.ServiceAccessor.ScopeServiceAccessor;

public class ScopeServiceProvider implements IScopeServiceProvider {
    private IServiceProviderExtens serviceProvider;

    private Map<Class<?>, Object> scopeServices = new HashMap<>();

    public ScopeServiceProvider(IServiceProviderExtens serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T GetService(Class<T> clazz) throws Exception {
        if (scopeServices.containsKey(clazz))
            return (T) scopeServices.get(clazz);

        IServiceAccessor accessor = serviceProvider.GetServiceAccessor(clazz);
        Object res = accessor.Resolve();
        if (accessor instanceof ScopeServiceAccessor)
            scopeServices.put(clazz, res);
        return (T) res;
    }

    @Override
    public void Dispose() {
        scopeServices.clear();
        serviceProvider = null;
    }
}
