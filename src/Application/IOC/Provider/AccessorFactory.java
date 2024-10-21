package Application.IOC.Provider;

import java.util.List;

import Application.IOC.ServiceDescriptor;
import Application.IOC.ServiceLiftTime;
import Application.IOC.Interface.IServiceAccessor;
import Application.IOC.Provider.ServiceAccessor.ScopeServiceAccessor;
import Application.IOC.Provider.ServiceAccessor.SingletonServiceAccessor;
import Application.IOC.Provider.ServiceAccessor.TransientServiceAccessor;

public class AccessorFactory {
    private List<ServiceDescriptor> serviceDescriptors;

    public AccessorFactory(List<ServiceDescriptor> serviceCollection) {
        serviceDescriptors = List.copyOf(serviceCollection);
    }

    public IServiceAccessor CreateAccessor(Class<?> clazz) {
        ServiceDescriptor descriptor = null;
        for (ServiceDescriptor serviceDescriptor : serviceDescriptors) {
            if (serviceDescriptor.HasInterface()) {
                try {
                    if (serviceDescriptor.GetInterFace().equals(clazz)) {
                        descriptor = serviceDescriptor;
                        break;
                    }
                } catch (Exception e) {
                    continue;
                }
            } else {
                if (serviceDescriptor.GetClazz().equals(clazz)) {
                    descriptor = serviceDescriptor;
                    break;
                }
            }
        }

        if (descriptor == null)
            return null;

        if (descriptor.GetLiftTime() == ServiceLiftTime.Transient)
            return new TransientServiceAccessor(descriptor.GetClazz());
        else if (descriptor.GetLiftTime() == ServiceLiftTime.Singleton)
            return new SingletonServiceAccessor(descriptor.GetClazz());
        else
            return new ScopeServiceAccessor(descriptor.GetClazz());
    }
}
