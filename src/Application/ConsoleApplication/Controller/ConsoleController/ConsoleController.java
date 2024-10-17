package Application.ConsoleApplication.Controller.ConsoleController;

import Application.ConsoleApplication.ConsoleView.IConsoleView;
import Application.ConsoleApplication.Controller.IOController.IIOController;
import Application.ConsoleApplication.Controller.IOController.PrintMethod;
import Application.ConsoleApplication.Controller.ViewController.IViewController;

public class ConsoleController implements IConsoleController {
    private final IViewController viewController;
    private final IIOController ioController;

    public ConsoleController(IViewController viewController, IIOController ioController) {
        this.viewController = viewController;
        this.ioController = ioController;
    }

    public void Run(Class<?> clazz) throws Exception {
        viewController.Push(clazz);
        Operate();
    }

    private void Operate() throws Exception {
        // 显示当前视图
        IConsoleView view = viewController.GetView();
        ShowView(view);
        ExecuteCommand(view);
        Operate();
    }

    private void ShowView(IConsoleView view) {
        String text = view.View();
        ioController.Println(text);
    }

    private void ExecuteCommand(IConsoleView view) throws Exception {
        int commandNum = view.CommandNum();

        ioController.Print("\33[33;1mCommandIndex:\33[0m");
        int index;
        while (true) {
            try {
                index = Integer.parseInt(ioController.ReadLine());
                if (index >= 1 && index <= commandNum)
                    break;
                ioController.Println("The Command doesn't exist！", PrintMethod.Error);
            } catch (NumberFormatException e) {
                ioController.Println("Input Error!", PrintMethod.Error);
            }
            ioController.Print("\33[33;1mInput Again:\33[0m ");
        }

        String message = view.Execute(index);
        if (message != null)
            ioController.Println(message, PrintMethod.Message);

        ioController.ReadLine();
    }
}
