package Application.ConsoleApplication;

import Application.ConsoleApplication.Controller.ConsoleController.ConsoleController;
import Application.ConsoleApplication.Controller.ConsoleController.IConsoleController;
import Application.ConsoleApplication.Controller.IOController.IIOController;
import Application.ConsoleApplication.Controller.IOController.IOController;
import Application.ConsoleApplication.Controller.ViewController.IViewController;
import Application.ConsoleApplication.Controller.ViewController.ViewContorller;
import Application.IOC.ServiceCollection;
import Application.IOC.Interface.IServiceCollection;
import Application.IOC.Interface.IServiceProvider;

public abstract class ConsoleApplication {
    private final IServiceProvider service;

    public ConsoleApplication() throws Exception {
        // IOC容器配置
        IServiceCollection serviceCollection = new ServiceCollection();

        serviceCollection.RegisterSingleton(IViewController.class, ViewContorller.class);
        serviceCollection.RegisterSingleton(IIOController.class, IOController.class);
        serviceCollection.RegisterSingleton(IConsoleController.class, ConsoleController.class);
        ConsoleApplicationExtension.AddConsoleViews(serviceCollection, "View");
        RegisterService(serviceCollection);

        service = serviceCollection.BuildServiceProvider();
    }

    public void Start() throws Exception {
        IConsoleController consoleController = service.GetService(IConsoleController.class);
        consoleController.Run(GetStartView());
    }

    protected void RegisterService(IServiceCollection serviceCollection) throws Exception {
    }

    protected abstract Class<?> GetStartView();
}
