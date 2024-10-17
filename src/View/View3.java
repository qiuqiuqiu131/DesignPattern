package View;

import Application.ConsoleApplication.ConsoleView.ConsoleViewBase;
import Application.ConsoleApplication.Controller.ViewController.IViewController;

public class View3 extends ConsoleViewBase {

    public View3(IViewController viewController) {
        super(viewController);
    }

    @Override
    protected String GetTitle() {
        return "View3";
    }

}
