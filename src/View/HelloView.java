package View;

import Application.ConsoleApplication.Annotation.ConsoleCommand;
import Application.ConsoleApplication.ConsoleView.ConsoleViewBase;
import Application.ConsoleApplication.Controller.ViewController.IViewController;

public class HelloView extends ConsoleViewBase {

    public HelloView(IViewController viewController) {
        super(viewController);
    }

    @Override
    protected String GetTitle() {
        return "HelloView";
    }

    @ConsoleCommand(Name = "Enter", Index = 0)
    private String Enter() throws Exception {
        return Translate("WorldView");
    }

}
