import Application.ConsoleApplication.ConsoleApplication;
import Application.IOC.Interface.IServiceCollection;
import View.HelloView;

public class Application extends ConsoleApplication {

    public Application() throws Exception {
        super();
    }

    @Override
    protected void RegisterService(IServiceCollection serviceCollection) throws Exception {

    }

    @Override
    protected Class<?> GetStartView() {
        return HelloView.class;
    }
}
