package Application.IOC.Provider.ServiceAccessor;

import Application.IOC.Interface.IServiceProvider;

public class TransientServiceAccessor extends ServiceAccessorBase {

    public TransientServiceAccessor(Class<?> clazz, IServiceProvider serviceProvider) {
        super(clazz, serviceProvider);
    }

    @Override
    protected Object ResolveObject() throws Exception {
        try {
            return CreateObject();
        } catch (Exception e) {
            throw e;
        }
    }

}
