package Program.View;

import Application.ConsoleApplication.Annotation.ConsoleCommand;
import Application.ConsoleApplication.ConsoleView.ConsoleViewBase;
import Application.ConsoleApplication.Controller.ViewController.IViewController;
import Application.IOC.Dependence;
import Program.Model.IExecuteCountModel;

public class MainView extends ConsoleViewBase {

    @Dependence
    IExecuteCountModel executeCountModel;

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

    @ConsoleCommand(Name = "ExecuteCount", Index = 3)
    public String GetExecuteCount() {
        return String.valueOf(executeCountModel.GetCount());
    }

}
