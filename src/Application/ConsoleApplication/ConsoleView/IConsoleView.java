package Application.ConsoleApplication.ConsoleView;

public interface IConsoleView {
    String View();

    String Execute(int index) throws Exception;

    int CommandNum();

    String Title();
}
