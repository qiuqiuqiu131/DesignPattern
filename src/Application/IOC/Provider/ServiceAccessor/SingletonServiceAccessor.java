package Application.IOC.Provider.ServiceAccessor;

import Application.IOC.Interface.IServiceProvider;

public class SingletonServiceAccessor extends ServiceAccessorBase {
    protected Object object = null;

    public SingletonServiceAccessor(Class<?> clazz, IServiceProvider serviceProvider) {
        super(clazz, serviceProvider);
    }

    @Override
    protected Object ResolveObject() throws Exception {
        if (object != null)
            return object;

        try {
            object = CreateObject();
            return object;
        } catch (Exception e) {
            throw e;
        }
    }

}
