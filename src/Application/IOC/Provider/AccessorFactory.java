package Application.IOC.Provider;

import java.util.List;

import Application.IOC.ServiceDescriptor;
import Application.IOC.ServiceLiftTime;
import Application.IOC.Interface.IServiceAccessor;
import Application.IOC.Interface.IServiceProvider;
import Application.IOC.Provider.ServiceAccessor.ScopeServiceAccessor;
import Application.IOC.Provider.ServiceAccessor.SingletonServiceAccessor;
import Application.IOC.Provider.ServiceAccessor.TransientServiceAccessor;

public class AccessorFactory {
    private List<ServiceDescriptor> serviceDescriptors;
    private IServiceProvider serviceProvider;

    public AccessorFactory(List<ServiceDescriptor> serviceCollection, IServiceProvider serviceProvider) {
        serviceDescriptors = List.copyOf(serviceCollection);
        this.serviceProvider = serviceProvider;
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
            return new TransientServiceAccessor(descriptor.GetClazz(), serviceProvider);
        else if (descriptor.GetLiftTime() == ServiceLiftTime.Singleton)
            return new SingletonServiceAccessor(descriptor.GetClazz(), serviceProvider);
        else
            return new ScopeServiceAccessor(descriptor.GetClazz(), serviceProvider);
    }
}
