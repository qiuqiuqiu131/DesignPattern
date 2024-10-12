package View;

import Application.ConsoleApplication.Annotation.ConsoleCommand;
import Application.ConsoleApplication.ConsoleView.ConsoleViewBase;
import Application.ConsoleApplication.Controller.ViewController.IViewController;

public class WorldView extends ConsoleViewBase {

    public WorldView(IViewController viewController) {
        super(viewController);
    }

    @Override
    protected String GetTitle() {
        return "WorldView";
    }

    @ConsoleCommand(Name = "Print", Index = 0)
    private String Print() {
        return "Hello World";
    }

    @ConsoleCommand(Name = "Print2", Index = 1)
    private String Print2() {
        return "Hello World";
    }

    @ConsoleCommand(Name = "Print3", Index = 2)
    private String Print3() {
        return "Hello World";
    }

}
