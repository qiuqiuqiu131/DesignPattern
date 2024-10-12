package Application.ConsoleApplication.Controller.IOController;

public interface IIOController {
    IOController Println(String text, PrintMethod method);

    IOController Println(String text);

    IOController Print(String text, PrintMethod method);

    IOController Print(String text);

    IOController NewLine();

    String ReadLine();
}
