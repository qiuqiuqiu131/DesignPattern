import Application.ConsoleApplication.ConsoleApplication;
import Application.IOC.Interface.IServiceCollection;
import Service.IMyService;
import Service.MyService;
import View.HelloView;

public class Application extends ConsoleApplication {

    public Application() throws Exception {
        super();
    }

    @Override
    protected void RegisterService(IServiceCollection serviceCollection) throws Exception {
        serviceCollection.RegisterTransient(IMyService.class, MyService.class);
    }

    @Override
    protected Class<?> GetStartView() {
        return HelloView.class;
    }
}
