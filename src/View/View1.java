package View;

import Application.ConsoleApplication.ConsoleView.ConsoleViewBase;
import Application.ConsoleApplication.Controller.ViewController.IViewController;

public class View1 extends ConsoleViewBase {

    public View1(IViewController viewController) {
        super(viewController);
    }

    @Override
    protected String GetTitle() {
        return "View1";
    }

}
