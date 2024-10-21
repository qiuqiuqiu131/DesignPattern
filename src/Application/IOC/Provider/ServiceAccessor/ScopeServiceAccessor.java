package Application.IOC.Provider.ServiceAccessor;

public class ScopeServiceAccessor extends ServiceAccessorBase {

    public ScopeServiceAccessor(Class<?> clazz) {
        super(clazz);
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
