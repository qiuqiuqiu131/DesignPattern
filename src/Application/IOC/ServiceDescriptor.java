package Application.IOC;

public class ServiceDescriptor {
    private boolean hasInterface;
    private Class<?> iClass;
    private Class<?> clazz;
    private ServiceLiftTime lifeTime;

    public ServiceDescriptor(Class<?> clazz, Class<?> iClass, ServiceLiftTime lifeTime) {
        hasInterface = (iClass != null);
        this.iClass = iClass;
        this.clazz = clazz;
        this.lifeTime = lifeTime;
    }

    public boolean HasInterface() {
        return hasInterface;
    }

    public Class<?> GetInterFace() throws Exception {
        if (!hasInterface)
            throw new Exception("The interface doesn't exist");
        return iClass;
    }

    public Class<?> GetClazz() {
        return clazz;
    }

    public ServiceLiftTime GetLiftTime() {
        return lifeTime;
    }
}
