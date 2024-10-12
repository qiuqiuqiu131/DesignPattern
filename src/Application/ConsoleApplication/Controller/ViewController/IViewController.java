package Application.ConsoleApplication.Controller.ViewController;

import Application.ConsoleApplication.ConsoleView.IConsoleView;

public interface IViewController {
    void Push(Class<?> view) throws Exception;

    void Pop();

    IConsoleView GetView();
}
