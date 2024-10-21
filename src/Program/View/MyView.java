package Program.View;

import Application.ConsoleApplication.Annotation.ConsoleCommand;
import Application.ConsoleApplication.ConsoleView.ConsoleViewBase;
import Application.ConsoleApplication.Controller.ViewController.IViewController;

public class MyView extends ConsoleViewBase {
    public MyView(IViewController vc) {
        super(vc);
    }

    @Override
    protected String GetTitle() {
        return "MyView";
    }

    @ConsoleCommand(Name = "Hello", Index = 0)
    private String Hello() {
        return "Hello";
    }

    @ConsoleCommand(Name = "Translate_MainView", Index = 1)
    private String Translate_MainView() throws Exception {
        return Translate("MainView");
    }

}
