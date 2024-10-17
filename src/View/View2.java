package View;

import Application.ConsoleApplication.ConsoleView.ConsoleViewBase;
import Application.ConsoleApplication.Controller.ViewController.IViewController;

public class View2 extends ConsoleViewBase {

    public View2(IViewController viewController) {
        super(viewController);
    }

    @Override
    protected String GetTitle() {
        return "View2";
    }

}
