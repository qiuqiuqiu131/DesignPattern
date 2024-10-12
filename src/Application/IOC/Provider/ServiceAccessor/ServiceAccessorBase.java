package Application.IOC.Provider.ServiceAccessor;

import java.lang.reflect.Constructor;

import Application.IOC.Interface.IServiceAccessor;
import Application.IOC.Interface.IServiceProvider;

public abstract class ServiceAccessorBase implements IServiceAccessor {
    protected Class<?> clazz;
    protected IServiceProvider serviceProvider;

    protected ServiceAccessorBase(Class<?> clazz, IServiceProvider serviceProvider) {
        this.clazz = clazz;
        this.serviceProvider = serviceProvider;
    }

    protected abstract Object ResolveObject() throws Exception;

    protected Object CreateObject() throws Exception {
        Constructor<?> selectConstractor = null;
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            // 检查构造器的参数数量
            boolean hasPrimitive = false;
            Class<?>[] clazzs = constructor.getParameterTypes();
            for (Class<?> cla : clazzs) {
                if (cla.isPrimitive())
                    hasPrimitive = true;
            }
            if (hasPrimitive)
                continue;
            if (selectConstractor == null || constructor.getParameterCount() > selectConstractor.getParameterCount()) {
                selectConstractor = constructor;
            }
        }

        if (selectConstractor == null)
            throw new Exception(
                    clazz.getName() + " need a none primitive parameter constractor or a none parameter contractor");

        Class<?>[] clazzs = selectConstractor.getParameterTypes();
        Object[] parameters = new Object[selectConstractor.getParameterCount()];
        for (int i = 0; i < selectConstractor.getParameterCount(); i++) {
            Class<?> cla = clazzs[i];
            Object obj = null;
            try {
                obj = serviceProvider.GetService(cla);
            } catch (Exception e) {
            }
            parameters[i] = obj;
        }

        try {
            Object result = selectConstractor.newInstance(parameters);
            return result;
        } catch (Exception e) {
            throw new Exception(clazz.getName() + " constract default");
        }
    }

    @Override
    public Object Resolve() throws Exception {
        return ResolveObject();
    }
}
