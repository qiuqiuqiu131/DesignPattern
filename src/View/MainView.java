package View;

import Application.ConsoleApplication.Annotation.ConsoleCommand;
import Application.ConsoleApplication.ConsoleView.ConsoleViewBase;
import Application.ConsoleApplication.Controller.ViewController.IViewController;

public class MainView extends ConsoleViewBase {

    public MainView(IViewController viewController) {
        super(viewController);
    }

    @Override
    protected String GetTitle() {
        return "Main View";
    }

    @ConsoleCommand(Name = "View1", Index = 0)
    public String View1() throws Exception {
        return Translate("View1");
    }

    @ConsoleCommand(Name = "View2", Index = 1)
    public String View2() throws Exception {
        return Translate("View2");
    }

    @ConsoleCommand(Name = "View3", Index = 2)
    public String View3() throws Exception {
        return Translate("View3");
    }

}
