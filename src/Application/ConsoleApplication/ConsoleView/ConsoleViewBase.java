package Application.ConsoleApplication.ConsoleView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import Application.ConsoleApplication.Annotation.ConsoleCommand;
import Application.ConsoleApplication.Controller.ViewController.IViewController;

public abstract class ConsoleViewBase implements IConsoleView {
    private final IViewController viewController;

    private Map<String, Method> commmands = new HashMap<>();
    private List<String> commandNames = new ArrayList<>();

    public ConsoleViewBase(IViewController viewController) {
        this.viewController = viewController;
        Init();
    }

    public String Execute(int index) throws Exception {
        Method method = commmands.get(commandNames.get(index - 1));
        method.setAccessible(true);
        return (String) method.invoke(this, (Object[]) null);
    }

    public String View() {
        String newLine = System.lineSeparator();
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("\33[32;1m[%s]\33[0m", GetTitle()));
        int index = 1;
        for (String string : commandNames) {
            builder.append(newLine);
            builder.append(String.format("\33[36;1m%d.\33[0m %s", index++, string));
        }
        return builder.toString();
    }

    public int CommandNum() {
        return commandNames.size();
    }

    public String Title() {
        return GetTitle();
    }

    private void Init() {
        Class<?> clazz = this.getClass();
        Method[] methods = Stream.concat(
                Arrays.stream(clazz.getDeclaredMethods()),
                Arrays.stream(clazz.getSuperclass().getDeclaredMethods())).toArray(Method[]::new);
        for (Method method : methods) {
            method.setAccessible(true);
            if (method.isAnnotationPresent(ConsoleCommand.class)) {
                String name = method.getAnnotation(ConsoleCommand.class).Name();
                commandNames.add(name);
                commmands.put(name, method);
            }
        }

        commandNames.sort((s1, s2) -> {
            int index1 = commmands.get(s1).getAnnotation(ConsoleCommand.class).Index();
            int index2 = commmands.get(s2).getAnnotation(ConsoleCommand.class).Index();
            return Integer.compare(index1, index2);
        });
    }

    protected abstract String GetTitle();

    @ConsoleCommand(Name = "Back", Index = 100)
    private String Back() {
        viewController.Pop();
        return "Back";
    }

    protected String Translate(String target) throws Exception {
        String className = this.getClass().getPackage().getName() + "." + target;
        Class<?> clazz = Class.forName(className);
        return Translate(clazz);
    }

    protected String Translate(Class<?> clazz) throws Exception {
        viewController.Push(clazz);
        return "Translate to " + viewController.GetView().Title();
    }
}
