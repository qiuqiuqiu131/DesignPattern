package View;

import Application.ConsoleApplication.Annotation.ConsoleCommand;
import Application.ConsoleApplication.ConsoleView.ConsoleViewBase;
import Application.ConsoleApplication.Controller.ViewController.IViewController;
import Application.IOC.Dependence;
import Service.IMyService;

public class HelloView extends ConsoleViewBase {

    @Dependence
    private IMyService myService;

    public HelloView(IViewController viewController) {
        super(viewController);
    }

    @Override
    protected String GetTitle() {
        myService.Print();
        return "HelloView";
    }

    @ConsoleCommand(Name = "Enter", Index = 0)
    private String Enter() throws Exception {
        return Translate("WorldView");
    }

}
