package Program;

import Application.ConsoleApplication.ConsoleApplication;
import Application.IOC.Interface.IServiceCollection;
import Program.Model.ExecuteCountModel;
import Program.Model.IExecuteCountModel;
import Program.Service.ViewService1;
import Program.Service.ViewService2;
import Program.Service.ViewService3;
import Program.Service.Interface.IViewService1;
import Program.Service.Interface.IViewService2;
import Program.Service.Interface.IViewService3;
import Program.View.MainView;

public class Application extends ConsoleApplication {

    public Application() throws Exception {
        super();
    }

    @Override
    protected void RegisterService(IServiceCollection serviceCollection) throws Exception {
        serviceCollection
                .RegisterTransient(IViewService1.class, ViewService1.class)
                .RegisterTransient(IViewService2.class, ViewService2.class)
                .RegisterTransient(IViewService3.class, ViewService3.class);

        serviceCollection
                .RegisterSingleton(IExecuteCountModel.class, ExecuteCountModel.class);
    }

    @Override
    protected Class<?> GetStartView() {
        return MainView.class;
    }
}
