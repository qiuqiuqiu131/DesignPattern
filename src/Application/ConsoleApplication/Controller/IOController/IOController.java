package Application.ConsoleApplication.Controller.IOController;

import java.util.Scanner;

public class IOController implements IIOController {
    Scanner scanner = new Scanner(System.in);

    public IOController Println(String text, PrintMethod method) {
        PrintMethod(method);
        System.out.format(text);
        NewLine();
        return this;
    }

    public IOController Println(String text) {
        return Println(text, PrintMethod.Normal);
    }

    public IOController Print(String text, PrintMethod method) {
        PrintMethod(method);
        System.out.format(text);
        return this;
    }

    public IOController Print(String text) {
        return Print(text, PrintMethod.Normal);
    }

    public IOController NewLine() {
        System.out.println();
        return this;
    }

    public String ReadLine() {
        return scanner.nextLine();
    }

    private void PrintMethod(PrintMethod method) {
        switch (method) {
            case PrintMethod.Error:
                System.out.format("\33[31;1mError: \33[0m");
                break;
            case PrintMethod.Message:
                System.out.format("\33[34;1mMessage: \33[0m");
            default:
                break;
        }
    }
}
