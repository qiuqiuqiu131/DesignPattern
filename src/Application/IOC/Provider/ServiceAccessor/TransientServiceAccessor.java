package Application.IOC.Provider.ServiceAccessor;

public class TransientServiceAccessor extends ServiceAccessorBase {

    public TransientServiceAccessor(Class<?> clazz) {
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
