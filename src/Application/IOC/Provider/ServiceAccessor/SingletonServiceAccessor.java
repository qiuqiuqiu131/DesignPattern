package Application.IOC.Provider.ServiceAccessor;

public class SingletonServiceAccessor extends ServiceAccessorBase {
    protected Object object = null;

    public SingletonServiceAccessor(Class<?> clazz) {
        super(clazz);
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
