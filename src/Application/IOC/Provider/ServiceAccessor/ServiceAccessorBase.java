package Application.IOC.Provider.ServiceAccessor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import Application.IOC.Dependence;
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

        Object result;
        try {
            result = selectConstractor.newInstance(parameters);
        } catch (Exception e) {
            throw new Exception(clazz.getName() + " constract default");
        }

        // 注解注入
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Dependence.class)) {
                field.setAccessible(true);
                Object obj = serviceProvider.GetService(field.getType());
                field.set(result, obj);
            }
        }
        return result;
    }

    @Override
    public Object Resolve() throws Exception {
        return ResolveObject();
    }
}
